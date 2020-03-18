package com.leroymerlin.pricesofferextractor;

import com.leroymerlin.pricesofferextractor.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class PricesOfferExtractorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricesOfferExtractorApplication.class, args);
    }

}
