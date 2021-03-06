import java.util.Scanner;
import java.util.Vector;

public class Helper {

	public static Staff receiveStaff() {
		System.out.println("Digite o primeiro nome: ");
		
		Scanner input = new Scanner(System.in);
		
		String firstName = input.nextLine(); 
//		input.nextLine(); //needed to read the next line.
		
		System.out.println("Sobrenome: ");
		String lastName = input.nextLine();
		
		System.out.println("Valor por hora de trabalho: ");
		double hourlyRate = input.nextDouble();
		
		System.out.println("Horas trabalhadas por semana: ");
		double hoursPerWeek = input.nextDouble();

		return new Staff(firstName, lastName, hourlyRate, hoursPerWeek);
	}
	
	public static void printMonthlySalary(Vector<Staff> staffList) {
		for (Staff staff : staffList) {
			System.out.println("\nNome do funcionário: " + staff.getFirstName() + " " + staff.getLastName());
			System.out.println("Salário mensal: " + staff.getMonthlySalary());
		}
	}
}
