import java.util.Scanner;

public class StaffTest {

	public static void main(String[] args) {
		Staff staff1 = receiveStaff();
		Staff staff2 = receiveStaff();
		
		System.out.println("Salário anual do primeiro funcionário: " + staff1.getAnualSalary());
		System.out.println("Salário anual do segundo funcionário: " + staff2.getAnualSalary());
		
		System.out.println("Aumento percentual do salário dos funcionários: ");
		Scanner input = new Scanner(System.in);
		
		double percentage = input.nextDouble();
		
		staff1.raiseSalaryByPercentage(percentage);
		staff2.raiseSalaryByPercentage(percentage);
		
		System.out.println("Novo salário anual do primeiro funcionário: " + staff1.getAnualSalary());
		System.out.println("Novo salário anual do segundo funcionário: " + staff2.getAnualSalary());
		
		input.close();
	}
	
	private static Staff receiveStaff() {
		System.out.println("Digite o primeiro nome: ");
		
		Scanner input = new Scanner(System.in);
		//variable leak here, since we're not closing Scanner.
		//it cannot be closed since it's inside a static method.
		
		String firstName = input.nextLine(); 
//		input.nextLine(); //needed to read the next line.
		
		System.out.println("Sobrenome: ");
		String lastName = input.nextLine();
		
		System.out.println("Salário mensal: ");
		double salary = input.nextDouble();

		return new Staff(firstName, lastName, salary);
	}

}
