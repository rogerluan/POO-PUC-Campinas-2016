import java.util.Scanner;

public class Exercise3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please type a 5 figure integer: ");
		String stringNumber = input.nextLine();
		String[] arrayNumber = stringNumber.split("");
		System.out.printf("Here are your 5 figures: %s   %s   %s   %s   %s", arrayNumber[0], arrayNumber[1], arrayNumber[2], arrayNumber[3], arrayNumber[4]);
		
		input.close();
	}

}
