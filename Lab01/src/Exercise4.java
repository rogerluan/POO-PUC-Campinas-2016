
public class Exercise4 {

	private static Integer numberOfLines = 10; 
	
	public static void main(String[] args) {
		System.out.println("Number\tSquared\tCubed");
		for (int i = 1 ; i <= numberOfLines ; i++) {
			System.out.printf("\n%d\t%.0f\t%.0f", i, Math.pow(i, 2), Math.pow(i, 3));
		}
	}
}
