import java.util.Scanner;
import java.util.Vector;

public class StaffTest {

	/*
	 * Exercise 02
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String operator = new String("s");
		
		Vector<Staff> staffList = new Vector<Staff>();
		while (operator.equals("s")) {
			staffList.add(Helper.receiveStaff());
			
			System.out.println("Deseja adicionar mais funcionários?(s/n): ");
			operator = input.nextLine();
		}
		
		Helper.printMonthlySalary(staffList);
		input.close();
	}
}