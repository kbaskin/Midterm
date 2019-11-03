package Team5;

import java.time.LocalDate;

public class Book {

	private String title;
	private String author;
	private String status;
	private LocalDate dueDate;
	private int quantity;

	public Book() {
		super();
	}
	
	public Book(String title, String author, String status, LocalDate dueDate, int quantity) {
		super();
		this.title = title;
		this.author = author;
		this.status = status;
		this.dueDate = dueDate;
		this.quantity = quantity;
	}

	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
		status = "On Shelf";
		dueDate = null;
		quantity = 1;
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
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
//		if(quantity == 0)
//			this.status = "checked out";
//		if(quantity >=1)
//			this.status = "on shelf";
	}

	@Override
	public String toString() {
		return String.format(title + "     " + author + "     " + status + "     " + dueDate + "     " + quantity);
	}

}
