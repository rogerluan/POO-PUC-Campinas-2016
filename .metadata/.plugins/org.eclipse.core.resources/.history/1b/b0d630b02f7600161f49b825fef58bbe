import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise1 {

	public static void main(String[] args) {
		ArrayList<Integer> grades = new ArrayList<Integer>(); 
		
		Scanner input = new Scanner(System.in);
		
		for (int i=1 ; i <= 10 ; i++ ) {
			if (i > 3) { //4+
				System.out.printf("Final grade of the %dth student is: ", i);
			} else if (i > 2) { //3
				System.out.println("Final grade of the 3rd student is: ");
			} else if (i > 1) { //2
				System.out.println("Final grade of the 2nd student is: ");
			} else {
				System.out.println("Final grade of the 1st student is: ");
			}
			grades.add(input.nextInt());
		}
		
		System.out.println("\nThe grades:");
		for (Integer grade : grades) {
			System.out.printf(" %d", grade);
		}
		
		System.out.println("\n\nHighest grade: " + Collections.max(grades));
		System.out.println("Lowest grade: " + Collections.min(grades));
		
		input.close();
	}

}
