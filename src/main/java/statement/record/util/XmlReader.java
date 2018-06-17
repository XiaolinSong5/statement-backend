package statement.record.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import statement.record.Record;

import javax.validation.constraints.NotNull;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class XmlReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlReader.class);

    public List<Record> getRecords(@NotNull String filePath) {
        List<Record> records = new ArrayList<>();
        try {
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("record");

            Stream<Node> nodeStream = IntStream.range(0, nList.getLength())
                    .mapToObj(nList::item);
            nodeStream.forEach(nNode -> {
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Record record = convertNodeToRecord(nNode);
                    records.add(record);

                }
            });

        } catch (Exception e) {
            LOGGER.error("Reading xml file failed", e);
        }
        return records;
    }

    private Record convertNodeToRecord(Node nNode) {
        Record record = new Record();
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            record.setReference(Integer.valueOf(eElement.getAttribute("reference")));
            record.setAccountNumber(eElement.getElementsByTagName("accountNumber").item(0).getTextContent());
            record.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
            record.setStartBalance(eElement.getElementsByTagName("startBalance").item(0).getTextContent());
            record.setMutation(eElement.getElementsByTagName("mutation").item(0).getTextContent());
            record.setEndBalance(eElement.getElementsByTagName("endBalance").item(0).getTextContent());

        }
        return record;
    }
}
