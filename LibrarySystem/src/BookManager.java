import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {

	private ArrayList<Book> books;
	
	public BookManager() {
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
				book.setCopiesCount(book.getCopiesCount() + newBook.getCopiesCount());
				return;
			}
		}
		books.add(newBook);
	}	
	
	public void listAllBooks() throws BookNotFoundException {
		if (books.size() > 0) {
			for (Book book : books) {
				System.out.println(book.toString());
			}
		} else {
			throw new BookNotFoundException("There're no books registered.");
		}
	}
	
	public ArrayList<Book> getBooksByTitle(String title) throws BookNotFoundException {
		ArrayList<Book> bookList = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getTitle().contains(title)) {
				bookList.add(book);
			}
		}
		if (bookList.size() == 0) {
			throw new BookNotFoundException("No books found with the given title.");
		}
		return bookList;
	}

	public Book getBookByTitle(String title) throws BookNotFoundException{
		for (Book book : books) {
			if (book.getTitle().equals(title)) {
				return book;
			}
		}
		throw new BookNotFoundException("No books found with the given title.");
	}
	
	public Book chooseBookByTitle(String title, Scanner input) throws BookNotFoundException {
		ArrayList<Book> possibleBooks = getBooksByTitle(title);

		if (possibleBooks.size() > 1) {
			System.out.println("The search brought " + possibleBooks.size() + " results.");
			for (Book book : possibleBooks) {
				System.out.println("Book #" + (possibleBooks.indexOf(book)+1) + ": " + book.toString());
			}
			System.out.println("Type the index of the book that you want to see more detailed information.");
			Integer option = ManagerHelper.chooseIndex(input, possibleBooks.size());
			return possibleBooks.get(option-1);
		} else {
			System.out.println("The search brought 1 result.");
			return possibleBooks.get(0);
		}
	}
}
