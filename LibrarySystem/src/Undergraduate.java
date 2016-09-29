

public class Undergraduate extends Person {
	private final Integer returnLimit = 7;
	private final Integer loanLimit = 3;
	
	public Undergraduate(String name, String uid) {
		super(name, uid);
	}

	@Override
	public Integer getReturnLimit() {
		return returnLimit;
	}
	
	public Integer getLoanLimit() {
		return loanLimit;
	}
	
	@Override
	public String toString() {
		return "Undergraduate: " + getName() + ", UID = " + getUid();
	}
	
	public String toFullString() {
		String returnString = new String(getName().toUpperCase() + ", Undergraduate Register = " + getUid() + "\nCurrent Books: ");
		
		if (loans.size() > 0) {
			for (Loan loan : loans) {
				returnString = returnString + "\n - " + loan.getBook().getTitle() + " - Due date: " + loan.getFormattedDueDate();
			}	
		} else {
			returnString = returnString + "none.";
		}
		
		return returnString;
	}
}
