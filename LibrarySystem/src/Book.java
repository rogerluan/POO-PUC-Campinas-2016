import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {

	private String title;
	private Date registerDate;
	private Date rentalDate;
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
		DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		return formatter.format(registerDate);
	}

	public Date getRentalDate() {
		return rentalDate;
	}
	
	public String getFormattedRentalDate() {
		DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
		return formatter.format(rentalDate);
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

	public void setAllowedRentalPeriod(Integer allowedRentalPeriod) {
		if (allowedRentalPeriod > 0) {
			this.allowedRentalPeriod = allowedRentalPeriod;
		} else {
			this.allowedRentalPeriod = 7; // default value;
		}
	}

	private void setTotalNumberOfCopies(Integer totalNumberOfCopies) {
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
				"\nBooks available: " + (getTotalNumberOfCopies()-getCopiesTaken()) + "\n";
	}

	/*
	 * Custom Methods
	 */
	public void incrementTotalNumberOfCopies() {
		setTotalNumberOfCopies(getTotalNumberOfCopies() + 1);
	}
	
	public void incrementCopiesTaken() {
		setCopiesTaken(getCopiesTaken()+1);
	}
	
	public void decrementCopiesTaken() {
		setCopiesTaken(getCopiesTaken()-1);
	}
}
