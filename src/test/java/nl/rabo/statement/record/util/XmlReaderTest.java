package nl.rabo.statement.record.util;

import nl.rabo.statement.record.Record;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class XmlReaderTest {
    private static final String XMLFILE = "records.xml";
    private XmlReader xmlReader = new XmlReader();

    @Test
    public void testGetRecords() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(XMLFILE).getFile());
        List<Record> b = xmlReader.getRecords(file.getAbsolutePath());
        Assert.assertNotNull(b);
    }
    @Test
    public void testWriteJsonObj() throws IOException {

        JSONObject obj = new JSONObject();

        JSONArray accounts = new JSONArray();
        for (int i = 0; i < 6000; i++) {
            JSONObject account = new JSONObject();
            account.put("iban", "NL89RABO0125021000" + i);
            account.put("accountId", "QVY8XHkdO3v4RjCnygjqx75djAwwjAMpz-7mVWtG1" + i);
            account.put("productName", "Rekening-Courant" + i);
            account.put("accountHolderName", "J. BSBLB NPA TAU" + i);
            accounts.add(account);

        }
        obj.put("accounts", accounts);

        // try-with-resources statement based on post comment below :)
        try (FileWriter jsonfile = new FileWriter("/Users/xiaolinsong/front-end/file1.txt")) {
            jsonfile.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }
    }

}