
public class Staff {
	private String firstName;
	private String lastName;
	private double salary;
	
	public Staff(String firstName, String lastName, double salary) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setSalary(salary);
	}
	
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		if (salary >= 0) {
			this.salary = salary;
		} else {
			this.salary = 0;
		}
	}
	
	public double getAnualSalary() {
		return 12*this.getSalary();
	}
	
	public void raiseSalaryByPercentage(double percentage) {
		percentage = 1 + (percentage/100);
		
		this.setSalary(this.getSalary()*percentage);
	}
}
