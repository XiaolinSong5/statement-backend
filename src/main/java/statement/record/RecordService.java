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
        List<Integer> references = new ArrayList();
        xmlRecords.stream().forEach(record -> references.add(record.getReference()));
        Set<Integer> set = new HashSet<>();
        Set<Integer> duplicateElements = new HashSet<>();

        for (Integer element : references) {
            if (!set.add(element)) {
                duplicateElements.add(element);
            }
        }
        for (Record record : xmlRecords) {
            if (duplicateElements.contains(record.getReference())) {
                invalidrecords.add(record);
            } else if (set.contains(record.getReference())) {
                          if ( !Validator.format(record.getEndBalance()) ){
                                invalidrecords.add(record);
                          }
            }
        }

        List<Integer> csvreferences = new ArrayList();
        csvRecords.stream().forEach(record -> csvreferences.add(record.getReference()));
        Set<Integer> setcsv = new HashSet<>();
        Set<Integer> duplicatecsv = new HashSet<>();

        for (Integer element : csvreferences) {
            if (!setcsv.add(element)) {
                duplicatecsv.add(element);
            }
        }
        for (Record record : csvRecords) {
            if (duplicatecsv.contains(record.getReference())) {
                invalidrecords.add(record);
            }
            else if (setcsv.contains(record.getReference())){
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