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
		arg0.setValue(arg1);
		for(Variable<Integer> v: this.getVariables())	{
			if(v.getValue()!=null && arg0!=v)	{
				if(arg0.getValue().equals(v.getValue()))
					return false;
			}
		}
		return true;
	}

	

}
