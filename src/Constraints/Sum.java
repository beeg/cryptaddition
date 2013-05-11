package Constraints;

import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class Sum extends Constraint<Integer>{
	
	private List<Variable<Integer>> sum;
	private int numberOperands;

	public Sum(String arg0, List<Variable<Integer>> arg1, int numberOperands) {
		super(arg0, arg1);
		this.numberOperands=numberOperands;
		this.sum = new ArrayList<Variable<Integer>>();
		for(int i=0;i<numberOperands;i++)	{
			sum.add(this.getVariables().get(i));
		}
		System.out.println("Sum constraint created");
		System.out.println("Sum elements");
		for(Variable<Integer> v : sum)	{
			System.out.println(v);
		}		
	}

	public boolean isSatisfied(Variable<Integer> arg0, Integer arg1) {
		arg0.setValue(arg1);
		int sumValue = 0;
		for(Variable<Integer> s : this.sum)	{
			sumValue = s.getValue().intValue();
		}
		
		int carry = (this.getVariables().get(numberOperands+1)==null) ? 0 : this.getVariables().get(numberOperands+1).getValue().intValue();
		
		if(sumValue == 10*carry + this.getVariables().get(numberOperands).getValue().intValue())	{
			System.out.println("Sum constraint satisfied");
			return true;
		}	else	{
			System.out.println("Sum constraint not satisfied");
			return false;
		}
	}

	

}
