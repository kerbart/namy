package com.kerbart.namy.controller;

import com.kerbart.namy.model.*;
import com.kerbart.namy.model.dto.LikeDto;
import com.kerbart.namy.model.dto.LikeResponse;
import com.kerbart.namy.repository.mongo.FirstNameRepository;
import com.kerbart.namy.repository.mongo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@Slf4j
@RequestMapping("/api")
public class NamyContoller {


    @Autowired
    FirstNameRepository firstNameRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    MongoTemplate mongoTemplate;


    @GetMapping(value = "/find/{firstName}", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public Firstname firstName(@PathVariable("firstName") String firstName) {
        List<Firstname> firstNames = firstNameRepository
                .findByValue(StringUtils.stripAccents(firstName).toUpperCase());

        firstNames.sort((f1, f2) -> f1.findBestyear().getNumber() < f2.findBestyear().getNumber() ? 1 : -1);


        return firstNames.size() > 0 ? firstNames.get(0) : null;
    }


    @GetMapping(value = "/random", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Firstname> random() {

        SampleOperation sampleStage = Aggregation.sample(50);
        Aggregation aggregation = Aggregation.newAggregation(sampleStage);
        AggregationResults<Firstname> output = mongoTemplate.aggregate(aggregation, "firstname", Firstname.class);

        return output.getMappedResults();
    }

    @GetMapping(value = "/random/florent", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Firstname> florent() {

        List<Firstname> names = firstNameRepository.findByValueLike("FLOR");
        Collections.shuffle(names);
        return names.subList(0, names.size() >= 100 ? 100 : names.size());
    }

    @GetMapping(value = "/random/brice", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Firstname> brice() {

        final List<String> briceList = Arrays.asList("Jacquie", "Rocco", "Ovidie", "Zenza", "titof", "Brice", "Katsuni", "Tabatha", "Brigitte", "Michel", "Clara", "Oceane", "Zahia", "Angela", "Kendra");

        List<Firstname> names = new ArrayList<>();
        for (String name : briceList) {
            Firstname found = this.firstName(name);
            if (found != null) {
                names.add(found);
            }
        }

        names.add(Firstname.builder().value("Stormy Daniels".toUpperCase())
                .id(UUID.randomUUID().toString())
                .metadata(Metadata.builder().female(true).build())
                .bestYear(Arrays.asList(BestYear.builder().date(LocalDate.now()).number(69L).build())).build());

        names.add(Firstname.builder().value("Nikki Benz".toUpperCase())
                .id(UUID.randomUUID().toString())
                .metadata(Metadata.builder().female(true).build())
                .bestYear(Arrays.asList(BestYear.builder().date(LocalDate.now()).number(69L).build())).build());

        names.add(Firstname.builder().value("Laure Sainclair".toUpperCase())
                .id(UUID.randomUUID().toString())
                .metadata(Metadata.builder().female(true).build())
                .bestYear(Arrays.asList(BestYear.builder().date(LocalDate.now()).number(69L).build())).build());

        names.add(Firstname.builder().value("Jacquie Michel".toUpperCase())
                .id(UUID.randomUUID().toString())
                .metadata(Metadata.builder().female(true).male(true).build())
                .bestYear(Arrays.asList(BestYear.builder().date(LocalDate.now()).number(69L).build())).build());


        Collections.shuffle(names);
        return names;
    }


    @PostMapping(value = "/like", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public LikeResponse like(@RequestBody LikeDto likeDto) {
        Validate.notEmpty(userRepository.findById(likeDto.getUserId()));
        Validate.notEmpty(firstNameRepository.findById(likeDto.getFirstNameId()));

        UserLike userLike = UserLike.builder()
                .clientUUID(likeDto.getUserId())
                .firstNameUUID(likeDto.getFirstNameId())
                .likeType(likeDto.getLikeType()).build();

        mongoTemplate.save(userLike);
        return LikeResponse.builder().message("UserLike saved").build();
    }


    @GetMapping(value = "/user/register", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public User newUser() {
        return mongoTemplate.save(new User());
    }
}

