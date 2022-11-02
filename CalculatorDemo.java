package assg7_seetooM21;

import java.util.*;

public class CalculatorDemo {

	public static void main(String[] args) {
		
		Scanner kbd =  new Scanner(System.in);
		String exp = "";
		while(!exp.equals("e")) {
		System.out.println("Enter an infix expression or enter 'e' to exit: ");
		
		exp = kbd.nextLine();
		
		if(!exp.equals("e")){
		Calculator calc = new Calculator(exp);
		System.out.println("Postfix expression: " + calc.getPostfix());
		System.out.println("Evaluated: " + calc.evaluate());
		}
	}
}

}
