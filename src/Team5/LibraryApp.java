package Team5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Intro
		// createList
		int counter = 0;
		String bookTitle, bookAuthor;
		ArrayList<Book> bookList = BookWriteAndRead.readBookListFromFile();
		String cont = "n";
		// set arraylist = BookHelper.readBookList()
		LocalDate today = LocalDate.now();
		HashMap<String, ArrayList<Book>> userList = new HashMap<>();
		userList=BookWriteAndRead.readUserFromFile();
		boolean returnName = false;

		System.out.println("It's the library, whatever, no big deal\n");
		String userName = Validator.getString(scan, "So... what's your name? ");
		// check if user is in system

		for (String n : userList.keySet()) {
			if (userName.equalsIgnoreCase(n)) {
				returnName = true;
				System.out.println("Welcome back " + userName + "!");
			}
		}

		if (returnName == false) {
			System.out.println("\nI see you are new! Welcome to the best library everrrrr!");
			// ask if they want to be a member
			String addUser = Validator.getString(scan,
					"Would you like to become a member of the best library ever? (y/n)");
			if (addUser.equalsIgnoreCase("yes") || (addUser.equalsIgnoreCase("y"))) {
				userList.put(userName, null);
				for(String i: userList.keySet())
				{
					System.out.println(i + userList.get(i));
				}
				
				BookWriteAndRead.addNewUserToFile(userName,null);
				System.out.println("\nGreat! You are now an official member!");
			} else {
				System.out.println(
						"\nLAME. \nMaybe when you're done working your muscles at whatever gym Group 3 signed you up for... \nyou can come back and work your brain by reading some of our awesome books!");
				System.exit(0);
			}
		}

		do {

			// menu
			System.out.println(
					"\n1. List all books\n2. Search by author\n3. Search by title or title keyword\n4. Check out a book\n5. Return a book\n6. Donate a book\n7. Exit");
			int menuChoice = Validator.getInt(scan, "\nWhat would you like to do? Enter menu number: ", 1, 7);

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
				System.out.printf("%-40s  %-40s %-15s", "\n   Title", " Author", " Status");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					System.out.printf("\n%-40s  %-40s %-15s", (i + 1) + ". " + bookList.get(i).getTitle(),
							bookList.get(i).getAuthor(), bookList.get(i).getStatus());

				}
				System.out.println();
				break;

			case 2: // search by author
				System.out.println("\nPlease enter the name of the author you would like to search: ");
				bookAuthor = scan.nextLine();
				counter = 0;
				int authorCounter = 0;
				System.out.printf("%-40s %-40s %-15s", "\n   Title", " Author", " Status");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					if (bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor())) {
						System.out.printf("\n%-40s %-40s %-15s", ++counter + ". " + bookList.get(i).getTitle(),
								bookList.get(i).getAuthor(), bookList.get(i).getStatus());
						authorCounter++;
					}
				}

				for (int i = 0; i < bookList.size(); i++) {
					// check for keyword and display matches
					String[] keyWords = bookList.get(i).getAuthor().split(" ");
					for (int j = 0; j < keyWords.length; j++) {
						// confirms that it isn't found twice (not the whole title)
						if ((bookAuthor.equalsIgnoreCase(keyWords[j]))
								&& (!bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor()))) {

							System.out.printf("\n%-40s %-40s %-15s", ++counter + ". " + bookList.get(i).getTitle(),
									bookList.get(i).getAuthor(), bookList.get(i).getStatus());
							authorCounter++;
						}
					}

				}
				// add in - after showing authors found - give option to check one of the books
				// displayed out
				String askCheckOut = Validator.getString(scan,
						"\n\nDid you want to check one of these books out? (y/n) ");
				if (askCheckOut.equalsIgnoreCase("yes") || (askCheckOut.equalsIgnoreCase("y"))) {
					int bookCheckOut = Validator.getInt(scan,
							"\nSelect the number of the book you would like to check out? ", 1, authorCounter);
					authorCounter = 0;
					for (int i = 0; i < bookList.size(); i++) {
						if (bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor())) {
							authorCounter++;
							if (authorCounter == bookCheckOut) {
							bookList.get(i).setStatus("Checked Out");
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
								if (authorCounter == bookCheckOut) {
								bookList.get(i).setStatus("Checked Out");
								}
							}
						}

					}
						bookList.get(bookCheckOut - 1).setStatus("Checked Out");
						LocalDate dueDate = today.plusWeeks(2);
						bookList.get(bookCheckOut - 1).setDueDate(dueDate);
						BookWriteAndRead.writeBooklistToFile(bookList);
						System.out.println("\nCongrats! The book is yours until " + dueDate + ". Happy reading!!");

				} else {
					continue;
				}

				if (counter == 0)
					System.out.print("\nSorry, we couldn't find any books by " + bookAuthor + ".");
				System.out.println();
				break;

			case 3: // search by title keyword

				System.out.print("Please enter a title or title keyword for the book you are looking for: ");
				String keyWord = scan.nextLine();
				System.out.println();
				counter = 0;
				System.out.printf("%-40s  %-40s %-15s", "\n   Title", " Author", " Status");
				System.out.println();
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

				if (counter == 0)
					System.out.print("\nSorry, we couldn't find any books with the keyword " + keyWord + ".");

				System.out.println();

				break;

			case 4: // check out a book (reiterate book list)
				System.out.printf("%-40s  %-40s %-15s", "\n   Title", " Author", " Status\n");
				for (int i = 0; i < bookList.size(); i++) {
					System.out.printf("\n%-40s  %-40s %-15s", (i + 1) + ". " + bookList.get(i).getTitle(),
							bookList.get(i).getAuthor(), bookList.get(i).getStatus());
				}
				int checkOut = Validator.getInt(scan,
						"\n\nSelect the number for the book you would like to check out: ", 1, bookList.size());

				if (bookList.get(checkOut - 1).getStatus().equalsIgnoreCase("Checked Out")) {
					System.out.println("\nThis book is currently unavailable. So sad!");
//					String bookHold = Validator.getString(scan, "Did you want to join the hold list for this book? (y/n) ");
//					if (bookHold.equalsIgnoreCase("yes") || (bookHold.equalsIgnoreCase("y"))) {
//						
//						System.out.println("You have been added to the hold list and we will let you know when the book is available! Super super cool!");
//					} else {
//						continue; 
//					}
					// FIXME - add code for hold list
				} else {
					String confirmCheckOut = Validator.getString(scan,
							"\nAre you sure you want to check out " + bookList.get(checkOut - 1).getTitle()
									+ "? (y/n) ");
					if (confirmCheckOut.equalsIgnoreCase("yes") || (confirmCheckOut.equalsIgnoreCase("y"))) {
						bookList.get(checkOut - 1).setStatus("Checked Out");
						LocalDate dueDate = today.plusWeeks(2);
						bookList.get(checkOut - 1).setDueDate(dueDate);
						BookWriteAndRead.writeBooklistToFile(bookList);
						System.out.println("\nThe book is yours, now get reading! This is due back on " + dueDate
								+ ".\nDON'T be late...");
					}
				}

				break;

			case 5: // return a book
				System.out.printf("%-40s  %-40s %-15s", "\n   Title", " Author", " Status\n");
				for (int i = 0; i < bookList.size(); i++) {
					System.out.printf("\n%-40s  %-40s %-15s", (i + 1) + ". " + bookList.get(i).getTitle(),
							bookList.get(i).getAuthor(), bookList.get(i).getStatus());

				}
				int bookReturn = Validator.getInt(scan, "\n\nSelect the number for the book you would like to return: ",
						1, bookList.size()); // 1 - end of the list of the books they have checked out

				if (bookList.get(bookReturn - 1).getStatus().equalsIgnoreCase("On Shelf")) {
					System.out.println("\nHa! You can't return a book you don't have! Try again.");
				} else {
					String confirmReturn = Validator.getString(scan,
							"\nAre you sure you want to return this book? (y/n)");
					if ((confirmReturn.equalsIgnoreCase("y")) || (confirmReturn.equalsIgnoreCase("yes"))) {
						bookList.get(bookReturn - 1).setStatus("On Shelf");
						BookWriteAndRead.writeBooklistToFile(bookList);
						System.out.println("\nBook returned! You should definitely check out another book now!");
						continue;
					}
				}

				break;

			case 6: // Donate a book
				System.out.println("\nOh, wait, you aren't kidding? You want to donate a book?");
				bookTitle = Validator.getString(scan, "\nSweet, please enter the title of the book: ");
				bookAuthor = Validator.getString(scan, "\nAwesome, and please enter the author's name: ");
				Boolean extraCopy = false;
				for (int i = 0; i < bookList.size(); i++) {
					if ((bookList.get(i).getTitle().equalsIgnoreCase(bookTitle))
							&& (bookList.get(i).getAuthor().equalsIgnoreCase(bookAuthor))) {
						System.out.println("\nWhat, that book? Come on " + userName
								+ ", we already got one of those. Donate it to Bryan, I heard he has a library.");
						extraCopy = true;
					}
				}
				if (extraCopy == false) {
					bookList.add(new Book(bookTitle, bookAuthor));
					System.out.println(
							"\nThank you " + userName + "! We have added " + bookTitle + " to the library of Team 5.");
					System.out.println("No takebacks.");
				}

				break;
			case 7: // Exit
				cont = Validator.getStringMatchingRegex(scan, "\nAre you sure you want to leave the library? (y/n): ",
						"[YyNn]");

				break;
			}
		} while (cont.equalsIgnoreCase("n"));

		//
		System.out.println("\nEnjoy your reading! Come back in 14 Days!");
		System.out.println("...\n...\n...or Else.");

		scan.close();
	}

}
