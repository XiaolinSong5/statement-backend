package statement.record;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecordService {
    public List<Record> getRecords() {
        List<Record> rs = new ArrayList<>();
        Record record = new Record();
        record.setReference(1L);
        record.setDescription("record1");
        Record record2 = new Record();
        record2.setReference(2L);
        record2.setDescription("record2");
        Record record3 = new Record();
        record3.setReference(3L);
        record3.setDescription("xiaolinrecord3");
        rs.add(record);
        rs.add(record2);
        rs.add(record3);
        //Arrays.asList(rs.toArray());
        return rs;
    }

    public void setRecords(List<Record> records) {
        Records = records;
    }

    private List<Record> Records = new ArrayList<>();

    private List<Symptoms> symptoms = symptoms();

    public List<Symptoms> findAll() {
        return Collections.unmodifiableList(symptoms);
    }

    private static List<Symptoms> symptoms() {
        List<Symptoms> symptoms = Arrays.asList(Symptoms.values());
        symptoms.sort(Comparator.comparingInt(Symptoms::getPriority));
        return symptoms;
    }


}
