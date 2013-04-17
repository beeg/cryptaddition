

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * It implements the functionality of a parser for an XML document that contains the
 * description of the problem environment.
 *  */
public abstract class XMLReader extends DefaultHandler {

	/**
	 * Constructor method.
	 * 
	 * @param fileXML
	 *            String that is the name of the XML file containing the 
	 *            information describing the problem environment.
	 */
	public XMLReader(String fileXML) {
		try {
			if (fileXML != null && new File(fileXML).exists()) {
				this.processXML(fileXML);
			} else {
				System.err.println("XML file name is 'null' or the file doesn't exists");
			}
		} catch (Exception ex) {
			System.err.println("% StateXMLReader(): " + ex.getMessage());
		}
	}

	/**
	 * It parses an XML file.
	 * 
	 * @param fileXML
	 *            String that is the name of the file to be parsed.
	 */
	private void processXML(String fileXML) {
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setValidating(true);
			parserFactory.setNamespaceAware(true);
			SAXParser parser = parserFactory.newSAXParser();
			parser.parse(fileXML, this);
		} catch (Exception ex) {
			System.err.println("% StateXMLReader.processXML(): " + ex);
		}
	}

	/**
	 * It returns the problem state.
	 * 
	 * @return State having the problem state.
	 */
	//public abstract State getState();

	/**
	 * Implementation of the startElement method from class DefaultHandler. This
	 * is executed every time the parser finds a new XML element.
	 * 
	 * @param uri
	 *            String having the URL names space.
	 * @param localName
	 *            String having the element's name without the names space.
	 * @param qName
	 *            String having the element's name with the names space.
	 * @param attributes
	 *            Attributes having the list of element's attributes.
	 * @throws SAXException
	 */
	public abstract void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;
}