import java.util.Date;

public class Book {
	
	private String title;
	private Boolean isTaken;
	private Date registerDate;
	private Date rentalDate;
	private Integer allowedRentalPeriod;
	private Integer numberOfCopies;
	
	/*
	 * Constructor
	 */
	public Book(String title, Integer numberOfCopies) {
		super();
		setTitle(title);
		setIsTaken(false);
		setRegisterDate(new Date());
		setAllowedRentalPeriod(7);
		setNumberOfCopies(numberOfCopies);
	}

	/*
	 * Getters
	 */
	public String getTitle() {
		return title;
	}
	public Boolean getIsTaken() {
		return isTaken;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public Integer getAllowedRentalPeriod() {
		return allowedRentalPeriod;
	}
	public Integer getNumberOfCopies() {
		return numberOfCopies;
	}
	
	/*
	 * Setters
	 */
	
	public void setTitle(String title) {
		this.title = title.trim();
	}
	public void setIsTaken(Boolean isTaken) {
		this.isTaken = isTaken;
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
			this.allowedRentalPeriod = 7; //default value;
		}
	}	
	public void setNumberOfCopies(Integer numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
	@Override
	public String toString() {
		return "Book: " + numberOfCopies + " x " + title + ", register date:  " + registerDate;
	}

	/*
	 * Custom Methods
	 */
	public void incrementNumberOfCopies() {
		setNumberOfCopies(getNumberOfCopies()+1);
	}
}