package com.santiagoposada.restlibrarymongo.controller;


import com.santiagoposada.restlibrarymongo.dto.ResourceDTO;
import com.santiagoposada.restlibrarymongo.entity.Resource;
import com.santiagoposada.restlibrarymongo.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceByID(@PathVariable("id") String id){
        return new ResponseEntity(resourceService.getResourceById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ResourceDTO>> getAllResources(){
        return new ResponseEntity<List<ResourceDTO>>(resourceService.getAllResources(), HttpStatus.OK);
    }

    @GetMapping("/add")
    public ResponseEntity<ResourceDTO> add(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity(resourceService.saveResource(resourceDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResourceDTO> update(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity<ResourceDTO>(resourceService.updateResource(resourceDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        resourceService.deleteResource(id);
        return new ResponseEntity("The book with id: " + id + "has been deleted", HttpStatus.OK);
    }

    @GetMapping("/isAvailable/{id}")
    public ResponseEntity checkForAvailablility(@PathVariable("id") String id){
        return new ResponseEntity(resourceService.isAvailable(id), HttpStatus.OK);
    }

    @PutMapping("/borrow/{id}")
    public ResponseEntity borrowResource(@PathVariable("id") String id){
        return new ResponseEntity(resourceService.borrowABook(id), HttpStatus.OK);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity returnResource(@PathVariable("id") String id){
        return new ResponseEntity(resourceService.returnABook(id), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity listByCategory(@PathVariable("category") String category){
        return new ResponseEntity(resourceService.getResourceByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity listByType(@PathVariable("type") String type){
        return new ResponseEntity(resourceService.getResourcesByType(type), HttpStatus.OK);
    }



}
