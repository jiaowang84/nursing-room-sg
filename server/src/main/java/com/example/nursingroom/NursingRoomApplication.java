package com.example.nursingroom;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class NursingRoomApplication implements CommandLineRunner {

    @Autowired
    private NursingRoomRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(NursingRoomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        Reader inputStreamReader = new InputStreamReader(new ClassPathResource("data.csv").getInputStream());
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(false)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(inputStreamReader)
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();


        String[] line;
        while ((line = csvReader.readNext()) != null) {
            System.out.println(line[0]);
            List facilities = Arrays.asList(line[4].split(","));
            String[] coordinates = line[5].split(",");
            repository.save(new NursingRoom(
                    line[1],
                    line[0],
                    line[2],
                    line[3],
                    facilities,
                    new GeoJsonPoint(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1])),
                    Boolean.parseBoolean(line[4]),
                    Boolean.parseBoolean(line[5]),
                    Boolean.parseBoolean(line[6]),
                    Boolean.parseBoolean(line[7])

            ));
        }

    }

}