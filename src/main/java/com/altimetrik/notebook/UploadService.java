package com.altimetrik.notebook;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

@Service
@AllArgsConstructor
public class UploadService {
    public static final String PREFIX = "stream2file";
    RestTemplate restTemplate;

    public static FileSystemResource streamToFile(InputStream in, String suffix) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, suffix);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return new FileSystemResource(tempFile);
    }

    public JsonNode uploadDocument(MultipartFile multipartFile, String procedureName) throws IOException {
        String serverUrl = "http://0fbb85dab1274bbba38e791cc2740e1c-1796465238.us-east-1.elb.amazonaws.com:5200/vcqi/get_procedure_details?procedure_type=" + procedureName;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", streamToFile(multipartFile.getInputStream(), "." + multipartFile.getOriginalFilename().split("\\.")[1]));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(serverUrl, requestEntity, JsonNode.class);
    }
}
