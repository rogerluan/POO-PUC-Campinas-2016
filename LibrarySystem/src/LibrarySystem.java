import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
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
		if (input != null) {
			System.out.println("Action: ");
			Integer intAction = input.nextInt();
			if (intAction >= 1 && intAction <= Action.values().length) {
				Action action = Action.values()[intAction-1];
				didChooseAction(action);	
			} else {
				System.out.println("Invalid action, please choose an action number between 1 and 9.");
				readOption();
			}
		} else {
			input = new Scanner(System.in);
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
				System.out.println(name.toUpperCase() + " was successfully registered.");
			} else {
				System.out.println("The given RA is already registered in our records.");
			}
		}
		showMenu();
	}

	private void actionRegisterNewBook() {
		if (bookManager != null) {
			System.out.println("Book title: ");
			String title = input.nextLine();
			System.out.println("Number of copies: ");
			Integer numberOfCopies = input.nextInt();
			
			bookManager.addBook(new Book(title, numberOfCopies));
			System.out.println(title.toUpperCase() + " was successfully registered.");
		}
		showMenu();
	}

	private void actionLendBooks() {
		
	}

	private void actionReturnBooks() {

	}

	private void actionSearchBookByTitle() {

	}

	private void actionSearchStudentByName() {

	}

	private void actionListOverdue() {

	}

	private void actionListBooks() {
		if (bookManager != null) {
			bookManager.listAllBooks();
		}
		showMenu();
	}
	
	private void actionListStudents() {
		if (studentManager != null) {
			studentManager.listAllStudents();
		}
		showMenu();
	}
	
	private void actionQuit() {
		System.out.println("Bye bye!");
		input.close();
		bookManager = null;
		studentManager = null;
	}
}