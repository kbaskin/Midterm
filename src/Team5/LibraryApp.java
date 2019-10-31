package Team5;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Intro
		// createList
		ArrayList<Book> bookList = new ArrayList<>();
		String cont = "n";
		// set arraylist = BookHelper.readBookList()

		System.out.println("It's the library, whatever, no big deal");

		do {

		// menu

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
			
			break;
			
		case 2:	// search by author
			
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
			cont = Validator.getStringMatchingRegex(scan,"Are you sure you want to leave the library or whatever: ","[YyNn]");
			
			
			break;
		} 
		}while(cont.equalsIgnoreCase("n"));

		//
		System.out.println("Enjoy your reading! Come back in 14 Days!");
		System.out.println("...\n...\n...or Else.");

		scan.close();
	}

}
