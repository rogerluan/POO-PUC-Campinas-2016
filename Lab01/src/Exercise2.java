import java.util.Scanner;

public class Exercise2 {

	private static Scanner input;
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		
		while(true) {
			System.out.println("Please type an integer: ");
			Integer number = input.nextInt();
			
			if (number%2 == 0) {
				System.out.println("This number is even!");
			} else {
				System.out.println("This number is odd!");
			}
		}
	}

}
