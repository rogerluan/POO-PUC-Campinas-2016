import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LibrarySystem {

	private BookManager bookManager;
	private StudentManager studentManager;
	private Scanner input;
	
	public enum Action {
		ActionRegisterNewStudent, ActionRegisterNewBook,
		ActionLendBooks, ActionReturnBooks,
		ActionSearchBookByTitle, ActionSearchStudentByName,
		ActionListOverdue, ActionListBooks, ActionListStudents,
		ActionQuit
	}
	
	public LibrarySystem () {
		bookManager = new BookManager();
		studentManager = new StudentManager();
		showWelcomeMessage();
		showMenu();
	}
	
	public void showWelcomeMessage() {
		DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		System.out.println("Welcome to the Library System. Today is " + formatter.format(new Date()));
	}
	
	public void showMenu() {
		System.out.println("Please choose an action below.");
		System.out.println("1 - Register new student");
		System.out.println("2 - Register new book title");
		System.out.println("3 - Lend books");
		System.out.println("4 - Return books");
		System.out.println("5 - Search book by title");
		System.out.println("6 - Search student by name");
		System.out.println("7 - List overdue books");
		System.out.println("8 - List all books registered");
		System.out.println("9 - List all students registered");
		System.out.println("10 - Quit");
		readOption();
	}
	
	private void readOption() {
		System.out.println("Action: ");
		input = new Scanner(System.in);
		Integer intAction = null;
		try {
			intAction = input.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid action, please choose an action number between 1 and 9.");
			readOption();
		}		
		if (intAction >= 1 && intAction <= Action.values().length) {
			Action action = Action.values()[intAction-1];
			didChooseAction(action);	
		} else {
			System.out.println("Invalid action, please choose an action number between 1 and 9.");
			readOption();
		}
	}
	
	private void didChooseAction(Action action) {
		
		switch (action) {
		case ActionRegisterNewStudent:
			actionRegisterNewStudent();
			break;
		case ActionRegisterNewBook:
			actionRegisterNewBook();
			break;
		case ActionLendBooks:
			actionLendBooks();
			break;
		case ActionReturnBooks:
			actionReturnBooks();
			break;
		case ActionSearchBookByTitle:
			actionSearchBookByTitle();
			break;
		case ActionSearchStudentByName:
			actionSearchStudentByName();
			break;
		case ActionListOverdue:
			actionListOverdue();
			break;
		case ActionListBooks:
			actionListBooks();
			break;
		case ActionListStudents:
			actionListStudents();
			break;
		case ActionQuit:
			actionQuit();
		}
	}
	
	/*
	 * Actions
	 */

	private void actionRegisterNewStudent() {
		if (studentManager != null) {
			input = new Scanner(System.in);
			System.out.println("Student name: ");
			String name = input.nextLine();
			System.out.println("Student RA: ");
			String RA = input.nextLine();
			
			if (studentManager.addStudent(new Student(name, RA))) {
				System.out.println(name.toUpperCase() + " was successfully registered.\n");
			} else {
				System.out.println("The given RA is already registered in our records.\n");
			}
		}
		showMenu();
	}

	private void actionRegisterNewBook() {
		if (bookManager != null) {
			input = new Scanner(System.in);
			System.out.println("Book title: ");
			String title = input.nextLine();
			System.out.println("Number of copies: ");
			Integer numberOfCopies = input.nextInt();
			
			bookManager.addBook(new Book(title, numberOfCopies));
			System.out.println(title.toUpperCase() + " was successfully registered.\n");
		}
		showMenu();
	}

	private void actionLendBooks() {
		if (studentManager != null && bookManager != null) {
			if (studentManager.getStudents().size() > 0) {
				if (bookManager.getBooks().size() > 0) {
					input = new Scanner(System.in);
					System.out.println("Type the RA of the student that wants to borrow books: ");
					String requestingRA = input.nextLine();

					Student requestingStudent = studentManager.getStudentByRA(requestingRA);
					if (requestingStudent != null) {
						if (requestingStudent.canBorrowBook()) {
							System.out.println("Title of the book to be borrowed: ");
							String title = input.nextLine();
							Book choosenBook = bookManager.chooseBookByTitle(title, input);
							System.out.println(choosenBook.toFullString());
							if (choosenBook.countAvailableCopies() > 0) {
								if (requestingStudent.rentBook(choosenBook)) {
									System.out.println("The book '" + choosenBook.getTitle() + "' was successfully lent to " + requestingStudent.getName());
								} else {
									System.out.println("This student can't borrow any more books.");
								}
							} else {
								System.out.println("There isn't a copy of this book available for borrow at the moment. Please try again later, or choose another book.");
							}
						} else {
							//TODO: if (requestingStudent.hasOverdueBooks) 
							System.out.println("This student can't borrow any more books.");
						}
					} else {
						System.out.println("We couldn't find a student with the given RA.");
					}
				} else {
					System.out.println("No book records found.");
				}
			} else {
				System.out.println("No student records found.");
			}
		}
		showMenu();
	}

	private void actionReturnBooks() {
		if (studentManager != null && bookManager != null) {
			if (studentManager.getStudents().size() > 0) {
				if (bookManager.getBooks().size() > 0) {
					input = new Scanner(System.in);
					System.out.println("Type the RA of the student that is returning the book: ");
					String requestingRA = input.nextLine();

					Student requestingStudent = studentManager.getStudentByRA(requestingRA);
					if (requestingStudent != null) {
						if (requestingStudent.getCurrentBooks().size() > 0) {
							System.out.println("Title of the book that is being returned: ");
							String title = input.nextLine();
							Book returningBook = requestingStudent.chooseBookByTitle(title, input);

							if (returningBook != null) {
								requestingStudent.returnBook(returningBook);
								Book bookReference = bookManager.getBookByTitle(returningBook.getTitle());
								if (bookReference != null) {
									bookReference.decrementCopiesTaken();
									System.out.println(requestingStudent.getName() + " successfully returned the book " + returningBook.getTitle());
								} else {
									System.out.println("Error: couldn't find a reference of the returning book in the Library.");
								}
							} else {
								System.out.println("Operation aborted, returning back to the main menu.\n");
							}
						} else {
							System.out.println("This student doesn't have any books in loan.\n");
						}
					} else {
						System.out.println("We couldn't find a student with the given RA.\n");
					}
				} else {
					System.out.println("No book records found.");
				}
			} else {
				System.out.println("No student records found.");
			}
		}
		showMenu();
	}

	private void actionSearchBookByTitle() {
		if (bookManager != null) {
			if (bookManager.getBooks().size() > 0) {
				input = new Scanner(System.in);
				System.out.println("Title of the book: ");
				String title = input.nextLine();
				Book choosenBook = bookManager.chooseBookByTitle(title, input); 
				if (choosenBook != null) {
					System.out.println(choosenBook.toFullString());
				} else {
					if (bookManager.getBooksByTitle(title).size() > 0) {
						System.out.println("Returning to the main menu.\n");
					} else {
						System.out.println("No books could be found with the given title.");
					}
				}
			} else {
				System.out.println("No book records found.");
			}
		}
		showMenu();
	}

	private void actionSearchStudentByName() {
		if (studentManager != null) {
			if (studentManager.getStudents().size() > 0) {
				input = new Scanner(System.in);
				System.out.println("Name of the student: ");
				String title = input.nextLine();
				Student choosenStudent = studentManager.chooseStudentByName(title, input); 
				if (choosenStudent != null) {
					System.out.println(choosenStudent.toFullString());
				} else {
					if (studentManager.getStudentsByName(title).size() > 0) { //means the chooseStudentByName method returned nil due to invalid command
						System.out.println("Returning to the main menu.\n");
					} else {
						System.out.println("No students could be found with the given title.");
					}
				}
			} else {
				System.out.println("No student records found.");
			}
		}
		showMenu();
	}

	private void actionListOverdue() {
		if (bookManager != null && studentManager != null) {
			ArrayList<Student> studentsList = studentManager.getStudents();
			if (studentsList.size() > 0) {
				for (Student student : studentsList) {
					ArrayList<Book> booksList = student.getCurrentBooks();
					for (Book book : booksList) {
						if (book.isOverdue()) {
							System.out.println(student.getName().toUpperCase() + " has over due books: \n -> " + book.getTitle() + ", Due date: " + book.getFormattedReturnDateLimit());
						}
					}
				}
			} else {
				System.out.println("No student records found.");
			}
		}
		showMenu();
	}

	private void actionListBooks() {
		if (bookManager != null) {
			bookManager.listAllBooks();
			System.out.println();
		}
		showMenu();
	}
	
	private void actionListStudents() {
		if (studentManager != null) {
			studentManager.listAllStudents();
			System.out.println();
		}
		showMenu();
	}
	
	private void actionQuit() {
		System.out.println("Exiting program...");
		input.close();
		bookManager = null;
		studentManager = null;
	}
}
