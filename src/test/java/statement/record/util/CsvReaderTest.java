package statement.record.util;

import org.junit.Assert;
import org.junit.Test;

import statement.record.Record;

import java.io.File;
import java.util.List;


public class CsvReaderTest {

private CsvReader csvReader = new CsvReader();;
    @Test
    public void testGetRecords() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("records.csv").getFile());
        List<Record> b = csvReader.getRecords(file.getAbsolutePath());
        Assert.assertNotNull(b);
    }
}