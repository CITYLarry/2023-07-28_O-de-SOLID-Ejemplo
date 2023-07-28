package com.santiagoposada.restlibrarymongo.respository;

import com.santiagoposada.restlibrarymongo.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
