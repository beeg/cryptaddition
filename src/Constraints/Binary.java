package Constraints;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class Binary extends Constraint<Integer>{

	public Binary(String arg0, List<Variable<Integer>> arg1) {
		super(arg0, arg1);
	}

	public boolean isSatisfied(Variable<Integer> arg0, Integer arg1) {
		arg0.setValue(arg1);
		ArrayList<Variable<Integer>> variables = (ArrayList<Variable<Integer>>) this.getVariables();
		if(variables.get(0).getValue().equals(variables.get(1).getValue()))	{
			System.out.println("Binary constraint satisfied");
			return true;
		}	else	{
			System.out.println("Binary constraint not satisfied");
			return false;
		}
	}

}
