

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Person {

	private String name;
	private String uid;
	private Integer returnLimit;
	private Integer loanLimit;
	protected ArrayList<Loan> loans;
	
	public Person(String name, String uid) {
		setName(name);
		setUid(uid);
		setLoans(new ArrayList<Loan>());
	}
	

	/*
	 * Getters
	 */
	public String getName() {
		return name;
	}
	public String getUid() {
		return uid;
	}
	public ArrayList<Loan> getLoans() {
		return loans;
	}
	public Integer getReturnLimit() {
		return returnLimit;
	}
	public Integer getLoanLimit() {
		return loanLimit;
	}
	
	/*
	 * Setters
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	public void setUid(String uid) {
		this.uid = uid.trim();
	}
	public void setReturnLimit(Integer returnLimit) {
		this.returnLimit = returnLimit;
	}
	public void setLoanLimit(Integer loanLimit) {
		this.loanLimit = loanLimit;
	}
	private void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}
	
	/*
	 * Override Methods
	 */

	@Override
	public String toString() {
		return "Name: " + getName() + ", UID = " + getUid();
	}
	
	public abstract String toFullString();
	
	/*
	 * Book Management Methods
	 */
	
	/**
	 * Should be called when a student wants to rent a book.
	 * @param book 	The book that the student is renting.
	 * @return true if the operation was successful, else false.
	 */
	public Boolean startLoan(Loan loan) {
		if (canBorrowBook()) {
			loan.getBook().incrementCopiesTakenCount();
			loans.add(loan);
			
			System.out.println(name.toUpperCase() + " must return this book by " + loan.getFormattedDueDate());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Should be called when the student is returning a book to the library.
	 * @param loan	The loan that is ending.
	 * @return true if the operation was successful, else false.
	 */
	public Boolean endLoan(Loan loan) {
		if (loans.contains(loan)) {
			if (loan.isOverdue()) {
				System.out.println(name.toUpperCase() + " is returning the book after the return date limit.");
			}
			loans.remove(loan);
			return true;
		} else {
			System.out.println("Error: the choosen book couldn't be found in the person's current books.");
			return false;
		}
	}
	
	private ArrayList<Loan> getLoansByBookTitle(String title) {
		ArrayList<Loan> loanList = new ArrayList<Loan>();
		for (Loan loan : loans) {
			if (loan.getBook().getTitle().contains(title)) {
				loanList.add(loan);
			}
		}
		return loanList;
	}
	
	public Loan chooseLoanByBookTitle(String title, Scanner input) throws LoanNotFoundException {
		ArrayList<Loan> possibleLoans = getLoansByBookTitle(title);
		
		if (possibleLoans.size() > 0) {
			if (possibleLoans.size() > 1) {
				System.out.println("The search brought " + possibleLoans.size() + " results.");
				for (Loan loan : possibleLoans) {
					System.out.println("Book #" + (possibleLoans.indexOf(loan)+1) + ": " + loan.getBook().toString());
				}
				System.out.println("Type the index of the book that you want to return.");
				Integer option = ManagerHelper.chooseIndex(input, possibleLoans.size());
				return possibleLoans.get(option-1);
			} else {
				System.out.println("The search brought 1 result.");
				return possibleLoans.get(0);
			}
		}
		throw new LoanNotFoundException("There are no loans that match this person and the given book title.");
	}
	
	public Integer getLoanCount() {
		return loans.size();
	}
	
	public Boolean canBorrowBook() {
		//TODO: verify if the student has any overdue book.
		if (loans.size() >= getLoanLimit()) {
			return false;
		}
		return true;
	}
}
