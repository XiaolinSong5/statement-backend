package nl.rabo.statement.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping(value = "api/mydocs/rpks")
    public @ResponseBody List<Rpk> getRpks() {
        List rpks = new ArrayList();
        Rpk rpk = new Rpk();
        rpk.setDocname("tecnation");
        rpk.setDescription("2018 kwartaal 1");
        rpk.setDocurl("/docs/typerpk/tecnation.pdf");
        rpks.add(rpk);
        Rpk rpk2 = new Rpk();
        rpk2.setDocname("invoice");
        rpk2.setDescription("2018 kwartaal 2");
        rpk2.setDocurl("/docs/typerpk/invoice.pdf");
        rpks.add(rpk2);


        return rpks;
    }
    @RequestMapping(value = "api/mydocs/rpk/view/{pdfname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPDFFile(@PathVariable String pdfname)
            throws IOException {

        ClassPathResource pdfFile = new ClassPathResource(pdfname+ ".pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + pdfname);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }
    @RequestMapping(value = "api/msp/mydocs/rpk/member/download/{pdfname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadPDFFile(@PathVariable String pdfname)
            throws IOException {

        ClassPathResource pdfFile = new ClassPathResource("tecnation.pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + pdfname);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }
//    @RequestMapping(value = "/action/{abcd}/{efgh}", method = RequestMethod.GET)
//    @PreAuthorize("@authorizationService.authorizeMethod(#id)")
//    public HttpEntity<byte[]> doAction(@PathVariable ObjectType obj, @PathVariable Date date, HttpServletResponse response) throws IOException {
//        ZipFileType zipFile = service.getFile(obj1.getId(), date);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        response.setHeader("Content-Disposition", "attachment; filename=" + zipFile.getFileName());
//
//        return new HttpEntity<byte[]>(zipFile.getByteArray(), headers);
//    }
}