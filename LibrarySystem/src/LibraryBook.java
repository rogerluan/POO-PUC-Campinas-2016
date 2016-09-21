import java.util.Date;

public class LibraryBook extends Book {

	private Date registerDate;
	private Integer allowedRentalPeriod;
	private Integer copiesCount;
	private Integer copiesTakenCount;
	
	public LibraryBook(String title, Integer copiesCount) {
		super(title);
		setRegisterDate(new Date());
		setAllowedRentalPeriod(7);
		setCopiesCount(copiesCount);
		setCopiesTakenCount(0);
	}
	
	/*
	 * Getters
	 */
	
	public Date getRegisterDate() {
		return registerDate;
	}	
	public String getFormattedRegisterDate() {
		return formatter.format(registerDate);
	}
	public Integer getAllowedRentalPeriod() {
		return allowedRentalPeriod;
	}
	public Integer getCopiesCount() {
		return copiesCount;
	}
	public Integer getCopiesTakenCount() {
		return copiesTakenCount;
	}

	/*
	 * Setters
	 */
	private void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	private void setAllowedRentalPeriod(Integer allowedRentalPeriod) {
		if (allowedRentalPeriod > 0) {
			this.allowedRentalPeriod = allowedRentalPeriod;
		} else {
			this.allowedRentalPeriod = 7; // default value;
		}
	}
	public void setCopiesCount(Integer copiesCount) {
		this.copiesCount = copiesCount;
	}
	private void setCopiesTakenCount(Integer copiesTakenCount) {
		this.copiesTakenCount = copiesTakenCount;
	}
	
	/*
	 * Public Methods
	 */
	
	public void incrementCopiesTakenCount() {
		setCopiesTakenCount(getCopiesTakenCount()+1);
	}
	
	public void decrementCopiesTakenCount() {
		setCopiesTakenCount(getCopiesTakenCount()-1);
	}
	
	public Integer countAvailableCopies() {
		return getCopiesCount() - getCopiesTakenCount();
	}
	
	/*
	 * Override
	 */	
	@Override
	public String toString() {
		return "" + getCopiesCount() + " x " + getTitle() + ", register date: " + getFormattedRegisterDate();
	}
	public String toFullString() {
		return "Book title: " + getTitle() + 
				"\nRegistered on: " + getFormattedRegisterDate() +
				"\nLibrary owns: " + getCopiesCount() + " copies" +
				"\nBooks available: " + (countAvailableCopies()) + "\n";
	}
}
