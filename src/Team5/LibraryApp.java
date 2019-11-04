/*
 * Authors: Katie Baskin, Matt Lynn, Tyler Mogk
 * Go Team 5!
 */

package Team5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int counter = 0;
		boolean lateFees = false;
		String bookTitle, bookAuthor;
		ArrayList<Book> bookList = new ArrayList<Book>();
		bookList = BookWriteAndRead.readBookListFromFile();
		String cont = "n";
		LocalDate today = LocalDate.now();
		LocalDate dueDate = today.plusWeeks(2);
		HashMap<String, ArrayList<Book>> userList = new HashMap<>();
		userList = BookWriteAndRead.readUserFromFile();
		boolean returnName = false;

		// welcome prompt
		System.out.println("It's the library, whatever, no big deal\n");
		// prompt for user's full name
		String userName = Validator.getString(scan, "So... what's your full name? ");

		// check if user is in system
		for (String n : userList.keySet()) {
			if (userName.equalsIgnoreCase(n)) {
				userName = n;
				returnName = true;
				System.out.println("Welcome back " + userName + "!");
			}
		}

		if (returnName == false) {
			System.out.println("\nI see you are new! Welcome to the best library everrrrr!");
			// if not in the system, ask if they want to be a member
			String addUser = Validator.getString(scan,
					"Would you like to become a member of the best library ever? (y/n)");
			if (addUser.equalsIgnoreCase("yes") || (addUser.equalsIgnoreCase("y"))) {
				userList.put(userName, null);

				BookWriteAndRead.addNewUserToFile(userName, null);
				userList = BookWriteAndRead.readUserFromFile();
				System.out.println("\nGreat! You are now an official member!");
			} else {
				System.out.println(
						"\nLAME. \nMaybe when you're done working your muscles at whatever gym Group 4 signed you up for... \nyou can come back and work your brain by reading some of our awesome books!");
				System.exit(0);
			}
		}

		// create ArrayList for user
		ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
		checkedOutBooks.ensureCapacity(5);
		checkedOutBooks = userList.get(userName);
		for (Book b : checkedOutBooks) {
			if (b.getDueDate().isBefore(today)) { // if returning user has book past due
				lateFees = true;
				System.out.println(b.getTitle() + " by " + b.getAuthor() + " is overdue.");
			}
		}
		if (lateFees) {
			String choice = Validator.getStringMatchingRegex(scan,
					"Do you want to pay the late fees with your Visa ending in " + (int) (Math.random() * 10000)
							+ "? (y/n):",
					"[YyNn]");
			if (choice.equalsIgnoreCase("n")) {
				System.out.println(
						"What, too cheap eh? Go win some money sweeping mines or playing hangman then, there's at least a few groups doing those right?");
				// needed to reference other groups
				System.out.println("I heard pneumonoultramicroscopicsilicovolcanoconiosis is a word.");
				System.exit(0);
			}
			System.out.println("Your card has been charged, you should really return those!");
		}

		do {

			// menu
			System.out.println(
					"\n1. List all books\n2. Search by author\n3. Search by title or title keyword\n4. Check out a book\n5. Return a book\n6. Donate a book\n7. Buy a cat\n8. Exit");
			int menuChoice = Validator.getInt(scan, "\nWhat would you like to do? Enter menu number: ", 1, 8);

			// List of books title author status
			// search by author
			// search by title keyword
			// check out a book (reiterate book list)
			// -if not checked out, check out for 2 wks
			// - if checked out already, inform user and ask if they would like to be put on
			// hold list
			// return a book
			// Donate a book
			// Exit

			switch (menuChoice) {

			case 1: // List of books title author status
				printBooks(bookList);
				break;

			case 2: // search by author
				System.out.println("\nPlease enter the name of the author you would like to search: ");
				bookAuthor = scan.nextLine();
				counter = 0;
				// calling searchAuthor method to search through bookList and display results by
				// name or keyword
				int authorCounter = searchAuthor(bookList, bookAuthor);
				if (authorCounter == 0) {
					break;
				}

				// after showing authors found - give option to check one of the books
				// displayed out
				String askCheckOut = Validator.getStringMatchingRegex(scan,
						"\n\nDid you want to check one of these books out? (y/n) ", "[YyNn]");
				if (askCheckOut.equalsIgnoreCase("yes") || (askCheckOut.equalsIgnoreCase("y"))) {
					int bookCheckOut = Validator.getInt(scan,
							"\nSelect the number of the book you would like to check out? ", 1, authorCounter);
					checkOutByAuthor(bookAuthor, bookList, bookCheckOut, dueDate, userList, checkedOutBooks, userName);
				}
				break;

			case 3: // search by title keyword

				System.out.print("Please enter a title or title keyword for the book you are looking for: ");
				String keyWord = scan.nextLine();
				System.out.println();
				counter = 0;
				System.out.printf("%-40s  %-40s %-15s", "\n   Title", " Author", " Status");
				System.out.println();

				counter = searchByTitle(bookList, keyWord);
				if (counter == 0) {
					break;
				}

				// after showing list of titles by title/keyword, option to check one of them
				// out
				String userCheckOut = Validator.getString(scan,
						"\n\nDid you want to check one of these books out? (y/n) ");
				if (userCheckOut.equalsIgnoreCase("yes") || (userCheckOut.equalsIgnoreCase("y"))) {
					int bookCheckOut = Validator.getInt(scan,
							"\nSelect the number of the book you would like to check out? ", 1, counter);
					searchByTitleKeyword(keyWord, bookList, bookCheckOut, dueDate, userList, checkedOutBooks, userName);
				}
				break;

			case 4: // check out a book (reiterate book list)
				printBooks(bookList);
				int checkOut = Validator.getInt(scan,
						"\n\nSelect the number for the book you would like to check out: ", 1, bookList.size());

				if (bookList.get(checkOut - 1).getStatus().equalsIgnoreCase("Checked Out")) {
					System.out.println("\nThis book is currently unavailable. So sad!");

// asking user to join hold list
					String bookHold = Validator.getStringMatchingRegex(scan, "Did you want to join the hold list for this book? (y/n) ", "[YyNn]");
					if (bookHold.equalsIgnoreCase("yes") || (bookHold.equalsIgnoreCase("y"))) {
						// either way, we let user know that we don't have a hold list
						System.out.println("Cool, cool. Yeah we don't do that here. So come back, say, March " + (int)(Math.random()*20 +31) + ".");
					} else {
						System.out.println("Good stuff, we definitely don't do that anyway. Maybe you should check out Everything is Steak.");
					}

				} else { // confirm book check out
					String confirmCheckOut = Validator.getString(scan, "\nAre you sure you want to check out "
							+ bookList.get(checkOut - 1).getTitle() + "? (y/n) ");
					if (confirmCheckOut.equalsIgnoreCase("yes") || (confirmCheckOut.equalsIgnoreCase("y"))) {
						checkOutBook(bookList, checkOut - 1, dueDate, userList, checkedOutBooks, userName);
					}
				}

				break;

			case 5: // return a book
				if (checkedOutBooks.size() < 1) {
					System.out.println("You don't have any books checked out!");
					break;
				}
				// show user's books they have checked out
				printBooks(checkedOutBooks);
				int bookReturn = Validator.getInt(scan, "\n\nSelect the number for the book you would like to return: ",
						1, checkedOutBooks.size()); // 1 - end of the list of the books they have checked out

				// confirm return
				String confirmReturn = Validator.getString(scan, "\nAre you sure you want to return this book? (y/n)");
				if ((confirmReturn.equalsIgnoreCase("y")) || (confirmReturn.equalsIgnoreCase("yes"))) {

					// Increases quantity in bookList by one

					returnBook(bookList, checkedOutBooks, bookReturn, userList);
				}

				break;

			case 6: // Donate a book
				System.out.println("\nOh, wait, you aren't kidding? You want to donate a book?");
				bookTitle = Validator.getString(scan, "\nSweet, please enter the title of the book: ");
				bookAuthor = Validator.getString(scan, "\nAwesome, and please enter the author's name: ");
				donateBook(bookList, bookTitle, bookAuthor, userName); // book added to bookList and quantity increased
																		// by 1
				break;
			
			case 7: //Buy a cat
				System.out.println();
				// needed to reference group 1
				System.out.println("Sorry, this is not available yet. Team 5 is still in talks with Group 1.\nIn  the meantime, curl up with a good book!");
				break;
				
			case 8: // Exit
				cont = Validator.getStringMatchingRegex(scan, "\nAre you sure you want to leave the library? (y/n): ",
						"[YyNn]"); // confirm exit

				break;
			}
		} while (cont.equalsIgnoreCase("n"));

		//
		System.out.println("\nEnjoy your reading! Come back in 14 Days!");
		System.out.println("...\n...\n...or Else.");

		scan.close();
	}

	// book checkout
	public static void checkOutBook(ArrayList<Book> bookList, int checkOut, LocalDate dueDate,
			HashMap<String, ArrayList<Book>> userList, ArrayList<Book> checkedOutBooks, String userName) {
		// reduces quantity by 1 and checks for no copies left
		bookList.get(checkOut).setQuantity(bookList.get(checkOut).getQuantity() - 1);
		if (bookList.get(checkOut).getQuantity() == 0)
			bookList.get(checkOut).setStatus("Checked Out");
		bookList.get(checkOut).setDueDate(dueDate);

		// avoiding null pointer exception error
		if (checkedOutBooks.size() < 1) {
			userList.get(userName).add(bookList.get(checkOut));
			BookWriteAndRead.rewriteUserToFile(userList);
		} else {
			checkedOutBooks.add(bookList.get(checkOut));
			BookWriteAndRead.rewriteUserToFile(userList);
		}
		BookWriteAndRead.writeBooklistToFile(bookList);
		System.out.println(
				"\nThe book is yours, now get reading! This is due back on " + dueDate + ".\nDON'T be late...");
	}

	// case 1 method for booList print out
	public static void printBooks(ArrayList<Book> bookList) {
		System.out.printf("%-40s  %-40s %-15s", "\n   Title", " Author", " Status");
		System.out.println();
		for (int i = 0; i < bookList.size(); i++) {

			System.out.printf("\n%-40s  %-40s %-15s", (i + 1) + ". " + bookList.get(i).getTitle(),
					bookList.get(i).getAuthor(), bookList.get(i).getStatus());

		}
		System.out.println();
	}

	// case 2 search-by-author
	public static int searchAuthor(ArrayList<Book> bookList, String bookAuthor) {
		int authorCounter = 0;
		System.out.printf("%-40s %-40s %-15s", "\n   Title", " Author", " Status");
		System.out.println();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor())) {
				System.out.printf("\n%-40s %-40s %-15s", ++authorCounter + ". " + bookList.get(i).getTitle(),
						bookList.get(i).getAuthor(), bookList.get(i).getStatus());
			}
		}

		for (int i = 0; i < bookList.size(); i++) {
			// check for keyword and display matches
			String[] keyWords = bookList.get(i).getAuthor().split(" ");
			for (int j = 0; j < keyWords.length; j++) {
				// confirms that it isn't found twice (not the whole title)
				if ((bookAuthor.equalsIgnoreCase(keyWords[j]))
						&& (!bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor()))) {

					System.out.printf("\n%-40s %-40s %-15s", ++authorCounter + ". " + bookList.get(i).getTitle(),
							bookList.get(i).getAuthor(), bookList.get(i).getStatus());
				}
			}

		}
		// if we can't find any authors matching keyword
		if (authorCounter == 0) {
			System.out.print("\nSorry, we couldn't find any books by " + bookAuthor + ".");
			System.out.println();
			// break;
		}
		return authorCounter;
	}

	// case 2 checkout-by-author after searching for author or author keyword
	public static void checkOutByAuthor(String bookAuthor, ArrayList<Book> bookList, int bookCheckOut,
			LocalDate dueDate, HashMap<String, ArrayList<Book>> userList, ArrayList<Book> checkedOutBooks,
			String userName) {
		int authorCounter = 0;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor())) {
				authorCounter++;
				if (bookList.get(i).getStatus().equalsIgnoreCase("Checked Out")) {
					System.out.println("\nThis book is currently unavailable. So sad!");
					break;
				} else if (authorCounter == bookCheckOut) {
					checkOutBook(bookList, i, dueDate, userList, checkedOutBooks, userName);
				}
			}
		}

		for (int i = 0; i < bookList.size(); i++) {
			// check for keyword and display matches
			String[] keyWords = bookList.get(i).getAuthor().split(" ");
			for (int j = 0; j < keyWords.length; j++) {
				// confirms that it isn't found twice (not the whole title)
				if ((bookAuthor.equalsIgnoreCase(keyWords[j]))
						&& (!bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor()))) {
					authorCounter++;
					if (bookList.get(i).getStatus().equalsIgnoreCase("Checked Out")) {
						System.out.println("\nThis book is currently unavailable. So sad!");
						break;
					} else if (authorCounter == bookCheckOut) {
						checkOutBook(bookList, i, dueDate, userList, checkedOutBooks, userName);
					}
				}
			}

		}
	}

	// case 3 search-by-title/keyword
	public static int searchByTitle(ArrayList<Book> bookList, String keyWord) {
		int counter = 0;
		for (int i = 0; i < bookList.size(); i++) {
			// check if exact title match and display before other books
			if (keyWord.equalsIgnoreCase(bookList.get(i).getTitle())) {
				System.out.printf("\n%-40s %-40s %-15s", ++counter + ". " + bookList.get(i).getTitle(),
						bookList.get(i).getAuthor(), bookList.get(i).getStatus());
			}
		}
		for (int i = 0; i < bookList.size(); i++) {
			// check for keyword and display matches
			String[] keyWords = bookList.get(i).getTitle().split(" ");
			for (int j = 0; j < keyWords.length; j++) {
				// confirms that it isn't found twice (not the whole title)
				if ((keyWord.equalsIgnoreCase(keyWords[j]))
						&& (!keyWord.equalsIgnoreCase(bookList.get(i).getTitle()))) {

					System.out.printf("\n%-40s %-40s %-15s", ++counter + ". " + bookList.get(i).getTitle(),
							bookList.get(i).getAuthor(), bookList.get(i).getStatus());
				}
			}

		}

		System.out.println();
		if (counter == 0) { // if no books match title keyword search
			System.out.print("\nSorry, we couldn't find any books with the keyword " + keyWord + ".");
			System.out.println();
		}
		return counter;
	}

	// search by title keyword
	public static void searchByTitleKeyword(String keyWord, ArrayList<Book> bookList, int bookCheckOut,
			LocalDate dueDate, HashMap<String, ArrayList<Book>> userList, ArrayList<Book> checkedOutBooks,
			String userName) {
		int counter = 0;
		for (int i = 0; i < bookList.size(); i++) {
			if (keyWord.equalsIgnoreCase(bookList.get(i).getTitle())) {
				counter++;
				if (bookList.get(i).getStatus().equalsIgnoreCase("Checked Out")) {
					System.out.println("\nThis book is currently unavailable. So sad!");
					break;
				} else if (counter == bookCheckOut) {
					checkOutBook(bookList, i, dueDate, userList, checkedOutBooks, userName);
				}
			}
		}

		for (int i = 0; i < bookList.size(); i++) {
			// check for keyword and display matches
			String[] keyWords = bookList.get(i).getTitle().split(" ");
			for (int j = 0; j < keyWords.length; j++) {
				// confirms that it isn't found twice (not the whole title)
				if ((keyWord.equalsIgnoreCase(keyWords[j]))
						&& (!keyWord.equalsIgnoreCase(bookList.get(i).getTitle()))) {
					counter++;

					if (bookList.get(i).getStatus().equalsIgnoreCase("Checked Out")) {
						System.out.println("\nThis book is currently unavailable. So sad!");
						break;
					}
					if (counter == bookCheckOut) {
						checkOutBook(bookList, i, dueDate, userList, checkedOutBooks, userName);
					}
				}
			}

		}
	}

	// case 5 return book
	public static void returnBook(ArrayList<Book> bookList, ArrayList<Book> checkedOutBooks, int bookReturn,
			HashMap<String, ArrayList<Book>> userList) {
		for (Book b : bookList) {
			if (b.getTitle().equals(checkedOutBooks.get((bookReturn - 1)).getTitle())
					&& b.getAuthor().equals(checkedOutBooks.get((bookReturn - 1)).getAuthor())) {
				b.setQuantity(b.getQuantity() + 1);
				b.setStatus("On Shelf");
			}

		}

		// bookList.get(bookReturn - 1).setStatus("On Shelf");
		checkedOutBooks.remove(bookReturn - 1);
		BookWriteAndRead.rewriteUserToFile(userList);
		BookWriteAndRead.writeBooklistToFile(bookList);
		System.out.println("\nBook returned! You should definitely check out another book now!");
	}

	// case 6 donate a book
	public static void donateBook(ArrayList<Book> bookList, String bookTitle, String bookAuthor, String userName) {
		Boolean extraCopies = false;
		for (Book b : bookList) {
			if ((b.getTitle().equalsIgnoreCase(bookTitle)) && b.getAuthor().equalsIgnoreCase(bookAuthor)) {
				// if we have 5 copies, rejects
				if (b.getQuantity() > 4) {
					System.out.println("\nWhat, that book? Come on " + userName + ", we already got like, "
							+ b.getQuantity() + " of those. Donate it to Bryan, I heard he has a library.");
					// reference to Bryan's project

				} else {
					// if we have less than 5, increase quantity
					b.setQuantity(b.getQuantity() + 1);
					System.out.println("\nThank you " + userName + "! Now we have another copy of " + bookTitle
							+ " for the Team 5.");
					System.out.println("no takebacks.");
					b.setStatus("On Shelf");
				}
				extraCopies = true;
			}
		}
		// if we have no copies, add a copy to list
		if (!extraCopies) {
			bookList.add(new Book(bookTitle, bookAuthor));
			System.out
					.println("\nThank you " + userName + "! We have added " + bookTitle + " to the library of Team 5.");
			System.out.println("No takebacks.");
		}

		BookWriteAndRead.writeBooklistToFile(bookList);

	}

}
