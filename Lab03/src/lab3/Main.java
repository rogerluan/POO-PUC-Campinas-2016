package lab3;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Account firstAccount = new Account();
		firstAccount = receiveAccount();
		
		Account secondAccount = new Account();
		secondAccount = receiveAccount();
		
		Account thirdAccount = new Account();
		thirdAccount = receiveAccount();
		
		firstAccount.printInformation();
		secondAccount.printInformation();
		thirdAccount.printInformation();
	}
	
	private static Account receiveAccount() {
		Account account = new Account();
		System.out.println("Digite o número da conta que deseja cadastrar: ");
		
		Scanner input = new Scanner(System.in);
		//variable leak here, since we're not closing Scanner.
		//it cannot be closed since it's inside a static method.

		account.setNumber(input.nextInt());
		input.nextLine(); //needed to read the next line.
		
		System.out.println("Nome do correntista: ");
		account.setOwner(input.nextLine());
		
		System.out.println("Saldo da conta: ");
		account.setBalance(input.nextDouble());
		
		System.out.println("Limite da conta: ");
		account.setLimit(input.nextDouble());
		
		return account;
	}
}
