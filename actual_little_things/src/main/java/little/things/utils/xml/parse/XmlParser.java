package little.things.utils.xml.parse;

import little.things.utils.reader.FileReader;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

public class XmlParser {
    public static String getXmlStringFromDocument(Document document) throws Exception {
        try {
            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException te) {
            throw new Exception("Could not get xml string from document: ", te);
        }
    }

    public static Document loadDocumentFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    }

    public static Node findNode(Document document, String xPathExpression) throws DOMException, XPathExpressionException {
        Node firstNode = null;
        NodeList nodes = findNodes(document, xPathExpression);
        if (nodes.getLength() != 0) {
            firstNode = nodes.item(0);
        }

        return firstNode;
    }

    private static NodeList findNodes(Document document, String xpathExpression) throws DOMException, XPathExpressionException {
        XPathExpression expr = compileXpathExpression(xpathExpression);
        return (NodeList) expr.evaluate(document, XPathConstants.NODESET);
    }

    private static XPathExpression compileXpathExpression(String xpathExpression) throws XPathExpressionException {
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        return xpath.compile(xpathExpression);
    }

    public static void main(String[] args) throws Exception {
        String xml = FileReader.readFile("actual_little_things\\src\\main\\resources\\order.xml");
        Document document = XmlParser.loadDocumentFromString(xml);
        Node node = findNode(document, "//*[local-name()='city']");
        System.out.println(node);
        System.out.println(node.getTextContent());
    }
}
