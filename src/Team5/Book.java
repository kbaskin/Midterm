package Team5;

public class Book {

	private String title;
	private String author;
	private boolean status;
	private String dueDate;
	
	//delete me -

	public Book() {
		super();
	}

	public Book(String title, String author, boolean status, String dueDate) {
		super();
		this.title = title;
		this.author = author;
		this.status = status;
		this.dueDate = dueDate;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return String.format("%-30s  %-20s %-15b %-10s", title, author, status, dueDate);
	}

}
