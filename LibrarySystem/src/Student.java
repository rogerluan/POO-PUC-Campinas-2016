import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Student {
	private String name;
	private String RA;
	private ArrayList<StudentBook> currentBooks;
	
	/**
	 * Constructor 	method
	 * @param name 	The name of the student
	 * @param rA 	The student unique identifier
	 */
	public Student(String name, String RA) {
		super();
		setName(name);;
		setRA(RA);
		setCurrentBooks(new ArrayList<StudentBook>());
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
	public ArrayList<StudentBook> getCurrentBooks() {
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
	public void setCurrentBooks(ArrayList<StudentBook> currentBooks) {
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
			for (StudentBook book : currentBooks) {
				returnString = returnString + "\n - " + book.getTitle() + " - Due date: " + book.getFormattedDueDate();
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
	public Boolean rentBook(LibraryBook book) {
		if (currentBooks.size() >= 3) {
			return false;
		} else {
			book.incrementCopiesTakenCount();
			StudentBook bookBeingBorrowed = new StudentBook(book.getTitle(), book.getAllowedRentalPeriod());
			currentBooks.add(bookBeingBorrowed);
			
			System.out.println(name.toUpperCase() + " must return this book by " + bookBeingBorrowed.getFormattedDueDate());
			return true;
		}
	}

	/**
	 * Should be called when the student is returning a book to the library.
	 * @param book	The book that is being returned.
	 * @return true if the operation was successful, else false.
	 */
	public Boolean returnBook(StudentBook book) {
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
	
	private ArrayList<StudentBook> getBooksByTitle(String title) {
		ArrayList<StudentBook> bookList = new ArrayList<StudentBook>();
		for (StudentBook book : currentBooks) {
			if (book.getTitle().contains(title)) {
				bookList.add(book);
			}
		}
		return bookList;
	}
	
	public StudentBook chooseBookByTitle(String title, Scanner input) {
		
		ArrayList<StudentBook> possibleBooks = getBooksByTitle(title);
		StudentBook choosenBook = null;
		
		if (possibleBooks.size() > 0) {
			if (possibleBooks.size() > 1) {
				System.out.println("The search brought " + possibleBooks.size() + " results.");
				for (StudentBook book : possibleBooks) {
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
		//TODO: verify if the student has any overdue book.
		if (currentBooks.size() >= 3) {
			return false;
		}
		return true;
	}
}
