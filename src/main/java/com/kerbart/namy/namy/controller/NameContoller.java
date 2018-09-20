package com.kerbart.namy.namy.controller;

import com.kerbart.namy.namy.model.Firstname;
import com.kerbart.namy.namy.repository.mongo.FirstNameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.Normalizer;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@Slf4j
public class NameContoller {


    @Autowired
    FirstNameRepository firstNameRepository;


    @Autowired
    MongoTemplate mongoTemplate;


    @GetMapping(value = "/find/{firstName}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Firstname firstName(@PathVariable("firstName") String firstName) {
        List<Firstname> firstNames = firstNameRepository
                .findByValue(Normalizer.normalize(firstName, Normalizer.Form.NFD).toUpperCase());

        firstNames.sort((f1, f2) -> f1.findBestyear().getNumber() < f2.findBestyear().getNumber() ? 1 : -1);


        return firstNames.size() > 0 ? firstNames.get(0) : null;
    }


    @GetMapping(value = "/random", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Firstname> random() {

        SampleOperation sampleStage = Aggregation.sample(100);
        Aggregation aggregation = Aggregation.newAggregation(sampleStage);
        AggregationResults<Firstname> output = mongoTemplate.aggregate(aggregation, "firstname", Firstname.class);

        return output.getMappedResults();
    }


}

