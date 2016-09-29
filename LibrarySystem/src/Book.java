import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	private String title;
	private Date registerDate;
	private Integer copiesCount;
	private Integer copiesTakenCount;
	
	private final DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
	
	public Book(String title, Integer copiesCount) {
		setTitle(title);
		setRegisterDate(new Date());
		setCopiesCount(copiesCount);
		setCopiesTakenCount(0);
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
	public Integer getCopiesCount() {
		return copiesCount;
	}
	public Integer getCopiesTakenCount() {
		return copiesTakenCount;
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
	public void setCopiesCount(Integer copiesCount) {
		this.copiesCount = copiesCount;
	}
	private void setCopiesTakenCount(Integer copiesTakenCount) {
		this.copiesTakenCount = copiesTakenCount;
	}
	
	/*
	 * Custom Public Methods
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