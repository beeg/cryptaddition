package Constraints;

import java.util.List;

import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class AllDiff extends Constraint<Integer>{

	public AllDiff(String name, List<Variable<Integer>> variables) {
		super(name, variables);
		for(Variable<Integer> v: variables)	{
			v.addConstraint(this);
		}
	}

	public boolean isSatisfied(Variable<Integer> arg0, Integer arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
