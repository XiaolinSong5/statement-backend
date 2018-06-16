package statement.record;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    ResponseEntity<List<Record>> getRecords() {
        return new ResponseEntity<List<Record>>(recordService.getRecords(), HttpStatus.OK);
    }

}
