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

    public Set<Record> getRecords() {
        List<Record> totalrecords = new ArrayList<Record>();
        totalrecords.addAll(xmlReader.getRecords(getFilepath(XMLFILE)));
        LOGGER.info("XML records retrieved");
        totalrecords.addAll(csvReader.getRecords(getFilepath(CSVFILE)));
        LOGGER.info("CSV records retrieved");

        return getInvalidRecords(totalrecords);
    }
    private Set<Record> getInvalidRecords(List<Record> records) {
        Set<Record> invalidrecords = new HashSet<>();

        List<Integer> references = new ArrayList();
        records.stream().forEach(record -> references.add(record.getReference()));
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        references.stream().forEach(element ->{
            if (!set.add(element)) {
                duplicates.add(element);
            }
        });

        records.stream().forEach(record -> {
            if (duplicates.contains(record.getReference())) {
                invalidrecords.add(record);
            }
            else if (set.contains(record.getReference())){
                if(!Validator.valdateFormat(record.getEndBalance())){
                    invalidrecords.add(record);
                }
            }
        });
        return invalidrecords;
    }

    private String getFilepath(String fileName) {
        ClassPathResource res = new ClassPathResource(fileName);
        return res.getPath();
    }
}