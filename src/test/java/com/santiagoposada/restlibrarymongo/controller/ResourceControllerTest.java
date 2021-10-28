package com.santiagoposada.restlibrarymongo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiagoposada.restlibrarymongo.dto.ResourceDTO;
import com.santiagoposada.restlibrarymongo.entity.Resource;
import com.santiagoposada.restlibrarymongo.mapper.ResourceMapper;
import com.santiagoposada.restlibrarymongo.services.ResourceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ResourceControllerTest {

    @MockBean
    private ResourceService resourceService;


    @Autowired
    private MockMvc mockMvc;

    private ResourceMapper resourceMapper = new ResourceMapper();

    private static String asJsonString(ResourceDTO resourceDTO) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(resourceDTO);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("GET /resource success")
    void testGetResourceSuccess() throws Exception {
        var resource1 = new Resource
                ("3",
                        "Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2);

        var resource2 = new Resource
                ("4",
                        "Harry potter",
                        "History",
                        "Book",
                        null,
                        0,
                        3);
        doReturn(List.of(resource1, resource2)).when(resourceService).getAllResources();

        mockMvc.perform(get("/library"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("3")))
                .andExpect(jsonPath("$[0].name", is("Game of thrones")))
                .andExpect(jsonPath("$[0].category", is("SciPhi")));
    }

    @Test
    @DisplayName("post /library/add success")
    void testPostResourceSuccess() throws Exception {
        var resource1 = new Resource
                ("3",
                        "Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2);
        var resourceDTO = resourceMapper.fromResourceEntityToDTO(resource1);

        doReturn(resourceDTO)
                .when(resourceService)
                .saveResource(resourceDTO);
//asJsonString(widgetToPost)
        mockMvc.perform(MockMvcRequestBuilders.post("/library/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(resourceDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("3")))
                .andExpect(jsonPath("$.name", is("Game of thrones")))
                .andExpect(jsonPath("$.category", is("SciPhi")));
    }

    @Test
    @DisplayName("put /library/update success")
    void testPutResourceSuccess() throws Exception {
        var resource1 = new Resource
                ("3",
                        "Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2);
        var resourceDTO = resourceMapper.fromResourceEntityToDTO(resource1);

        doReturn(resourceDTO)
                .when(resourceService)
                .updateResource(resourceDTO);
//asJsonString(widgetToPost)
        mockMvc.perform(MockMvcRequestBuilders.put("/library/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(resourceDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("3")))
                .andExpect(jsonPath("$.name", is("Game of thrones")))
                .andExpect(jsonPath("$.category", is("SciPhi")));
    }

    @Test
    @DisplayName("get /library/id success")
    void testGetByIdResourceSuccess() throws Exception {
        var resource1 = new Resource
                ("3",
                        "Game of thrones",
                        "SciPhi",
                        "Book",
                        null,
                        0,
                        2);
        var resourceDTO = resourceMapper.fromResourceEntityToDTO(resource1);

        doReturn(resourceDTO)
                .when(resourceService)
                .getResourceById(any(String.class));
//asJsonString(widgetToPost)
        mockMvc.perform(MockMvcRequestBuilders.get("/library/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("3")))
                .andExpect(jsonPath("$.name", is("Game of thrones")))
                .andExpect(jsonPath("$.category", is("SciPhi")));
    }



}