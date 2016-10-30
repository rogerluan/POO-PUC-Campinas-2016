import java.util.Scanner;

public class ManagerHelper {
	public static Integer chooseIndex(Scanner input, Integer maxIndex) {
		try {
			input = new Scanner(System.in);
			Integer option = input.nextInt();
			if (option >= 1 && option <= maxIndex) {
				return option;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("The choosen index doesn't represent a valid integer between 1 and " + maxIndex + ". Please try again.\n");
			return chooseIndex(input, maxIndex);
		}
	}
}
