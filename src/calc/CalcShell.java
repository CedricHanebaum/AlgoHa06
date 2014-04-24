package calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CalcShell {

	private HashMap<Character, Double> mapVars = new HashMap<Character, Double>();

	public String analyze(String expr) {
		expr = expr.trim();
		if (expr.contains("=")) {
			return asignVariable(expr);
		} else {
			return "result = " + eval(expr);
		}
	}
	
	public void clear(){
		mapVars = new HashMap<Character, Double>();
	}

	private String asignVariable(String expr) {
		String[] ex = expr.split("=");
		if (ex.length != 2) throw new IllegalArgumentException();

		String var = ex[0];
		String exprRPN = ex[1];

		double value = eval(exprRPN);
		mapVars.put(var.charAt(0), value);

		return "variable '" + var.charAt(0) + "' defined, value = " + value;
	}

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
			default:
				if (isNumber(s)) {
					calc.push(new Value(Double.parseDouble(s)));
				} else if (isVariable(s)) {

					char c = s.charAt(0);
					if (mapVars.keySet().contains(c)) {
						calc.push(new Value(mapVars.get(c)));
					} else {
						return Double.NaN;
					}

				} else {
					return Double.NaN;
				}
				break;
			}
		}

		return calc.getValue().getValue();
	}

	private boolean isVariable(String s) {
		char c = s.charAt(0);
		if (s.length() != 1) return false;
		return Character.isLetter(c);
	}

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
