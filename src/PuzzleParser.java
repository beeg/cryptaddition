import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class PuzzleParser extends XMLReader{

	public PuzzleParser(String fileXML) {
		super(fileXML);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		try	{
			if(qName.equals("is:additionpuzzle"))	{
				int operands = Integer.parseInt(attributes.getValue("operands"));
				System.out.println("Operands: "+operands);
			} else if(qName.equals("is:operand"))	{
				String word = attributes.getValue("word");
				System.out.println("Word: "+word);
			} else if(qName.equals("is:result"))	{
				String resultWord = attributes.getValue("word");
				System.out.println("Result Word: "+resultWord);
			}			
		} catch(Exception e)	{
			e.printStackTrace();
		}
	}

}
