package Problem;

import java.util.List;
import es.deusto.ingenieria.is.csp.formulation.CSPProblem;
import es.deusto.ingenieria.is.csp.formulation.Variable;

public class CryptadditionProblem extends CSPProblem<Integer>{
	
	private List<List<Variable<Integer>>> variables;

	public CryptadditionProblem(List<List<Variable<Integer>>> variables) {
		super();
		this.variables = variables;
	}
	
	

}