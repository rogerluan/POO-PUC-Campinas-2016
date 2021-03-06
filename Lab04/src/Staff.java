
public class Staff {
	private String firstName;
	private String lastName;
	private double hourlyRate;
	private double hoursPerWeek;
	
	public Staff(String firstName, String lastName, double hourlyRate, double hoursPerWeek) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setHourlyRate(hourlyRate);
		this.setHoursPerWeek(hoursPerWeek);
	}
	
	/*
	 * Getters & Setters
	 */
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double hourlyRate) {
		if (hourlyRate >= 0) {
			this.hourlyRate = hourlyRate;
		} else {
			this.hourlyRate = 0;
		}
	}
	public double getHoursPerWeek() {
		return hoursPerWeek;
	}
	public void setHoursPerWeek(double hoursPerWeek) {
		if (hoursPerWeek >= 0) {
			this.hoursPerWeek = hoursPerWeek;
		} else {
			this.hoursPerWeek = 0;
		}
	}
	
	/*
	 * Public Methods
	 */
	public double getWeeklySalary() {
		return this.getHourlyRate()*this.getHoursPerWeek();
	}
	public double getAnualSalary() {
		return 52*this.getWeeklySalary();
	}
	public double getMonthlySalary() {
		return 4*this.getWeeklySalary();
	}
	public void raiseSalaryByPercentage(double percentage) {
		percentage = 1 + (percentage/100);	
		this.setHourlyRate(this.getHourlyRate()*percentage);
	}
}
