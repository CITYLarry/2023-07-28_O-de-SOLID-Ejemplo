package com.santiagoposada.restlibrarymongo.services;

import com.santiagoposada.restlibrarymongo.entity.Resource;
import com.santiagoposada.restlibrarymongo.mapper.ResourceMapper;
import com.santiagoposada.restlibrarymongo.respository.ResourceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ResourceServiceTest {


    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceMapper resourceMapper;

    @MockBean
    private ResourceRepository resourceRepository;

    @Test
    @DisplayName("Get all the resources test")
    void getAllResourcesTest(){
        List<Resource> list = new ArrayList<>();
        list.add(new Resource
                ("1",
                        "Harry Potter",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        5));
        list.add(new Resource
                ("2",
                        "Narnia",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        3));
        list.add(new Resource
                ("3",
                        "Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2));

        Mockito.when(resourceRepository.findAll()).thenReturn(list);
        var result = resourceRepository.findAll();

        Assertions.assertEquals(result.size(), list.size());
        Assertions.assertEquals("Narnia", result.get(1).getName());
        Assertions.assertEquals(3, result.get(1).getUnitsAvailable());
    }

    @Test
    @DisplayName("Get a resource by id")
    void getResourceByIdTest(){

        var resource = new Resource
                ("3",
                        "Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2);
        Mockito.when(resourceRepository.findById("3")).thenReturn(Optional.of(resource));

        var result = resourceService.getResourceById("3");

        Assertions.assertEquals("3", result.getId());
        Assertions.assertEquals("Game of thrones", result.getName());
        Assertions.assertEquals(2, result.getUnitsAvailable());
    }

    @Test
    @DisplayName("Add a resource")
    void addAResource(){
        var resource = new Resource
                ("Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2);

        Mockito.when(resourceRepository.save(Mockito.any())).thenReturn(resource);

        var result = resourceService.saveResource(resourceMapper.fromResourceEntityToDTO(resource));
        Assertions.assertEquals("Game of thrones", result.getName());
        Assertions.assertEquals("Book", result.getType());
        Assertions.assertEquals(2, result.getUnitsAvailable());
    }

    @Test
    @DisplayName("Update a resource")
    void updateAResource(){
        var resource = new Resource
                ("3",
                        "Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2);

        var resourceDto = new Resource
                ("3",
                        "Game of thrones",
                        "History",
                        "Book",
                        null,
                        0,
                        2);

        Mockito.when(resourceRepository.findById("3")).thenReturn(Optional.of(resource));
        Mockito.when(resourceRepository.save(Mockito.any())).thenReturn(resourceDto);

        var result = resourceService.updateResource(resourceMapper.fromResourceEntityToDTO(resourceDto));

        Assertions.assertEquals("History", result.getCategory());
    }

}