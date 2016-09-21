import java.util.ArrayList;

public class BookManager {

	private ArrayList<Book> books;
	
	public BookManager() {
		super();
		books = new ArrayList<Book>();
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	/*
	 * Book Manager Methods
	 */
	
	/**
	 * Registers a new book to the system.
	 * @param newBook	The new book that's being added to the system.
	 */
	public void addBook(Book newBook) {
		for (Book book : books) {
			if (book.getTitle().equals(newBook.getTitle())) {
				book.incrementNumberOfCopies();
			}
		}
		books.add(newBook);
	}	
	
	public void listAllBooks() {
		for (Book book : books) {
			System.out.println(book.toString());
		}
	}
}
