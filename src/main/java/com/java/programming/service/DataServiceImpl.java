package com.java.programming.service;

import com.java.programming.entity.DataEntity;
import com.java.programming.repo.DataRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepo dataRepo;

    @Override
    public boolean hasCsvFormat(MultipartFile file) {
        String type = "text/csv";
        if (!type.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    @Override
    public void processAndSaveData(File file) throws IOException {

        try {
            List<DataEntity> dataEntities = csvToDataEntity(new FileInputStream(file));
            dataRepo.saveAll(dataEntities);
            System.out.println("Processing and saving data from filr: " +file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Input/Output exception occured " + getClass());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<DataEntity> getAllData() {
        return dataRepo.findAll();
    }

    private List<DataEntity> csvToDataEntity(InputStream inputStream) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(bufferedReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<DataEntity> dataEntities = new ArrayList<DataEntity>();

//            List<CSVRecord> records = csvParser.getRecords();
            for (CSVRecord csvRecord : csvParser) {
                DataEntity dataEntity = new DataEntity(Long.parseLong(csvRecord.get("index")), csvRecord.get("station_code"), csvRecord.get("Direction"));
                dataEntities.add(dataEntity);
            }
            return dataEntities;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}