package calc;

import java.util.Stack;

public class Calculator {

	private Stack<Element> stack = new Stack<Element>();
	
	/**
	 * Pushes a new element to the stack.
	 * @param e the element to push on the stack.
	 */
	public void push(Element e){
		stack.push(e);
	}
	
	/**
	 * evaluates the expression currently on the stack.
	 * @return the value of the expression currently on the stack.
	 */
	public Value getValue(){
		return stack.pop().evaluate(stack);
	}
}
