import java.util.Calendar;
import java.util.Date;

public class StudentBook extends Book {

	private Date rentalDate;
	private Date dueDate;
	
	public StudentBook(String title, Integer allowedRentalPeriod) {
		super(title);
		setRentalDate(new Date());

		// adds allowed rental period to the rental date so we have the return date
		Calendar calendarHelper = Calendar.getInstance(); 
		calendarHelper.setTime(getRentalDate()); 
		calendarHelper.add(Calendar.DATE, allowedRentalPeriod);

		setDueDate(calendarHelper.getTime());
	}
	
	/*
	 * Getters
	 */
	public Date getRentalDate() {
		return rentalDate;
	}
	public String getFormattedRentalDate() {
		return formatter.format(rentalDate);
	}
	public Date getDueDate() {
		return dueDate;
	}
	public String getFormattedDueDate() {
		return formatter.format(dueDate);
	}
	
	/*
	 * Setters
	 */
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	/*
	 * Public Methods
	 */
	public Boolean isOverdue() {
		if (new Date().after(getDueDate())) {
			return true;
		}
		return false;
	}

}
