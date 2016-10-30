import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class LibrarySystem {

	private BookManager bookManager;
	private PersonManager personManager;
	private ArrayList<Loan> loans;
	private Scanner input;
	
	public enum Action {
		ActionRegisterNewPerson, ActionRegisterNewBook,
		ActionLendBooks, ActionReturnBooks,
		ActionSearchBookByTitle, ActionSearchPersonByName,
		ActionListOverdue, ActionListBooks, ActionListPeople,
		ActionQuit
	}

	public enum Role {
		RoleUndergraduate, RolePostgraduate, RoleProfessor
	}
	
	public LibrarySystem () {
		bookManager = new BookManager();
		personManager = new PersonManager();
		setLoans(new ArrayList<Loan>());
		showWelcomeMessage();
		showMenu();
	}
	
	private ArrayList<Loan> getLoans() {
		return loans;
	}
	
	private void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}
	
	public void showWelcomeMessage() {
		DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		System.out.println("Welcome to the Library System. Today is " + formatter.format(new Date()));
	}
	
	public void showMenu() {		
		System.out.println("Please choose an action below.");
		System.out.println("1 - Register new person");
		System.out.println("2 - Register new book title");
		System.out.println("3 - Lend books");
		System.out.println("4 - Return books");
		System.out.println("5 - Search book by title");
		System.out.println("6 - Search person by name");
		System.out.println("7 - List overdue books");
		System.out.println("8 - List all books registered");
		System.out.println("9 - List everyone registered");
		System.out.println("10 - Quit");
		didChooseAction(readOption());
	}
	
	private Action readOption() {
		System.out.println("Action: ");
		Integer intAction = ManagerHelper.chooseIndex(input, Action.values().length);
		return Action.values()[intAction-1];
	}
	
	private Role readRole() {
		System.out.println("Role: ");
		Integer intRole = ManagerHelper.chooseIndex(input, Role.values().length);
		return Role.values()[intRole-1];
	}
	
	private void didChooseAction(Action action) {
		switch (action) {
		case ActionRegisterNewPerson:
			actionRegisterNewPerson();
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
		case ActionSearchPersonByName:
			actionSearchPersonByName();
			break;
		case ActionListOverdue:
			actionListOverdue();
			break;
		case ActionListBooks:
			actionListBooks();
			break;
		case ActionListPeople:
			actionListPeople();
			break;
		case ActionQuit:
			actionQuit();
		}
	}
	
	/*
	 * Actions
	 */

	private void actionRegisterNewPerson() {
		if (personManager == null) {
			personManager = new PersonManager();
		}		
		System.out.println("Which role does this person belong to? \n1 - Undergraduate\n2 - Postgraduate\n3 - Professor");
		Role role = readRole();
		
		Person newPerson = null;
		
		input = new Scanner(System.in);

		switch (role) {
		case RoleUndergraduate: {
			System.out.println("Undergraduate name: ");
			String name = input.nextLine();
			System.out.println("Undergraduate RA: ");
			String RA = input.nextLine();
			newPerson = new Undergraduate(name, RA);
			break;
		}
		case RolePostgraduate: {
			System.out.println("Postgraduate name: ");
			String name = input.nextLine();
			System.out.println("Postgraduate RA: ");
			String RA = input.nextLine();
			newPerson = new Postgraduate(name, RA);
			break;
		}
		case RoleProfessor: {
			System.out.println("Professor name: ");
			String name = input.nextLine();
			System.out.println("Professor ID: ");
			String uid = input.nextLine();
			newPerson = new Professor(name, uid);
			break;
		}
		}
		
		if (newPerson != null) {
			try {
				personManager.addPerson(newPerson);
				System.out.println(newPerson.getName().toUpperCase() + " was successfully registered.\n");
			} catch (PersonException e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.err.println("An unknown error occurred: the role read could not be parsed.");
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
		if (personManager != null || bookManager != null) {
			if (personManager.getPersons().size() > 0) {
				if (bookManager.getBooks().size() > 0) {
					input = new Scanner(System.in);
					System.out.println("Type the unique identifier of the person that wants to borrow books: ");
					Person requestingPerson = requestPersonByUid(input);
					if (requestingPerson.canBorrowBook()) {
						System.out.println("Title of the book to be borrowed: ");
						Book choosenBook = requestBook(input);
						System.out.println(choosenBook.toFullString());
						if (choosenBook.countAvailableCopies() > 0) {
							Loan newLoan = new Loan(choosenBook, requestingPerson);
							if (requestingPerson.startLoan(newLoan)) {
								getLoans().add(newLoan);
								System.out.println("The book '" + choosenBook.getTitle() + "' was successfully lent to " + requestingPerson.getName());
							} else {
								System.err.println("This person can't borrow any more books.");
							}
						} else {
							System.err.println("There isn't a copy of this book available for borrow at the moment. Please try again later.");
						}
					} else {
						//TODO: if (requestingPerson.hasOverdueBooks) 
						System.err.println("This person can't borrow any more books.");
					}

				} else {
					System.err.println("No book records found.");
				}
			} else {
				System.err.println("No person records found.");
			}
		}
		showMenu();
	}

	private void actionReturnBooks() {
		if (personManager != null || bookManager != null) {
			if (personManager.getPersons().size() > 0) {
				if (bookManager.getBooks().size() > 0) {
					System.out.println("Type the unique identifier of the person that is returning the book: ");
					Person requestingPerson = requestPersonByUid(input);
					if (requestingPerson.getLoans().size() > 0) {
						System.out.println("Title of the book that is being returned: ");
						String title = input.nextLine();
						try {
							Loan endingLoan = requestingPerson.chooseLoanByBookTitle(title, input);
							getLoans().remove(endingLoan);
							requestingPerson.endLoan(endingLoan);
							endingLoan.getBook().decrementCopiesTakenCount();
							System.out.println(requestingPerson.getName() + " successfully returned the book " + endingLoan.getBook().getTitle());
						} catch (LoanNotFoundException e) {
							System.err.println(e.getMessage());
						} catch (Exception e) {
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
					} else {
						System.err.println("This person doesn't have any books in loan.\n");
					}
				} else {
					System.err.println("No book records found.");
				}
			} else {
				System.err.println("No student records found.");
			}
		}
		showMenu();
	}

	private void actionSearchBookByTitle() {
		if (bookManager != null) {
			if (bookManager.getBooks().size() > 0) {
				System.out.println("Title of the book: ");
				Book choosenBook = requestBook(input); 
				System.out.println(choosenBook.toFullString());
			} else {
				System.out.println("No book records found.");
			}
		}
		showMenu();
	}

	private void actionSearchPersonByName() {
		if (personManager != null) {
			if (personManager.getPersons().size() > 0) {
				System.out.println("Name of the person: ");
				Person choosenPerson = requestPersonByName(input);
				System.out.println(choosenPerson.toFullString());
			} else {
				System.out.println("No person records found.");
			}
		}
		showMenu();
	}

	private void actionListOverdue() {
		String noOverdueBooksMessage = "There're no overdue books.\n";
		if (bookManager != null && personManager != null) {
			ArrayList<Person> peopleList = personManager.getPersons();
			if (peopleList.size() > 0) {
				for (Person person : peopleList) {
					ArrayList<Loan> loanList = person.getLoans();
					for (Loan loan : loanList) {
						if (loan.isOverdue()) {
							noOverdueBooksMessage = "";
							System.out.println(person.getName().toUpperCase() + " has over due books: \n -> " + loan.getBook().getTitle() + ", Due date: " + loan.getFormattedDueDate());
						}
					}
				}
				System.out.println(noOverdueBooksMessage);
			} else {
				System.out.println("No student records found.\n");
			}
		}
		showMenu();
	}

	private void actionListBooks() {
		if (bookManager != null) {
			try {
				bookManager.listAllBooks();
			} catch (BookNotFoundException e) {
				System.err.println(e.getMessage());
			}
			System.out.println();
		}
		showMenu();
	}
	
	private void actionListPeople() {
		if (personManager != null) {
			try {
				personManager.listEveryone();
			} catch (PersonNotFoundException e) {
				System.err.println(e.getMessage());
			}
			System.out.println();
		}
		showMenu();
	}
	
	private void actionQuit() {
		System.out.println("Exiting program...");
		input.close();
		bookManager = null;
		personManager = null;
		loans = null;
	}
	
	/**
	 * Helpers
	 */
	@SuppressWarnings("resource")
	private Person requestPersonByUid(Scanner input) {
		input = new Scanner(System.in);
		try {
			String uid = input.nextLine();
			return personManager.getPersonByUid(uid);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println("Please try again.\nUnique Identifier: ");
			return requestPersonByUid(input);
		}
	}
	
	private Person requestPersonByName(Scanner input) {
		input = new Scanner(System.in);
		try {
			String name = input.nextLine();
			return personManager.choosePersonByName(name, input);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println("Please try again.\nName: ");
			return requestPersonByName(input);
		}
	}

	private Book requestBook(Scanner input) {
		input = new Scanner(System.in);
		try {
			String title = input.nextLine();
			return bookManager.chooseBookByTitle(title, input);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return requestBook(input);
		}
	}
}
