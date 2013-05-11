package Constraints;

import java.util.List;

import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class Unary extends Constraint<Integer>{

	public Unary(String arg0, List<Variable<Integer>> arg1) {
		super(arg0, arg1);
	}

	@Override
	public boolean isSatisfied(Variable<Integer> arg0, Integer arg1) {
		if(arg0.getValue().equals(new Integer(0)))
			return false;
		else
			return true;
	}

}
