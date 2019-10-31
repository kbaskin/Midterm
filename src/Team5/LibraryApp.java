package Team5;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Intro
		// createList
		int counter = 0;
		ArrayList<Book> bookList = new ArrayList<>();
		String cont = "n";
		// set arraylist = BookHelper.readBookList()

		System.out.println("It's the library, whatever, no big deal\n");

		do {

		// menu
			System.out.println(
					"1. List all books\n2. Search by author\n3. Search by title or title keyword\n4. Check out a book\n5. Return a book\n6. Donate a book\n7. Exit");
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

		switch (menuChoice)	{

		case 1:	// List of books title author status
				System.out.printf("%-30s  %-30s %-15s", "Title", "Author", "Status");
				for (int i = 0; i < bookList.size(); i++) {
					System.out.printf("%-30s  %-30s %-15s", (i + 1) + bookList.get(i).getTitle(),
							bookList.get(i).getAuthor(), bookList.get(i).getStatus());

				}
			
			break;
			
		case 2:	// search by author
				System.out.println("Please enter the name of the author you would like to search: ");
				String bookAuthor = scan.nextLine();
				counter = 0;
				for (int i = 0; i < bookList.size(); i++) {
					if (bookAuthor.equals(bookList.get(i).getAuthor())) {
						System.out.printf("%-30s %-15s", counter++ + bookList.get(i).getTitle(),
								bookList.get(i).getStatus());
					}
				}
			
			break;
			
		case 3:	// search by title keyword
			
			break;
			
		case 4:	// check out a book (reiterate book list)
			
			break;
			
		case 5:	// return a book
			
			break;
			
		case 6:	// Donate a book
		
			break;
		case 7:	// Exit
				cont = Validator.getStringMatchingRegex(scan, "\nAre you sure you want to leave the library? (y/n): ",
						"[YyNn]");
			
			
			break;
		} 
		}while(cont.equalsIgnoreCase("n"));

		//
		System.out.println("\nEnjoy your reading! Come back in 14 Days!");
		System.out.println("...\n...\n...or Else.");

		scan.close();
	}

}
