package statement.record;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import statement.record.util.CsvReader;
import statement.record.util.Validator;
import statement.record.util.XmlReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordService.class);
    private static final String XMLFILE = "records.xml";
    private static final String CSVFILE = "records.csv";
    @Autowired
    private CsvReader csvReader;
    @Autowired
    private XmlReader xmlReader;

    public List<Record> getRecords() {
        List<Record> invalidrecords = new ArrayList<Record>();
        List<Record> xmlRecords = xmlReader.getRecords(getFilepath(XMLFILE));

        List<Record> csvRecords = csvReader.getRecords(getFilepath(CSVFILE));
        invalidrecords.addAll(getInvalidRecords(xmlRecords));
        invalidrecords.addAll(getInvalidRecords(csvRecords));

        return invalidrecords;
    }
    private List<Record> getInvalidRecords(List<Record> records) {
        List<Record> invalidrecords = new ArrayList<>();

        List<Integer> references = new ArrayList();
        records.stream().forEach(record -> references.add(record.getReference()));
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (Integer element : references) {
            if (!set.add(element)) {
                duplicates.add(element);
            }
        }
        for (Record record : records) {
            if (duplicates.contains(record.getReference())) {
                invalidrecords.add(record);
            }
            else if (set.contains(record.getReference())){
                if(!Validator.format(record.getEndBalance())){
                    invalidrecords.add(record);
                }
            }
        }
        return invalidrecords;
    }

    private String getFilepath(String fileName) {
        ClassPathResource res = new ClassPathResource(fileName);
        return res.getPath();
    }
}