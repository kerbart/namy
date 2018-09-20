package com.kerbart.namy.namy;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.kerbart.namy.namy.repository.mongo")
public class NamyConfiguration {

    //MongoDB properties read from the application.yaml configuration file (to handle different profiles)
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo(), mongoDatabase);
        MappingMongoConverter mongoMapping = (MappingMongoConverter) mongoTemplate.getConverter();
        return mongoTemplate;
    }

    /**
     * Configure the MongoDB client
     **/
    @Bean
    public MongoClient mongo() throws Exception {
        return new MongoClient(mongoHost, mongoPort);
    }

}
