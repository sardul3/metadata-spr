package com.sagar.metadatagooglesheet.service;

import com.sagar.metadatagooglesheet.model.Metadata;
import com.sagar.metadatagooglesheet.repository.MetadataRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
public class MetadataService {

    private final MetadataRepository metadataRepository;

    public void addMetaDate(Metadata metadata){
        metadataRepository.save(metadata);
    }

    public void saveToDb() {
        try {
            FileReader reader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MMM/yyyy");

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(",");
                Metadata metadata = new Metadata(null, entries[0],entries[1],entries[2],entries[3],entries[4],entries[5],entries[6],entries[7],entries[8],entries[9],entries[10],entries[11],entries[12],entries[13],entries[14],entries[15],entries[16],entries[17], entries[18],entries[19],entries[20],entries[21],entries[22],entries[23],entries[24],entries[25],entries[26],entries[27],entries[28],entries[29],entries[30],entries[31],entries[32],entries[33],entries[34],entries[35],entries[36],entries[37],entries[38],entries[39],entries[40],entries[41],entries[42],entries[43],entries[44],entries[45],entries[46],entries[47],entries[48],entries[49],entries[50],entries[51],entries[52],entries[53],entries[54],entries[55],entries[56],entries[57],entries[58],entries[59], entries[60]);
                metadataRepository.save(metadata);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
