import java.lang.Math.*;
import java.util.Scanner;


public class KerbinCalculator {

	public static void main(String[] args) {
		
		Scanner stdIn = new Scanner(System.in);
		
		welcome();
		int menuChoice = menuInput(stdIn);
		
		if (menuChoice == 1) {
			double mass = massInput(stdIn);
			double orbHeight = orbHeightInput(stdIn);
			
			double reqVelocity = givenHeightOpt1(orbHeight, mass);
			System.out.println();
			System.out.println("Thank you for using the Kerbal Calculator!");
			System.out.println("Your angular velocity will be: " + reqVelocity + " m/s at an altitude of " + orbHeight + "meters.");
		}
		else if (menuChoice == 2) {
			double mass = massInput(stdIn);
			double reqVelocity = angVelocityInput(stdIn);
			
			double orbitHeight = givenVelocityOpt2(reqVelocity, mass);
			System.out.println();
			System.out.println("Thank you for using the Kerbal Calculator!");
			System.out.println("Your orbit height (relative to surface) will be " + orbitHeight + " meters traveling at " + reqVelocity + " m/s.");
		}
		else if (menuChoice == 3) {
			double mass = massInput(stdIn);
			double velocity = angVelocityInput(stdIn);
			
			double orbPeriod = givenAngularVelocityOpt3(velocity, mass);
			System.out.println();
			System.out.println("Thank you for using the Kerbal Calculator!");
			System.out.println("Your orbital period will be " + orbPeriod + " seconds.");
		}
		else if (menuChoice == 4) {
			double mass = massInput(stdIn);
			double orbHeight = orbHeightInput(stdIn);
			
			double orbPeriod = givenHeightOpt4(orbHeight, mass);
			System.out.println();
			System.out.println("Thank you for using the Kerbal Calculator!");
			System.out.println("Your orbital period will be " + orbPeriod + " seconds.");
		}
		else {
			System.out.println("That's not a valid input!");
			menuChoice = menuInput(stdIn);
		}
		
		
		stdIn.close();		
	}

	
	public static void welcome() {
		System.out.println("Welcome to the Kerbal Space Program Calculator!");
		System.out.println("------------------------------------------------");
		System.out.println("[1]: You're trying to find what velocity is needed at a known altitude relative to Kerbin to maintain a polar orbit");
		System.out.println("");
		System.out.println("[2]: You're trying to find what height of polar orbit you would get using a known angular velocity");
		System.out.println("");
		System.out.println("[3]: Given an angular velocity, [and assuming a polar orbit] this finds the period of orbit of a body relative to Kerbin");
		System.out.println("");
		System.out.println("[4]: (Same as above but using a given height)");
		System.out.println("-------------------------------------------------");
		System.out.println("");
	}
	
	
	public static int menuInput(Scanner stdIn) {
		System.out.print("Please enter which menu selection you'd like to pick [1-4]: ");
		int menu = stdIn.nextInt();
		System.out.println();
		
		
		while( !(menu >= 1 && menu <= 4)) {
			System.out.println("That is not a valid menu option!");
			System.out.print("Please enter which menu selection you'd like to pick [1-4]: ");
			menu = stdIn.nextInt();
		}
		return menu;
	}
	
	
	public static double massInput(Scanner stdIn) {
		System.out.print("Please enter what the mass of your shuttle is [double]: ");
		double mass = stdIn.nextDouble();
		System.out.println();
		
		while( !(mass >= 0)) {
			System.out.println("That is not a valid mass (mass cannot be negative)!");
			System.out.print("Please enter what the mass of your shuttle is [double]: ");
			mass = stdIn.nextDouble();
		}
		return mass;
	}
	
	
	public static double angVelocityInput(Scanner stdIn) {
		System.out.print("Please enter your angular velocity [double]: ");
		double angVeloc = stdIn.nextDouble();
		System.out.println();
		
		while( !(angVeloc >= 0)) {
			System.out.println("That is not a valid velocity (velocity cannot be negative)!");
			System.out.print("Please enter your angular velocity [double]: ");
			angVeloc = stdIn.nextDouble();
		}
		return angVeloc;		
	}
	
	
	public static double orbHeightInput(Scanner stdIn) {
		System.out.print("Please enter your orbital height relative to the surface of Kerbin [double]: ");
		double orbHeight = stdIn.nextDouble();
		System.out.println();
		return orbHeight;		
	}
	
	
	public static double givenHeightOpt1(double height, double mass) {
		double KERB_MASS = (5.929 * (Math.pow(10, 22)) );
		double GRAV_CONSTANT = (6.67 * (Math.pow(10, (-11) ) ) );
		double KERB_RADIUS = 600000;
		double totalHeight = KERB_RADIUS + height;
		
		double gravForce = ( (GRAV_CONSTANT) * (KERB_MASS) * (mass) ) / ( Math.pow(totalHeight, 2));
		double reqVeloc = Math.sqrt( ( (totalHeight) * (gravForce) ) / mass);
		return reqVeloc;
	}
	
	
	public static double givenVelocityOpt2(double velocity, double mass) {
		double KERB_MASS = (5.929 * (Math.pow(10, 22)) );
		double GRAV_CONSTANT = (6.67 * (Math.pow(10, (-11) ) ) );
		double KERB_RADIUS = 600000;
		
		double altitude = ( ( -(mass * (Math.pow(velocity, 2)) * KERB_RADIUS) + (GRAV_CONSTANT * KERB_MASS * mass)) / (mass * (Math.pow(velocity, 2))));
		return altitude;
	}
	
	
	public static double givenAngularVelocityOpt3(double velocity, double mass) {
		double KERB_MASS = (5.929 * (Math.pow(10, 22)) );
		double GRAV_CONSTANT = (6.67 * (Math.pow(10, (-11) ) ) );
		double KERB_RADIUS = 600000;
		double orbitalHeight = givenVelocityOpt2(velocity, mass);
		
		double period = (Math.sqrt( (4 * (Math.pow(Math.PI, 2)) * ( (Math.pow( (orbitalHeight + KERB_RADIUS), 3))) / (GRAV_CONSTANT * KERB_MASS))));
		return period;
	}
	
	
	public static double givenHeightOpt4(double height, double mass) {
		double KERB_MASS = (5.929 * (Math.pow(10, 22)) );
		double GRAV_CONSTANT = (6.67 * (Math.pow(10, (-11) ) ) );
		double KERB_RADIUS = 600000;
		
		double period = (Math.sqrt( (4 * (Math.pow(Math.PI, 2)) * ( (Math.pow( (height + KERB_RADIUS), 3))) / (GRAV_CONSTANT * KERB_MASS))));
		return period;
	}
	
	
	
}
