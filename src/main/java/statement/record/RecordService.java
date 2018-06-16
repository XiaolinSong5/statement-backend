package statement.record;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import statement.record.util.CsvReader;
import statement.record.util.XmlReader;

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.util.stream.Collectors;

@Service
public class RecordService {
        private static final Logger LOGGER = LoggerFactory.getLogger(RecordService.class);
    private static final String XMLFILE = "records.xml";
    private static final String CSVFILE = "records.csv";
    @Autowired
    private CsvReader csvReader;
    @Autowired
    private XmlReader xmlReader;

    public List<Record> getRecords()  {
        List<Record> a = new ArrayList<Record>();

//        File file = null;
//         File file1 = null;
//        try {
//            file = new ClassPathResource("records.xml").getFile();
//             file1 = new ClassPathResource("records.csv").getFile();
//        } catch (IOException e) {
//           LOGGER.error("Cannot get file", e);
//        }
        ClassPathResource res = new ClassPathResource(XMLFILE);
        File file = new File(res.getPath());
        List<Record> xmlRecords = xmlReader.getRecords(res.getPath());
         ClassPathResource res1 = new ClassPathResource(CSVFILE);
        List<Record> csvRecords = csvReader.getRecords(res1.getPath());
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
                a.add(record);
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
                a.add(record);
            }
        }
        return a;
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }
}
