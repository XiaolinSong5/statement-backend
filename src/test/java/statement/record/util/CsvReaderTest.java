package statement.record.util;

import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;

import statement.record.Record;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class CsvReaderTest {

private CsvReader csvReader;
    @Test
    public void testGetRecords() {
        CsvReader  csvReader = new CsvReader();
        List<Record> b = csvReader.getRecords("/Users/xiaolinsong/xsdev/statement-backend/src/main/resources/records.csv");
        Assert.assertNotNull(b);
    }
}