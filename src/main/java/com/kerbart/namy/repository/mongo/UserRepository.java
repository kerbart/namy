package com.kerbart.namy.repository.mongo;

import com.kerbart.namy.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findById(String id);
}