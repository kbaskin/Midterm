package Team5;

import java.time.LocalDate;

public class Book {

	private String title;
	private String author;
	private String status;
	private LocalDate dueDate;
	

	public Book() {
		super();
	}
	
	public Book(String title, String author, String status, LocalDate dueDate) {
		super();
		this.title = title;
		this.author = author;
		this.status = status;
		this.dueDate = dueDate;
	}

	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
		status = "true";
		dueDate = null;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return String.format(title + "     " + author + "     " + status + "     " + dueDate);
	}

}
