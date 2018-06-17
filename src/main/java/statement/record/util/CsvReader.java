package statement.record.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import statement.record.Record;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CsvReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvReader.class);
    private static final String DEFAULT_SEPARATOR = ",";

    public List<Record> getRecords(@NotNull String filePath) {

        List<Record> inputList = new ArrayList<>();

        try {
            File inputF = new File(filePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            LOGGER.error("Reading csv file failed", e);
        }

        return inputList;
    }

    private Function<String, Record> mapToItem = (line) -> {

        String[] p = line.split(DEFAULT_SEPARATOR);// a CSV has comma separated lines
        Record item = new Record();

        item.setReference(Integer.valueOf(p[0]));
        item.setAccountNumber(p[1]);
        item.setStartBalance(p[3]);
        item.setMutation(p[4]);
        item.setDescription(p[2]);
        item.setEndBalance(p[5]);

        return item;
    };

}

