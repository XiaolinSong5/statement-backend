package nl.rabo.statement.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RecordController {
    private RecordService recordService;

    @Autowired
    RecordController(RecordService recordService) {
        Assert.notNull(recordService, "RecordService should be provided.");
        this.recordService = recordService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/")
    public @ResponseBody Set<Record> getRecords() {
        return recordService.getRecords();

    }
}
