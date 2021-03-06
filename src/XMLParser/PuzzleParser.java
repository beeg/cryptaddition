package XMLParser;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import Variable.IntegerVariable;

import es.deusto.ingenieria.is.csp.formulation.Variable;


public class PuzzleParser extends XMLReader{
	
	private List<List<Variable<Integer>>> variables;
	private int numOperands;

	public PuzzleParser(String fileXML) {
		super(fileXML);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		try	{
			if(qName.equals("is:additionpuzzle") || qName.equals("is:puzzle"))	{
				this.numOperands = Integer.parseInt(attributes.getValue("operands"));
				createDataStructure(numOperands);
				System.out.println("Operands: "+ numOperands);
			} else if(qName.equals("is:operand"))	{
				String word = attributes.getValue("word");
				add(word);
				System.out.println("Word: "+word);
			} else if(qName.equals("is:result"))	{
				String resultWord = attributes.getValue("word");
				add(resultWord);
				System.out.println("Result Word: "+resultWord);
			}			
		} catch(Exception e)	{
			e.printStackTrace();
		}
	}
	
	public List<List<Variable<Integer>>> getVariables() {
		return variables;
	}

	public void setVariables(List<List<Variable<Integer>>> variables) {
		this.variables = variables;
	}

	public int getNumOperands() {
		return numOperands;
	}

	public void setNumOperands(int numOperands) {
		this.numOperands = numOperands;
	}

	public void createDataStructure(int operands)	{
		variables = new ArrayList<List<Variable<Integer>>>();
	}
	
	public ArrayList<Integer> createDomain(int maxValue)	{
		ArrayList<Integer> domain = new ArrayList<Integer>();
		for(int i=0;i<=maxValue;i++)	{
			domain.add(new Integer(i));
		}
		return domain;
	}
	
	public void add(String word)	{
		ArrayList<Variable<Integer>> operand = new ArrayList<Variable<Integer>>();
		ArrayList<Integer> domain = createDomain(9);
		for(int i=0;i<word.length();i++)	{
			operand.add(new IntegerVariable(word.valueOf(word.charAt(i)),domain));
		}
		variables.add(operand);
	}

}
