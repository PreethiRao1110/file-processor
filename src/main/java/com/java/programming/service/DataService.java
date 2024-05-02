package com.java.programming.service;

import com.java.programming.entity.DataEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DataService {


    boolean hasCsvFormat(MultipartFile file);

    void processAndSaveData(File file) throws IOException;

    public List<DataEntity> getAllData();
}
