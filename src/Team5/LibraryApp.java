package Team5;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Intro
		// createList
		int counter = 0;
		String bookTitle, bookAuthor;
		ArrayList<Book> bookList = new ArrayList<>();
		bookList.add(new Book("It follows", "me", true, "12/12/12"));
		bookList.add(new Book("it", "me", true, "12/12/12"));
		bookList.add(new Book("steve", "you", false, "12/12/12"));
		bookList.add(new Book("It is all good in the hood", "you", false, "/12/12/12"));
		String cont = "n";
		// set arraylist = BookHelper.readBookList()

		System.out.println("It's the library, whatever, no big deal\n");

		do {

			// menu
			System.out.println(
					"\n1. List all books\n2. Search by author\n3. Search by title or title keyword\n4. Check out a book\n5. Return a book\n6. Donate a book\n7. Exit");
			int menuChoice = Validator.getInt(scan, "\nWhat would you like to do?", 1, 7);

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
				System.out.printf("%-30s  %-30s %-15s", "   Title", "Author", "Status");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					System.out.printf("\n%-30s  %-30s %-15s", (i + 1) + ". " + bookList.get(i).getTitle(),
							bookList.get(i).getAuthor(), bookList.get(i).getStatus());

				}
				System.out.println();
				break;

			case 2: // search by author
				System.out.println("Please enter the name of the author you would like to search: ");
				bookAuthor = scan.nextLine();
				counter = 0;
				System.out.printf("%-30s  %-30s %-15s", "   Title", "Author", "Status");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					if (bookAuthor.equalsIgnoreCase(bookList.get(i).getAuthor())) {
						System.out.printf("\n%-30s %-15s", counter++ + bookList.get(i).getTitle(),
								bookList.get(i).getStatus());
					}
				}

				break;

			case 3: // search by title keyword

				System.out.print("Please enter a title or title keyword for the book you are looking for: ");
				String keyWord = scan.nextLine();
				System.out.println();
				counter = 0;
				System.out.printf("%-30s  %-30s %-15s", "   Title", "Author", "Status");
				System.out.println();
				for (int i = 0; i < bookList.size(); i++) {
					// check if exact title match and display before other books
					if (keyWord.equalsIgnoreCase(bookList.get(i).getTitle())) {
						System.out.printf("\n%-30s %-30s %-15s", ++counter + ". " + bookList.get(i).getTitle(),
								bookList.get(i).getAuthor(), bookList.get(i).getStatus());
					}

				}
				for (int i = 0; i < bookList.size(); i++) {
					// check for keyword and display matches
					String[] keyWords = bookList.get(i).getTitle().split(" ");
					for (int j = 0; j < keyWords.length; j++) {
						//confirms that it isn't found twice (not the whole title)
						if ((keyWord.equalsIgnoreCase(keyWords[j]))
								&& (!keyWord.equalsIgnoreCase(bookList.get(i).getTitle()))) {
							
							System.out.printf("\n%-30s %-30s %-15s", ++counter + ". " + bookList.get(i).getTitle(),
									bookList.get(i).getAuthor(), bookList.get(i).getStatus());
						}
					}

				}
				
				if(counter==0)
					System.out.print("Sorry, we couldn't find any books with that keyword.");
				
				System.out.println();

				break;

			case 4: // check out a book (reiterate book list)

				break;

			case 5: // return a book

				break;

			case 6: // Donate a book
				System.out.println("Oh, wait, you aren't kidding? You want to donate a book?");
				bookTitle = Validator.getString(scan, "Sweet, please enter the title of the book: ");
				bookAuthor = Validator.getString(scan, "Awesome, and please enter the author's name: ");
				Boolean extraCopy = false;
				for(int i = 0; i<bookList.size();i++)
				{
					if((bookList.get(i).getTitle().equalsIgnoreCase(bookTitle)) && (bookList.get(i).getAuthor().equalsIgnoreCase(bookAuthor)))
					{
						System.out.println("What, that book? We already got one of those. Donate it to Bryan, I heard he has a library.");
						extraCopy = true;
					}
				}
				if(extraCopy == false)
				{
				bookList.add(new Book(bookTitle,bookAuthor));
				System.out.println("Thank you! We have added " + bookTitle + " to the library of Team 5.");
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
