import java.util.Scanner;

public class Exercise1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Type the radius of the circle: ");
		Integer radius = input.nextInt();
		
		System.out.println("Diameter: " + radius*2);
		System.out.println("Area: " + Math.PI*(Math.pow(radius, 2)));
		
		input.close();
	}
}
