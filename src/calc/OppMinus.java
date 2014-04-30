package calc;

import java.util.Stack;

public class OppMinus extends Opperator {

	@Override
	public Value evaluate(Stack<Element> stack) {
		double[] vals = new double[2];
		for(int i = 0; i < vals.length; ++i){
			if(stack.isEmpty()) return new Value(Double.NaN);
			vals[i] = stack.pop().evaluate(stack).getValue();
		}
		return new Value(vals[1] - vals[0]);
	}

}
