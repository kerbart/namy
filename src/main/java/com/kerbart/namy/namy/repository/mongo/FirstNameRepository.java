package com.kerbart.namy.namy.repository.mongo;

import com.kerbart.namy.namy.model.Firstname;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FirstNameRepository extends MongoRepository<Firstname, Long> {

    List<Firstname> findAll();

    List<Firstname> findByValue(String firstname);
}