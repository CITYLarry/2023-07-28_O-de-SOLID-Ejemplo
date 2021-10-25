package com.santiagoposada.restlibrarymongo.mapper;


import com.santiagoposada.restlibrarymongo.dto.ResourceDTO;
import com.santiagoposada.restlibrarymongo.entity.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ResourceMapper {

    public Resource fromResourceDTOtoEntity(ResourceDTO resourceDTO){
        Resource resource = new Resource();
        resource.setId(resourceDTO.getId());
        resource.setName(resourceDTO.getName());
        resource.setCategory(resourceDTO.getCategory());
        resource.setType(resourceDTO.getCategory());
        resource.setLastBorrow(resourceDTO.getLastBorrow());
        resource.setUnitsAvailable(resourceDTO.getUnitsAvailable());
        resource.setUnitsOwed(resourceDTO.getUnitsOwed());
        return resource;
    }

    public ResourceDTO fromResourceEntityToDTO(Resource resource){
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(resource.getId());
        resourceDTO.setName(resource.getName());
        resourceDTO.setCategory(resource.getCategory());
        resourceDTO.setType(resource.getType());
        resourceDTO.setLastBorrow(resource.getLastBorrow());
        resourceDTO.setUnitsAvailable(resource.getUnitsAvailable());
        resourceDTO.setUnitsOwed(resource.getUnitsOwed());
        return resourceDTO;
    }

    public List<ResourceDTO> fromEntityListToDtoList(List<Resource> list){
        Objects.requireNonNull(list, "The list cant be null");
        List<ResourceDTO> listDto = new ArrayList<>();
        for(Resource item: list){
            listDto.add(fromResourceEntityToDTO(item));
        }
        return listDto;
    }
}
