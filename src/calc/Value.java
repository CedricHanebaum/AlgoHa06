package calc;

import java.util.Stack;

public class Value extends Element {

	private double value;
	
	public Value(double value) {
		this.value = value;
	}
	
	@Override
	public Value evaluate(Stack<Element> stack) {
		return this;
	}
	
	public double getValue(){
		return value;
	}

}
