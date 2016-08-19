package lab3;

public class Account {
	private Integer number;
	private String owner;
	private Double balance;
	private Double limit;
	
	public void printInformation() {
		System.out.println("\nAccount number: " + number);
		System.out.println("Account owner: " + owner);
		System.out.println("Account balance: " + balance);
		System.out.println("Account limit: " + limit);
	}
	
	/*
	 * Getters
	 */
	public Integer getNumber() {
		return number;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public Double getLimit() {
		return limit;
	}
	
	/*
	 * Setters
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public void setLimit(Double limit) {
		this.limit = limit;
	}
}
