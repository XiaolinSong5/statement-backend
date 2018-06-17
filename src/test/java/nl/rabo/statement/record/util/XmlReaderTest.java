package nl.rabo.statement.record.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import nl.rabo.statement.record.Record;

import java.io.File;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class XmlReaderTest {
    private static final String XMLFILE = "records.xml";
    private XmlReader xmlReader = new XmlReader();

    @Test
    public void testGetRecords() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(XMLFILE).getFile());
        List<Record> b = xmlReader.getRecords(file.getAbsolutePath());
        Assert.assertNotNull(b);
    }

}