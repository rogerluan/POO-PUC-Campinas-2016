import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book {

	private final DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
	
	private String title;
	private Date registerDate;
	private Date rentalDate;
	private Date returnDateLimit;
	private Integer allowedRentalPeriod;
	private Integer totalNumberOfCopies;
	private Integer copiesTaken;
	
	/*
	 * Constructor
	 */
	public Book(String title, Integer totalNumberOfCopies) {
		super();
		setTitle(title);
		setRegisterDate(new Date());
		setAllowedRentalPeriod(7);
		setTotalNumberOfCopies(totalNumberOfCopies);
		setCopiesTaken(0);
	}
	
	public Book copy() {
		Book newBook = new Book(getTitle(), 1);
		newBook.setCopiesTaken(1);
		return newBook;
	}

	/*
	 * Getters
	 */
	public String getTitle() {
		return title;
	}

	public Date getRegisterDate() {
		return registerDate;
	}
	
	public String getFormattedRegisterDate() {
		return formatter.format(registerDate);
	}

	public Date getRentalDate() {
		return rentalDate;
	}
	
	public String getFormattedRentalDate() {
		return formatter.format(rentalDate);
	}
	
	public Date getReturnDateLimit() {
		return returnDateLimit;
	}
	
	public String getFormattedReturnDateLimit() {
		return formatter.format(returnDateLimit);
	}

	public Integer getAllowedRentalPeriod() {
		return allowedRentalPeriod;
	}

	public Integer getTotalNumberOfCopies() {
		return totalNumberOfCopies;
	}
	public Integer getCopiesTaken() {
		return copiesTaken;
	}

	/*
	 * Setters
	 */

	public void setTitle(String title) {
		this.title = title.trim();
	}

	private void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public void setRentalDateToNow() {
		this.rentalDate = new Date();
	}
	
	public void setReturnDateLimit(Date returnDateLimit) {
		this.returnDateLimit = returnDateLimit;
	}

	public void setAllowedRentalPeriod(Integer allowedRentalPeriod) {
		if (allowedRentalPeriod > 0) {
			this.allowedRentalPeriod = allowedRentalPeriod;
		} else {
			this.allowedRentalPeriod = 7; // default value;
		}
	}

	public void setTotalNumberOfCopies(Integer totalNumberOfCopies) {
		this.totalNumberOfCopies = totalNumberOfCopies;
	}
	
	private void setCopiesTaken(Integer copiesTaken) {
		this.copiesTaken = copiesTaken;
	}

	@Override
	public String toString() {
		return "" + totalNumberOfCopies + " x " + title + ", register date: " + getFormattedRegisterDate();
	}
	public String toFullString() {
		return "Book title: " + title + 
				"\nRegistered on: " + getFormattedRegisterDate() +
				"\nLibrary owns: " + getTotalNumberOfCopies() + " copies" +
				"\nBooks available: " + (countAvailableCopies()) + "\n";
	}

	/*
	 * Custom Methods
	 */
	public void incrementCopiesTaken() {
		setCopiesTaken(getCopiesTaken()+1);
	}
	
	public void decrementCopiesTaken() {
		setCopiesTaken(getCopiesTaken()-1);
	}
	
	public Integer countAvailableCopies() {
		return getTotalNumberOfCopies() - getCopiesTaken();
	}
	
	/**
	 * Method that should be called by a copy reference of the book that is being borrowed.
	 */
	public void borrow() {
		setRentalDateToNow();
		
		// adds allowed rental period to the rental date so we have the return date
		Calendar calendarHelper = Calendar.getInstance(); 
		calendarHelper.setTime(getRentalDate()); 
		calendarHelper.add(Calendar.DATE, getAllowedRentalPeriod());

		setReturnDateLimit(calendarHelper.getTime());
	}
}
