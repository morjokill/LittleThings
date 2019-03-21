package site_parse;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static final String SITE = "https://online.anidub.com";

    public static void main(String[] args) {
        try {
            List<String> links = new LinkedList<>();
            XPath xpath = XPathFactory.newInstance().newXPath();
            for (int i = 1; i <= 5; i++) {
                String page = "/page/" + i + "/";
                Document doc = getDocument(SITE + page);

                for (int r = 1; r <= 30; r++) {
                    String expr = "//*[@id=\"dle-content\"]/div[" + r + "]/div/h2/a/@href";
                    String link = (String) xpath.evaluate(expr, doc, XPathConstants.STRING);
                    if (!link.isEmpty()) {
                        links.add(link);
                    }
                }
            }

            System.out.println("Links: " + links.size());

            for (String link : links) {

                System.out.println("LINK: " + link);

                Document document = getDocument(link);
                String title = ((String) xpath.evaluate("//*[@id=\"dle-content\"]/div[1]/div[1]/div/div/h1", document, XPathConstants.STRING)).trim();
                System.out.println("TITLE: " + title);

                String tags = ((String) xpath.evaluate("//*[@id=\"dle-content\"]/div[1]/div[4]/div[1]/div[3]/div[6]/div", document, XPathConstants.STRING)).trim();
                tags = tags.replace("Теги:", "");
                System.out.println("KEYWORDS1: " + tags);

                String body = ((String) xpath.evaluate("normalize-space(//*[@id=\"dle-content\"]/div/div[4]/div[1]/div/div[3])", document, XPathConstants.STRING)).trim();
                body = body.replace("Описание:", "");
                body = body.replace("Подпишись на  наш канал @anidub  Telegram и получай новости обновлений релизов", "");
                System.out.println("BODY: " + body);
                System.out.println();

                Site site = new Site(title, link, tags, body);
                Dao.insertRecordIntoTable(site);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Document getDocument(String link) throws IOException, ParserConfigurationException {
        URLConnection connection;
        String content;
        connection = new URL(link).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36");
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        content = scanner.next();
        scanner.close();

        TagNode tagNode = new HtmlCleaner().clean(content);
        return new DomSerializer(
                new CleanerProperties()).createDOM(tagNode);
    }
}
