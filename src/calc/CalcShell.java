package calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CalcShell {

	private HashMap<Character, Double> mapVars = new HashMap<Character, Double>();

	/**
	 * Analyzes the given expression. Either assigns a value to a variable or performs a calculation.
	 * @param expr the expression to analyze.
	 * @return a String describing the actions performed.
	 */
	public String analyze(String expr) {
		expr = expr.trim();
		if (expr.contains("=")) {
			return assignVariable(expr);
		} else {
			return "result = " + eval(expr);
		}
	}
	
	/**
	 * removes all variables.
	 */
	public void clear(){
		mapVars = new HashMap<Character, Double>();
	}

	/**
	 * Assigns the value of a RPN equation to a variable.
	 * @param expr the expression to evaluate.
	 * @return s String describing the action performed.
	 */
	private String assignVariable(String expr) {
		String[] ex = expr.split("=");
		if (ex.length != 2) throw new IllegalArgumentException();

		String var = ex[0];
		String exprRPN = ex[1];

		double value = eval(exprRPN);
		mapVars.put(var.charAt(0), value);

		return "variable '" + var.charAt(0) + "' defined, value = " + value;
	}

	/**
	 * Evaluates a calculation given in Reverse Polish Notation.
	 * @param exprRPN a String, representing a calculation in Reverse Polish Notation.
	 * @return the result of the calculation.
	 */
	private double eval(String exprRPN) {
		exprRPN = exprRPN.trim();
		String[] expr = exprRPN.split(" ");

		Calculator calc = new Calculator();

		for (String s: expr) {
			switch (s) {
			case "+":
				calc.push(new OppPlus());
				break;
			case "-":
				calc.push(new OppMinus());
				break;
			case "*":
				calc.push(new OppMultiplication());
				break;
			case "/":
				calc.push(new OppDivision());
				break;
			case "%":
				calc.push(new OppModulo());
				break;
			case "^":
				calc.push(new OppPow());
				break;
			default:	// if the current String is not an operator, check if it is a number or variable.
				if (isNumber(s)) {
					calc.push(new Value(Double.parseDouble(s)));
				} else if (isVariable(s)) {

					char c = s.charAt(0);
					if (mapVars.keySet().contains(c)) {
						calc.push(new Value(mapVars.get(c)));
					} else {
						return Double.NaN;	// Variable not found.
					}

				} else {
					return Double.NaN;	// String is neither an operator, nor a number, nor a variable.
				}
				break;
			}
		}

		// Calculate
		return calc.getValue().getValue();
	}

	/**
	 * Checks whether the given String is a Variable. 
	 * @param s the String to check.
	 * @return
	 */
	private boolean isVariable(String s) {
		char c = s.charAt(0);
		if (s.length() != 1) return false;
		return Character.isLetter(c);
	}

	/**
	 * Checks whether the given String is a Number.
	 * @param s the String to check.
	 * @return
	 */
	private boolean isNumber(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		CalcShell shell = new CalcShell();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean running = true;
		
		String s = "";
		try{
			while(running){
				s = in.readLine();
				switch(s){
				case "quit":
					running = false;
					break;
				case "clear":
					shell.clear();
					break;
				default:
					System.out.println("task '" + s + "' => " + shell.analyze(s));
					break;
				}
			}
		} catch(IOException e){
			System.out.println("kaputt");
		}
	}
}
