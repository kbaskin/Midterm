package Team5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class BookWriteAndRead {

//	public static void readBooklistFromFile() {
//
//		String bookList = "listofbooks.txt";
//		Path p = Paths.get("Book", bookList);
//
//		try {
//			File file = p.toFile();
//			BufferedReader br = new BufferedReader(new FileReader(file));
//
//			String line = br.readLine();
//
//			while (line != null) {
//				System.out.println(line);
//				line = br.readLine();
//			}
//			br.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("Something went wrong with the file. ");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//			System.out.println("Something went wrong when we tried to read the file. ");
//		}
//	}

	public static ArrayList<Book> readBookListFromFile() {

		String bookList = "listofbooks.txt";
		Path p = Paths.get(bookList);
		ArrayList<Book> listBooks = new ArrayList<>();
		File file = p.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			String[] bookParts = new String[5];
			int quantity;

			while (line != null) {

				bookParts = line.split("     ");
				quantity = Integer.parseInt(bookParts[4]); 
				try {
					LocalDate date = LocalDate.parse(bookParts[3]);
					listBooks.add(new Book(bookParts[0], bookParts[1], bookParts[2], date, quantity));
				}

				catch (DateTimeParseException e) {
					listBooks.add(new Book(bookParts[0], bookParts[1], bookParts[2], null, quantity));
				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file. ");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Something went wrong when we tried to read the file. ");
		}
		return listBooks;
	}

	public static void writeBooklistToFile(Book book) {

		String bookList = "listofbooks.txt";
		Path p = Paths.get(bookList);

		File file = p.toFile();
		PrintWriter pwOutput = null;

		try {
			pwOutput = new PrintWriter(new FileOutputStream(file, true));
			pwOutput.println(book);
			pwOutput.close();
		} catch (FileNotFoundException e) {
			System.out.println("Contact customer service.");
		} finally {
			pwOutput.close();

		}
	}

	public static void writeBooklistToFile(ArrayList<Book> writeList) {

		String bookList = "listofbooks.txt";
		Path p = Paths.get(bookList);

		File file = p.toFile();
		PrintWriter pwOutput = null;

		try {
			pwOutput = new PrintWriter(new FileOutputStream(file));

			for (int i = 0; i < writeList.size(); i++) {
				pwOutput.println(writeList.get(i));
			}
			pwOutput.close();

		} catch (FileNotFoundException e) {
			System.out.println("Contact customer service.");
		} finally {
			pwOutput.close();

		}
	}

	public static void createFile() {

		String bookList = "listofbooks.txt";
		Path p = Paths.get("Team5", bookList);

		if (Files.notExists(p)) {
			try {
				Files.createFile(p);
				System.out.println("The file was created successfully.");
			} catch (IOException e) {
				System.out.println("Something went wrong with file creation.");
			}
		} else {
			System.out.println("The file already exists.");

		}
	}

	public static void createDir() {

		String dirPath = "Team5";
		Path folder = Paths.get(dirPath);

		if (Files.notExists(folder)) {
			try {
				Files.createDirectories(folder);
				System.out.println("The folder was created successfully.");
			} catch (IOException e) {
				System.out.println("Something went wrong with folder creation.");
			}
		} else {
			System.out.println("Folder already exists.");
		}

	}

	// Writing user/library member to list
	public static void rewriteUserToFile(HashMap<String, ArrayList<Book>> userList) { 

		String fileName = "userlistofbooks.txt";
		Path p = Paths.get(fileName);

		File file = p.toFile();
		PrintWriter pwOutput = null;

		try {
			pwOutput = new PrintWriter(new FileOutputStream(file));
			
			for (String name: userList.keySet()) {
				pwOutput.print(name);
				if(userList.get(name) == null)
				{
					pwOutput.println("     null");
				}
				else if(userList.get(name).size() != 0)
				{
					for(int i = 0;i<userList.get(name).size(); i++)
				{
					
					pwOutput.print("     " + userList.get(name).get(i).getTitle()
						 	   + "     " + userList.get(name).get(i).getAuthor()
						 	   + "     " + userList.get(name).get(i).getStatus()
						 	   + "     " + userList.get(name).get(i).getDueDate()
						 	   + "     " + userList.get(name).get(i).getQuantity());
					
				}
					pwOutput.println();
				}
				else
					pwOutput.println("     null");

			}
			pwOutput.close();
			}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			}
		}

	public static void addNewUserToFile(String userName, ArrayList<Book> userList) { 

		String fileName = "userlistofbooks.txt";
		Path p = Paths.get(fileName);

		File file = p.toFile();
		PrintWriter pwOutput = null;

		try {
			pwOutput = new PrintWriter(new FileOutputStream(file, true));
				pwOutput.println(userName + "     null");
			pwOutput.close();
			}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			}
		}
	
//	public static void addNewUserToFile(HashMap<String, ArrayList<Book>> userList) { 
//
//		String fileName = "userlistofbooks.txt";
//		Path p = Paths.get(fileName);
//
//		File file = p.toFile();
//		PrintWriter pwOutput = null;
//
//		try {
//			pwOutput = new PrintWriter(new FileOutputStream(file, true));
//			pwOutput.println(user);
//			pwOutput.close();
//			}
//		 catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//			}
//		}
	

	
	

	public static HashMap<String, ArrayList<Book>> readUserFromFile() {

		String fileName = "userlistofbooks.txt";
		Path p = Paths.get(fileName);
		HashMap<String, ArrayList<Book>> userList = new HashMap<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String line = br.readLine();

			String name;

			while (line != null) {
				ArrayList<Book> bookList = new ArrayList<>();
				String[] bookParts;				
				bookParts = line.split("     ");
				name = bookParts[0];
				if(bookParts[1].equals("null"))
				userList.put(name, bookList);
				for (int i = 1; i+4 < bookParts.length; i += 5) {

					LocalDate date = LocalDate.parse(bookParts[i + 3]);
					int quantity = Integer.parseInt(bookParts[i+4]);
					bookList.add(new Book(bookParts[i], bookParts[i + 1], bookParts[i + 2], date, quantity));
					userList.put(name, bookList);

				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file. ");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Something went wrong when we tried to read the file. ");
		}

		return userList;

	}

}
