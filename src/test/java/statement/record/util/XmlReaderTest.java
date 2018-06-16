package statement.record.util;

import mockit.Injectable;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import statement.record.Record;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class XmlReaderTest {
    private XmlReader xmlReader= new XmlReader();
    @Test
    public void testGetRecords() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("records.xml").getFile());
        List<Record> b = xmlReader.getRecords(file.getAbsolutePath());
        Assert.assertNotNull(b);
    }

}