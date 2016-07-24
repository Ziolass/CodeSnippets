import java.io.File;
import java.io.ObjectInputStream.GetField;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


/**
 * Klasa typu singleton, przechowujaca dokument XML z ustawieniami programu.
 *
 */
public class XMLFile {
	
	/**
	 * dokument XML
	 */
	private Document xmlDocument;
	/**
	 * nazwa pliku XML zawierajaca dane konfiguracyjne
	 */
	private static final String NAME_OF_FILE = "configuration.xml";

	/**
	 * Konstruktor prywatny
	 */
	private XMLFile() {
		File fXmlFile = new File(NAME_OF_FILE);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			xmlDocument = dBuilder.parse(fXmlFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Prywatna klasa statyczna przeytrzymujaca informacje o instancji klasy XMLFile
	 *
	 */
	private static class SingletonHolder {
	    private final static XMLFile instance = new XMLFile();
	}
	/**
	 * Funkcja zwracajaca instancje dokumentu XML
	 * @return
	 */
	public static XMLFile getInstance() {
	    return SingletonHolder.instance;
	}


	/**
	 * Metoda pobierajaca ustawienia z pliku XML
	 * XML przyklad:
	 *
	 * <config>
	 * 		<servers>
	 * 			<server>
	 * 				<id>1</id>
	 * 				<address>http://0.0/</address>
	 * 				<user>user1</user>
	 * 				<password>123</password>
	 * 			</server>
	 * 		</servers>
	 * </config>
	 *
	 * Wywolanie getData("server","user","1")
	 *
	 * @param name - nazwa wymaganego ustawienia np. server
	 * @param paramName - nazwa elementu do pobrania np. user
	 * @param id - unikalny id ustawienia np. 1
	 * @return zwraca wartosc wybranego parametru
	 */
	public synchronized String getData(String name, String paramName, String id) {
		NodeList nodeList =  xmlDocument.getElementsByTagName(name);
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node nNode = nodeList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if( eElement.getElementsByTagName("id").item(0).getTextContent().equals(id)){
					return eElement.getElementsByTagName(paramName).item(0).getTextContent();
				}
			}
		}
		return null;
	}
	
	public synchronized void setData(String name, String paramName, String id, String newValue) {
		
		System.out.println(newValue);
		
		NodeList nodeList =  xmlDocument.getElementsByTagName(name);
		for (int temp = 0; temp < nodeList.getLength(); temp++) {
			Node nNode = nodeList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
				Element eElement = (Element) nNode;
				if( eElement.getElementsByTagName("id").item(0).getTextContent().equals(id)){
					System.out.println(eElement.getElementsByTagName(paramName).item(0).getTextContent());
					eElement.getElementsByTagName(paramName).item(0).setTextContent(newValue);
					System.out.println(eElement.getElementsByTagName(paramName).item(0).getTextContent());
				}
			}
		}
		
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File("configuration.xml"));
			Source input = new DOMSource(xmlDocument);
			transformer.transform(input, output);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}