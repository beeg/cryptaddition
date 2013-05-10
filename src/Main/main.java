package Main;

import es.deusto.ingenieria.is.csp.algorithms.BackTracking;
import es.deusto.ingenieria.is.csp.algorithms.CSPAlgorithm;
import Problem.CryptadditionProblem;
import XMLParser.PuzzleParser;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PuzzleParser parser = new PuzzleParser("additionpuzzle1.xml");
		CryptadditionProblem problem = new CryptadditionProblem(parser.getVariables());
		CSPAlgorithm<Integer> algorithm = new BackTracking<>();
		algorithm.solve(problem);
	}

}
