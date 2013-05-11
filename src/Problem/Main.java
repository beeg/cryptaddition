package Problem;

import es.deusto.ingenieria.is.csp.algorithms.BackTracking;
import es.deusto.ingenieria.is.csp.algorithms.CSPAlgorithm;
import XMLParser.PuzzleParser;

public class Main {

	public static void main(String[] args) {
		//Parse an XML file
		PuzzleParser parser = new PuzzleParser("additionpuzzle1.xml");
		
		//Generate the Cryptaddition problem
		CryptadditionProblem problem = new CryptadditionProblem(parser.getVariables(),parser.getNumOperands());
		
		//Generate the algorithm, in this case we have chosen Backtracking
		CSPAlgorithm csp = new BackTracking<Integer>();
		
		//Solve the problem
		System.out.println(csp.solve(problem));
		System.out.println(problem.getVariables());

	}

}
