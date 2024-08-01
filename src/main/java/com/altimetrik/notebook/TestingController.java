package com.altimetrik.notebook;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class TestingController {

    public static final String PREFIX = "stream2file";

    public static FileSystemResource stream2file(InputStream in, String suffix) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, suffix);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }

    @PostMapping("/scan")
    public ResponseEntity<String> getScan(@RequestParam("procedureId") String procedureId,
                                          @RequestParam("procedureName") String procedureName,
                                          @RequestPart("file") MultipartFile file) throws IOException {

        String output = uploadDocument(file, procedureName);

        return new ResponseEntity<>(output, HttpStatus.CREATED);

    }

    public String uploadDocument(MultipartFile multipartFile, String procedureName) throws IOException {
        String serverUrl = "http://0fbb85dab1274bbba38e791cc2740e1c-1796465238.us-east-1.elb.amazonaws.com:5200/vcqi/get_procedure_details?procedure_type=" + procedureName;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", stream2file(multipartFile.getInputStream(), "." + multipartFile.getOriginalFilename().split("\\.")[1]));

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = new RestTemplate()
                .postForEntity(serverUrl, requestEntity, String.class);
        return response.getBody();
    }
}
