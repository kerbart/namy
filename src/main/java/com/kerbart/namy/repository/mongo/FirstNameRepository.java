package com.kerbart.namy.repository.mongo;

import com.kerbart.namy.model.Firstname;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FirstNameRepository extends MongoRepository<Firstname, Long> {

    List<Firstname> findAll();

    List<Firstname> findByValue(String firstname);

    List<Firstname> findByValueLike(String firstname);

    List<Firstname> findById(String id);
}