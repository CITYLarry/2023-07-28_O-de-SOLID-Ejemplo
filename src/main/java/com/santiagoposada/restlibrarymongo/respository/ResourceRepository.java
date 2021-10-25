package com.santiagoposada.restlibrarymongo.respository;

import com.santiagoposada.restlibrarymongo.entity.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResourceRepository extends MongoRepository<Resource, String> {
    List<Resource> findByType(final String type);
    List<Resource> findByCategory(final String category);
}
