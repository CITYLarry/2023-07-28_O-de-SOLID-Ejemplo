package com.santiagoposada.restlibrarymongo.services;


import com.santiagoposada.restlibrarymongo.dto.ResourceDTO;
import com.santiagoposada.restlibrarymongo.entity.Resource;
import com.santiagoposada.restlibrarymongo.mapper.ResourceMapper;
import com.santiagoposada.restlibrarymongo.respository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    private ResourceMapper resourceMapper = new ResourceMapper();

    public List<ResourceDTO> getAllResources(){
        List<ResourceDTO> listDto = new ArrayList<>();
        return resourceMapper.fromEntityListToDtoList(resourceRepository.findAll());
    }

    public ResourceDTO getResourceById(Integer id){
        Objects.requireNonNull(id);
        Resource resource = resourceRepository.findById(id).orElseThrow(()-> new RuntimeException("The resource with the id provided wasnt find"));
        return resourceMapper.fromResourceEntityToDTO(resource);
    }

    public ResourceDTO saveResource(ResourceDTO resourceDTO){
        return resourceMapper
                .fromResourceEntityToDTO(resourceRepository
                        .save(resourceMapper
                                .fromResourceDTOtoEntity(resourceDTO)));
    }

    public ResourceDTO updateResource(ResourceDTO resourceDTO){
        Resource resource = resourceMapper.fromResourceDTOtoEntity(resourceDTO);
        resourceRepository.findById(resourceDTO.getId())
                .orElseThrow(()-> new RuntimeException("The resource tha you want to update couldnt been found"));
        return resourceMapper.fromResourceEntityToDTO(resourceRepository.save(resource));
    }

    public void deleteResource(Integer id){
        Objects.requireNonNull(id);
        resourceRepository.deleteById(id);
    }

    public boolean isAvailable(Integer id){
        Objects.requireNonNull(id);
        if(getResourceById(id).getUnitsAvailable() > 0){
            return true;
        }
        return false;
    }

    public List<ResourceDTO> getResourcesByType(String type){
        Objects.requireNonNull(type);
        List<Resource> list = resourceRepository.findByType(type);
        return resourceMapper.fromEntityListToDtoList(list);
    }

    public List<ResourceDTO> getResourceByCategory(String category){
        Objects.requireNonNull(category);
        List<Resource> list = resourceRepository.findByCategory(category);
        return resourceMapper.fromEntityListToDtoList(list);
    }

    public ResourceDTO borrowABook(Integer id){
        Objects.requireNonNull(id);
        if(!isAvailable(id)){
            new IllegalArgumentException("The resource that wants to be borrowed isnt available");
        }
        Resource resource = resourceRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Couldnt find the resource that is requested to be borrowed"));

        resource.setUnitsOwed(resource.getUnitsOwed() + 1);
        resource.setUnitsAvailable(resource.getUnitsAvailable() - 1);
        resource.setLastBorrow(LocalDate.now());
        return resourceMapper.fromResourceEntityToDTO(resourceRepository.save(resource));
    }

    public ResourceDTO returnABook(Integer id){
        Objects.requireNonNull(id);
        if(!isAvailable(id)){
            new IllegalArgumentException("The resource that wants to be returned doesnt exist");
        }
        Resource resource = resourceRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Couldnt find the resource that is requested to be returned"));

        resource.setUnitsOwed(resource.getUnitsOwed() - 1);
        resource.setUnitsAvailable(resource.getUnitsAvailable() + 1);
        return resourceMapper.fromResourceEntityToDTO(resourceRepository.save(resource));
    }


}
