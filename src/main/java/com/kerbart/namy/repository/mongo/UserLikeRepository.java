package com.kerbart.namy.repository.mongo;

import com.kerbart.namy.model.UserLike;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserLikeRepository extends MongoRepository<UserLike, Long> {

    List<UserLike> findAll();

    List<UserLike> findByClientUUID(String clientUUID);
}