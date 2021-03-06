import java.util.Scanner;

public class Exercise2 {

	private static Scanner input;
	private static Integer opcode = 1;
	private static char currentPlayer = 'X'; 
	
	public static void main(String[] args) {
		input = new Scanner(System.in);
		
		while (opcode == 1) {
			char[][] board = new char[3][3];
			cleanBoard(board);
			
			System.out.println("First player, please make your move. Type a number between 0 and 8.");
			printBoard(board);
			Integer move = input.nextInt();
			
			
			printMenu();
		}
		
		input.close();
	}
	
	private static void printMenu() {
		System.out.println("What do you want to do now? Type 1 if you want to play, 0 to exit.");
		opcode = input.nextInt();
	}
}
