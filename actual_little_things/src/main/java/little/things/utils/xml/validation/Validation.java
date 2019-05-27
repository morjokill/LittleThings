package little.things.utils.xml.validation;

import little.things.utils.reader.FileReader;
import little.things.utils.xml.parse.XmlParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class Validation {
    public static void validateXmlWithXsdSchema(Document document, String pathToSchema) throws IOException, SAXException {
        File schemaLocation = new File(pathToSchema);
        SchemaFactory factory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source xmlFile = new DOMSource(document);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println("Xsd validation passed. Xml document with systemId: " + xmlFile.getSystemId() + " is valid");
        } catch (SAXException saxe) {
            System.out.println("Xml document with systemId: " + xmlFile.getSystemId() + " is NOT valid: " + saxe);
            throw saxe;
        } catch (IOException ioe) {
            System.out.println("IO exception while validating xml: " + ioe);
            throw ioe;
        }
    }

    public static void main(String[] args) throws Exception {
        String xml = FileReader.readFile("actual_little_things\\src\\main\\resources\\order.xml");
        Document document = XmlParser.loadDocumentFromString(xml);
        validateXmlWithXsdSchema(document, "actual_little_things\\src\\main\\resources\\order.xsd");
    }
}
