import java.util.Scanner;

public class CircleCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int radius = readRadius();
		long diameter = diameterFrom(radius);
		double area = areaFrom(radius);
		System.out.printf("Radius: %d\nDiameter: %d\nArea: %.2f", radius, diameter, area);
	}
	
	public static int readRadius() {
		System.out.println("Digite o raio: ");
		int radius;
		Scanner input = new Scanner(System.in);
		radius = input.nextInt();
		input.close();
		return radius;
	}
	
	private static long diameterFrom(int radius) {
		return radius*2;
	}
	
	private static double areaFrom(int radius) {
		return 2*(Math.PI)*radius;
	}
}
