package nl.rabo.statement.record;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {
    @Autowired
    private RecordService recordService;
    @Test
    public void getRecords() {
        Set<Record> records = recordService.getRecords();
        Assert.assertNotNull(records);
    }
}