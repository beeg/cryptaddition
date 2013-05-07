package Constraints;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class Sum extends Constraint<Integer>{
	
	private List<Variable<Integer>> sum;
	private Variable<Integer> result;
	private Variable<Integer> carry;

	public Sum(String arg0, List<Variable<Integer>> arg1, int numberOperands) {
		super(arg0, arg1);
		sum = new ArrayList<Variable<Integer>>();
		for(int i=0;i<numberOperands;i++)	{
			sum.add(this.getVariables().get(i));
		}
		result = this.getVariables().get(numberOperands);
		carry = this.getVariables().get(numberOperands+1);
	}

	@Override
	public boolean isSatisfied(Variable<Integer> arg0, Integer arg1) {
		arg0.setValue(arg1);
		int sumValue = 0;
		for(Variable<Integer> s : this.sum)	{
			sumValue = s.getValue().intValue();
		}
		if(sumValue == ((result.getValue().intValue()*10)+carry.getValue().intValue()))
			return true;
		else
			return false;
	}

	

}
