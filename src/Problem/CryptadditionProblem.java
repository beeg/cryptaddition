package Problem;

import java.util.ArrayList;
import java.util.List;

import Variable.IntegerVariable;

import Constraints.AllDiff;
import Constraints.Sum;
import es.deusto.ingenieria.is.csp.formulation.CSPProblem;
import es.deusto.ingenieria.is.csp.formulation.Constraint;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class CryptadditionProblem extends CSPProblem<Integer>{
	
	private List<List<Variable<Integer>>> variables;

	public CryptadditionProblem(List<List<Variable<Integer>>> variables) {
		super();
		this.variables = variables;
		
		//create a variable list whit all different ones
		List<Variable<Integer>> dif_var = crete_different_variables(variables);
		
		//Alldif constraints adding
		Constraint<Integer> alldif = new AllDiff("Alldiff", dif_var );
		for(Variable<Integer> v: dif_var){
			v.addConstraint(alldif);
		}
		
		//all the other constraints
		//1º calculate columns
		List<List<Variable<Integer>>> columns = create_colums(variables);
		
		//2º Add the constraints
		addConstraints(columns);
	}
	

	private List<Variable<Integer>> crete_different_variables(List<List<Variable<Integer>>> variables){
		
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
	
	private List<List<Variable<Integer>>> create_colums (List<List<Variable<Integer>>> variables){
		
		List<Variable<Integer>> result = variables.get(variables.size()-1);
		int numColums = result.size();
		
		List<List<Variable<Integer>>> columns = new ArrayList<List<Variable<Integer>>>();
		
		for(int j=0; j<numColums; j++){
			for(int i=0; i<variables.size(); i++){
				List<Variable<Integer>> current_column= new ArrayList<Variable<Integer>>();
				if(variables.get(i).size()>j){
					current_column.add(variables.get(i).get(j));
					columns.add(current_column);
				}
			}
		}
		return columns;
	}
	

	private void addConstraints(List<List<Variable<Integer>>> columns) {
		// TODO Auto-generated method stub
		//Total Carry 
		IntegerVariable carry= null;
		
		for(int i=0;i<columns.size();i++){
			//carry of each column sum
			IntegerVariable column_carry=null;
			
			//get only the operands not the result
			List<Variable<Integer>> column_operands=columns.get(i).subList(0, columns.get(i).size()-1);
			
			if(!column_operands.isEmpty()){
				sumColumns(carry, column_carry, column_operands);
			}
			else{
				//is empty
				//************************************************
			}
			//Carry Update
			carry=column_carry;
		}
	}

	private void sumColumns(IntegerVariable carry,IntegerVariable column_carry, List<Variable<Integer>> column_operands){
		
			//if the carry is not null, that means that the previous
			//has a carry. So you need to add like a operand
			if(carry!=null){
				column_operands.add(0,carry);
			}
			
			//column carry
			ArrayList<Integer> sum_integers = new ArrayList<Integer>();
			for(int j=0;j<variables.size()-1;j++){
				sum_integers.add(j);
			}
			column_carry=new IntegerVariable("carry", sum_integers);
			
			//add the column carry
			column_operands.add(column_carry);
			
			//create constraint
			Constraint<Integer> sumConstraint = new Sum("sumConstraint", column_operands, column_operands.size());
			addSumConstraints(sumConstraint,column_operands);
			
	}
	

	private void addSumConstraints(Constraint<Integer> sumConstraint, List<Variable<Integer>> column_operands) {
		// TODO Auto-generated method stub
		for(Variable<Integer> v:column_operands){
			v.addConstraint(sumConstraint);
		}
	}
}