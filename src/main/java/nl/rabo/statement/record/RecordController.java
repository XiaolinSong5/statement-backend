package nl.rabo.statement.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @RequestMapping("/") @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Set<Record>> getRecords() {
        return new ResponseEntity<>(recordService.getRecords(), HttpStatus.OK);
    }

}
