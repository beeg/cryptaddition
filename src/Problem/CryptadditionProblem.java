package Problem;

import java.util.ArrayList;
import java.util.List;

import Variable.IntegerVariable;

import Constraints.AllDiff;
import Constraints.Binary;
import Constraints.Sum;
import es.deusto.ingenieria.is.csp.formulation.CSPProblem;
import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class CryptadditionProblem extends CSPProblem<Integer>{
	
	private List<List<Variable<Integer>>> variables;
	private int numOperands;

	public CryptadditionProblem(List<List<Variable<Integer>>> variables, int numOperands) {
		super();
		this.variables = variables;
		
		//Create a list of variables with all different ones
		List<Variable<Integer>> difVar = createDiffVariables(variables);
		
		//Alldif constraints adding
		Constraint<Integer> alldif = new AllDiff("Alldiff", difVar);
		
		/* Sum constraints
		 * In order to implement it, we have to get first the different columns
		 */
		List<List<Variable<Integer>>> columns = createColumns(variables);
		
		//Create the rest of constraints
		addConstraints(columns);
	}
	

	private List<Variable<Integer>> createDiffVariables(List<List<Variable<Integer>>> variables){
		List<Variable<Integer>> dif_var=new ArrayList<Variable<Integer>>();
		for(List<Variable<Integer>> lv: variables){
			for(Variable<Integer> v: lv){
				if(!dif_var.contains(v)){
					dif_var.add(v);
				}
			}
		}
		return dif_var;
		
	}
	
	private List<List<Variable<Integer>>> createColumns (List<List<Variable<Integer>>> variables){
		
		List<Variable<Integer>> result = variables.get(variables.size()-1);
		int numColums = result.size();
		
		List<List<Variable<Integer>>> columns = new ArrayList<List<Variable<Integer>>>();
		
		for(int j=0; j<numColums; j++)	{
			List<Variable<Integer>> currentColumn= new ArrayList<Variable<Integer>>();
			for(int i=0; i<variables.size(); i++)	{				
				if(variables.get(i).size()>j){
					currentColumn.add(variables.get(i).get(j));					
				}
			}
			columns.add(currentColumn);
		}
		return columns;
	}
	

	private void addConstraints(List<List<Variable<Integer>>> columns) {
		//Total Carry 
		IntegerVariable carry= null;
		IntegerVariable columnCarry=null;
		
		for(int i=0;i<columns.size();i++){
			//Carry of each column sum
			columnCarry=null;
			
			//Get only the operands, avoiding the result
			List<Variable<Integer>> columnOperands=columns.get(i).subList(0, columns.get(i).size()-1);
			
			//Checking that there are operands to be summed
			if(!columnOperands.isEmpty()){
				//If the carry is not null, that means that the previous column generated
				// a carry, so you need to add it like an operand
				if(carry!=null){
					columnOperands.add(0,carry);
				}
				
				//Generate the domain of the carry variable 
				ArrayList<Integer> sumIntegers = new ArrayList<Integer>();
				for(int j=0;j<variables.size()-1;j++){
					sumIntegers.add(j);
				}
				columnCarry=new IntegerVariable("Carry "+i, sumIntegers);
				
				//Add the carry variable to the column
				columnOperands.add(columnCarry);
				
				//Create constraint
				Constraint<Integer> sumConstraint = new Sum("Sum Constraint", columnOperands, columnOperands.size()-1);
				//Add the new constraint to the variables involved
				addSumConstraints(sumConstraint,columnOperands);
			}	else	{
				//No operands to be summed, just a result variable
				ArrayList<Integer> domain = new ArrayList<Integer>();
				//Initializing the domain to 1 we are applying the unary constraint
				for(int j=1;j<numOperands;j++)	{
					domain.add(j);
				}
				Variable<Integer> result = columns.get(i).get(0);
				result.setDomain(domain);
				
				if(result.getDomain().size()==1)
					result.setValue(result.getDomain().get(0));
				
				if(carry!=null)	{
					//0 value must be removed from the domain
					carry.getDomain().remove(0);
					
					//Checking if it is a singleton
					if(carry.getDomain().size()==1)
						carry.setValue(carry.getDomain().get(0));
					
					columnOperands.add(carry);
					
					//Binary Constraint must be created
					Binary binaryConstraint = new Binary(result.getName()+" = "+carry.getName(),columnOperands);
					//Add the constraint to the variables
					result.addConstraint(binaryConstraint);
					carry.addConstraint(binaryConstraint);
				}
			}
			//Carry update
			carry=columnCarry;
		}
	}	

	/**
	 * Add the constraint to the variables that are involved
	 * @param sumConstraint
	 * @param column_operands
	 */
	private void addSumConstraints(Constraint<Integer> sumConstraint, List<Variable<Integer>> columnOperands) {
		for(Variable<Integer> v:columnOperands){
			v.addConstraint(sumConstraint);
		}
	}
}