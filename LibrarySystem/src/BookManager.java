import java.util.ArrayList;
import java.util.Scanner;

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
				book.incrementTotalNumberOfCopies();
			}
		}
		books.add(newBook);
	}	
	
	public void listAllBooks() {
		if (books.size() > 0) {
			for (Book book : books) {
				System.out.println(book.toString());
			}
		} else {
			System.out.println("There're no books registered.\n");
		}
	}
	
	public ArrayList<Book> getBooksByTitle(String title) {
		ArrayList<Book> bookList = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getTitle().contains(title)) {
				bookList.add(book);
			}
		}
		return bookList;
	}
	
	public Book chooseBookByTitle(String title, Scanner input) {
		ArrayList<Book> possibleBooks = getBooksByTitle(title);
		
		Book choosenBook = null;
		
		if (possibleBooks.size() > 0) {
			if (possibleBooks.size() > 1) {
				System.out.println("The search brought " + possibleBooks.size() + " results.");
				for (Book book : possibleBooks) {
					System.out.println("Book #" + (possibleBooks.indexOf(book)+1) + ": " + book.toString());
				}
				System.out.println("Type the index of the book that you want to see more detailed information, or any other key if you want to return to the main menu.");
				
				try {
					input = new Scanner(System.in);
					Integer option = input.nextInt();
					if (option >= 1 && option <= possibleBooks.size()) {
						choosenBook = possibleBooks.get(option-1);
					}
				} catch (Exception e) {
					//do nothing, just let the program continue to the main menu
					return null;
				}
			} else {
				System.out.println("The search brought 1 result.");
				choosenBook = possibleBooks.get(0);
			}
		}
		return choosenBook;
	}
}
