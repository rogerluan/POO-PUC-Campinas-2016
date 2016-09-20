import java.util.ArrayList;

public class Student {
	private String name;
	private String RA;
	private ArrayList<Book> currentBooks;
	
	/**
	 * Constructor 	method
	 * @param name 	The name of the student
	 * @param rA 	The student unique identifier
	 */
	public Student(String name, String RA) {
		super();
		setName(name);;
		setRA(RA);
		setCurrentBooks(new ArrayList<Book>());
	}
	
	/*
	 * Getters
	 */
	public String getName() {
		return name;
	}
	public String getRA() {
		return RA;
	}
	public ArrayList<Book> getCurrentBooks() {
		return currentBooks;
	}

	/*
	 * Setters
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	public void setRA(String RA) {
		this.RA = RA.trim();
	}
	public void setCurrentBooks(ArrayList<Book> currentBooks) {
		this.currentBooks = currentBooks;
	}
	
	/*
	 * Override Methods
	 */

	@Override
	public String toString() {
		String returnString = new String("Student: " + name + ", RA = " + RA + "\nCurrent Books: ");
		
		if (currentBooks.size() > 0) {
			for (Book book : currentBooks) {
				returnString = returnString + "\n - " + book.getTitle();
			}	
		} else {
			returnString = returnString + "none.";
		}
		
		return returnString;
	}
	
	/*
	 * Book Management Methods
	 */
	
	/**
	 * Should be called when a student wants to rent a book.
	 * @param book 	The book that the student is renting.
	 * @return true if the operation was successful, else false.
	 */
	public Boolean rentBook(Book book) {
		if (currentBooks.size() >= 3) {
			return false;
		} else {
			book.setRentalDateToNow();
			currentBooks.add(book);
			return true;
		}
	}

	/**
	 * Should be called when the student is returning a book to the library.
	 * @param book	The book that is being returned.
	 * @return true if the operation was successful, else false.
	 */
	public Boolean returnBook(Book book) {
		if (currentBooks.contains(book)) {
			currentBooks.remove(book);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Should be called when the student is returning a title to the library.
	 * @param title	The title of the book that is being returned.
	 */
	public Boolean returnBookByTitle(String title) {
		for (Book book : currentBooks) {
			if (book.getTitle().equals(title.trim())) {
				return returnBook(book);
			}
		}
		return false;
	}
	
	public Integer getBookRentalCount() {
		return currentBooks.size();
	}
}
