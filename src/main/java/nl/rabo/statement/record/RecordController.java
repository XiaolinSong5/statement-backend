package nl.rabo.statement.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class RecordController {
    private RecordService recordService;

    @Autowired
    RecordController(RecordService recordService) {
        Assert.notNull(recordService, "RecordService should be provided.");
        this.recordService = recordService;
    }
    @GetMapping(value = "/")
    public @ResponseBody Set<Record> getRecords() {
        return recordService.getRecords();
    }
    @GetMapping(value = "/xmlrecords")
    public @ResponseBody List<Record> getXmlrecords() {
        return recordService.getXmlrecords();
    }
    @GetMapping(value = "/{reference}")
    public @ResponseBody Record getRecord(@PathVariable Integer reference) {
        Set<nl.rabo.statement.record.Record> records = recordService.getRecords();
        Optional<nl.rabo.statement.record.Record> record = records.stream()
                .filter(record1 -> record1.getReference().equals(reference))
                .findFirst();

        return record.get();
    }
    @GetMapping(value = "/xmlrecords/{reference}")
    public @ResponseBody Record getXmlRecord(@PathVariable Integer reference) {
        List<Record> records = recordService.getXmlrecords();
        Optional<nl.rabo.statement.record.Record> record = records.stream()
                .filter(record1 -> record1.getReference().equals(reference))
                .findFirst();

        return record.get();
    }
    @GetMapping(value = "api/sample/data")
    public @ResponseBody Greeting getGreeting() {
        Greeting greeting = new Greeting();
        greeting.setSomeDataForTheFrontend("Greet from Fabian, Xiaolin, Ruurd ...");

        return greeting;
    }
}
