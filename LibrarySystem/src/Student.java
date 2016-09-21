import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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
		return "Student: " + getName() + ", RA = " + getRA();
	}
	
	public String toFullString() {
		String returnString = new String(getName().toUpperCase() + ", RA = " + getRA() + "\nCurrent Books: ");
		
		if (currentBooks.size() > 0) {
			for (Book book : currentBooks) {
				returnString = returnString + "\n - " + book.getTitle() + " - Due date: " + book.getFormattedReturnDateLimit();
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
			book.incrementCopiesTaken();
			Book bookBeingBorrowed = book.copy();
			bookBeingBorrowed.borrow();
			currentBooks.add(bookBeingBorrowed);
			
			System.out.println(name.toUpperCase() + " must return this book by " + bookBeingBorrowed.getFormattedReturnDateLimit());
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
			if (book.isOverdue()) {
				System.out.println(name.toUpperCase() + " is returning the book after the return date limit.");
			}
			currentBooks.remove(book);
			return true;
		} else {
			System.out.println("Error: the book choosen couldn't be found in the student's current books.");
			return false;
		}
	}
	
	private ArrayList<Book> getBooksByTitle(String title) {
		ArrayList<Book> bookList = new ArrayList<Book>();
		for (Book book : currentBooks) {
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
				System.out.println("Type the index of the book that you want to return, or any other key if you want to return to the main menu.");
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
	
	public Integer getBookRentalCount() {
		return currentBooks.size();
	}
	
	public Boolean canBorrowBook() {
		//to-do: verify if the student has any overdue book.
		if (currentBooks.size() >= 3) {
			return false;
		}
		return true;
	}
}
