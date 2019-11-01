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
import java.util.ArrayList;

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
			String[] bookParts = new String[4];
			
			while (line != null) {
				
					bookParts = line.split("     ");
					listBooks.add(new Book(bookParts[0],bookParts[1],bookParts[2],bookParts[3]));
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
			
			for(int i = 0; i<writeList.size(); i++)
			{
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

}
