import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Loan {
	private Book book;
	private Person person;
	private Date rentalDate;
	private Date dueDate;
	
	private final DateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
	
	public Loan(Book book, Person person) {
		setBook(book);
		setPerson(person);
		setRentalDate(new Date());
		
		// adds allowed rental period to the rental date so we have the return date
		Calendar calendarHelper = Calendar.getInstance(); 
		calendarHelper.setTime(getRentalDate()); 
		calendarHelper.add(Calendar.DATE, person.getReturnLimit());

		setDueDate(calendarHelper.getTime());
	}

	/*
	 * Getters
	 */
	public Book getBook() {
		return book;
	}
	public Person getPerson() {
		return person;
	}
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
	public void setBook(Book book) {
		this.book = book;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	/*
	 * Custom Public Methods
	 */
	public Boolean isOverdue() {
		if (new Date().after(getDueDate())) {
			return true;
		}
		return false;
	}
}
