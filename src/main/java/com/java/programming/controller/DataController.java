package com.java.programming.controller;

import com.java.programming.entity.DataEntity;
import com.java.programming.response.ResponseMessage;
import com.java.programming.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/fileservice")
@CrossOrigin("*")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> uploadFile() throws IOException {


        ClassPathResource resource = new ClassPathResource("central_west.csv");

        if (resource.exists()) {
            try {
                dataService.processAndSaveData(resource.getFile());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Uploaded file sucessfully"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Please upload a CSV file"));
    }

    @GetMapping("/all")
    public List<DataEntity> getAllData(){
      return dataService.getAllData();
    }

}