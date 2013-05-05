package Constraints;

import java.util.List;

import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class Sum extends Constraint<Integer>{

	public Sum(String arg0, List<Variable<Integer>> arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSatisfied(Variable<Integer> arg0, Integer arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
