package calc;

import java.util.Stack;

public class Calculator {

	private Stack<Element> stack = new Stack<Element>();
	
	public void push(Element e){
		stack.push(e);
	}
	
	public Value getValue(){
		return stack.pop().evaluate(stack);
	}
}
