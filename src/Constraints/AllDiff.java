package Constraints;

import java.util.List;

import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class AllDiff extends Constraint<Integer>{

	public AllDiff(String name, List<Variable<Integer>> variables) {
		super(name, variables);
		for(Variable<Integer> v: variables)	{
			//Adding alldiff constraint to variables
			System.out.println("Adding alldiff constraint to "+v);
			v.addConstraint(this);
		}
	}

	public boolean isSatisfied(Variable<Integer> arg0, Integer arg1) {
		arg0.setValue(arg1);
		for(Variable<Integer> v: this.getVariables())	{
			
			//Checking alldiff list of variables completeness
			if(v.hasValue())	{
				/* Checking that the rest of variables have different value
				 * Not necessary to compare the rest of variables between them,
				 * since they cannot have the same value because it has been
				 * checked when they were assigned the value					  
				 */
				if(v!=arg0 && v.getValue().intValue()==arg1.intValue())	{	
					//If a variable with the same value is found, it is not satisfied
					System.out.println("Aldiff not satisfied");
					return false;
				}					
			} else	{
				System.out.println("Aldiff Incomplete");
				return true;
			}
		}
		//Every variable has been checked that they have different values
		System.out.println("Aldiff satisfied");
		return true;
	}

	

}
