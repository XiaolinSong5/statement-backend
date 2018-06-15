package statement.record.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import statement.record.Record;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlReader {
    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("/Users/xiaolinsong/dev/statementbackend/src/main/resources/records.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("record");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                List references = new ArrayList();


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Record record = new Record();

                    System.out.println("Record reference : " + eElement.getAttribute("reference"));
                    record.setReference(Long.valueOf(eElement.getAttribute("reference")));
                    System.out.println("accountNumber : " + eElement.getElementsByTagName("accountNumber").item(0).getTextContent());
                    record.setAccountNumber(eElement.getElementsByTagName("accountNumber").item(0).getTextContent());
                    System.out.println("description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
                    record.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("startBalance : " + eElement.getElementsByTagName("startBalance").item(0).getTextContent());
                    record.setStartBalance(eElement.getElementsByTagName("startBalance").item(0).getTextContent());
                    System.out.println("mutation : " + eElement.getElementsByTagName("mutation").item(0).getTextContent());
                    record.setMutation(eElement.getElementsByTagName("mutation").item(0).getTextContent());
                    System.out.println("endBalance : " + eElement.getElementsByTagName("endBalance").item(0).getTextContent());
                    record.setEndBalance(eElement.getElementsByTagName("endBalance").item(0).getTextContent());
                    validateCurrency (record.getEndBalance());
                    System.out.println("end " );
                    if (!(references.isEmpty() && references.contains(record.getReference()))) {
                        System.out.println("Reference is nog unique");
                    }
                }
            }
            writehtm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void validateCurrency (String endbalance) {
        String money="23.234,00";
        Pattern p=Pattern.compile("^(?:0|[1-9]\\d{0,2}(?:\\.\\d{3})*).\\d{2}$");
        Matcher m=p.matcher(endbalance);
        if (m.matches()) {
            System.out.println("valid");
        }
        else {
            System.out.println("unvalid");
        }
    }
    private static void writehtm() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html lang=\"en\">");
        sb.append("<head>");
        sb.append("<meta charset=\"UTF-8\">");
        sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        sb.append("<meta name=\"generator\" content=\"Asciidoctor 1.5.2\">");
        sb.append("<title>Rabobank Customer Statement Processor for failed records</title>");
        sb.append("<link rel=\"stylesheet\" href=\"style-googleapis.css\">");
        sb.append("  <link rel=\"stylesheet\" href=\"style.css\">");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body class=\"article\">");
        sb.append("<div id=\"header\">\n" +
                "<h1>Rabobank Customer Statement Processor</h1>\n" +
                "</div>\n" +
                "<div id=\"content\">\n" +
                "<div id=\"preamble\">\n" +
                "<div class=\"sectionbody\">\n" +
                "<div class=\"paragraph\">\n" +
                "<p>Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"sect1\">\n" +
                "<h2 id=\"_input\">Input</h2>\n" +
                "<div class=\"sectionbody\">\n" +
                "<div class=\"paragraph\">\n" +
                "<p>The format of the file is a simplified format of the MT940 format. The format is as follows:</p>\n" +
                "</div>\n" +
                "<table class=\"tableblock frame-all grid-all spread\">\n" +
                "<caption class=\"title\">Table 1. Record description</caption>\n" +
                "<colgroup>\n" +
                "<col style=\"width: 50%;\">\n" +
                "<col style=\"width: 50%;\">\n" +
                "</colgroup>\n" +
                "<thead>\n" +
                "<tr>\n" +
                "<th class=\"tableblock halign-left valign-top\">Field</th>\n" +
                "<th class=\"tableblock halign-left valign-top\">Description</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">Transaction reference</p></td>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">A numeric value</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">Account number</p></td>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">An IBAN</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">Start Balance</p></td>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">The starting balance in Euros</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">Mutation</p></td>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">Either and addition (+) or a deducation (-)</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">Description</p></td>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">Free text</p></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">End Balance</p></td>\n" +
                "<td class=\"tableblock halign-left valign-top\"><p class=\"tableblock\">The end balance in Euros</p></td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"sect1\">\n" +
                "<h2 id=\"_output\">Output</h2>\n" +
                "<div class=\"sectionbody\">\n" +
                "<div class=\"paragraph\">\n" +
                "<p>There are two validations:</p>\n" +
                "</div>\n" +
                "<div class=\"ulist\">\n" +
                "<ul>\n" +
                "<li>\n" +
                "<p>all transaction references should be unique</p>\n" +
                "</li>\n" +
                "<li>\n" +
                "<p>the end balance needs to be validated</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"paragraph\">\n" +
                "<p>At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"footer\">\n" +
                "<div id=\"footer-text\">\n" +
                "Last updated 2015-09-08 17:08:00 CEST\n" +
                "</div>\n" +
                "</div>");
        sb.append("</body>");
        sb.append("</html>");
        FileWriter fstream = new FileWriter("/Users/xiaolinsong/xsdev/statement/src/main/resources/failedrecords.html");
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(sb.toString());
        out.close();

    }

}
