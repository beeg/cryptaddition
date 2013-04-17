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
			}
			
		} catch(Exception e)	{
			e.printStackTrace();
		}
	}

}
