package com.kerbart.namy.controller;

import com.kerbart.namy.model.Firstname;
import com.kerbart.namy.model.User;
import com.kerbart.namy.model.UserLike;
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

import java.util.List;

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

