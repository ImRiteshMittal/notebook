package com.altimetrik.notebook;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class UploadController {
    UploadService uploadService;

    @PostMapping
    public JsonNode uploadDocument(@RequestPart("file") MultipartFile file,
                                   @RequestParam("procedureName") String procedureName) throws IOException {
        return uploadService.uploadDocument(file, procedureName);
    }


}
