package com.pablodll.ingestion_service.runner;

import com.pablodll.ingestion_service.service.CountryIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CountryIngestionRunner implements CommandLineRunner {

    private final CountryIngestionService ingestionService;

    @Autowired
    public CountryIngestionRunner(CountryIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @Override
    public void run(String... args) {
        System.out.println("INGESTION PROCESS STARTED");
        ingestionService.ingestCountries();
        System.out.println("INGESTION PROCESS ENDED");
        System.exit(0);
    }
}