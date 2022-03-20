package com.springboot.springweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springweb.entity.Bird;
import com.springboot.springweb.repository.BirdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = {BirdSightrestapiApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles(value = "local")
class BirdControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BirdRepository birdRepository;

    @Test
    void getAllBirds() throws Exception {
        when(birdRepository.findAll()).thenReturn(new ArrayList<Bird>());
        this.mockMvc.perform(get("/bird/")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getBirdById() throws Exception {
        long birdId = 1;
        when(birdRepository.findById(birdId)).thenReturn(Optional.of(new Bird()));
        this.mockMvc.perform(get("/bird/" + birdId)).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void getBirdByName() throws Exception {
        String name = "American Black Duck";

        Bird bird = new Bird();
        bird.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Bird> example = Example.of(bird, matcher);
        when(birdRepository.findAll(example)).thenReturn(new ArrayList<Bird>());
        this.mockMvc.perform(get("/bird/name/" + name)).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void getBirdByColor() throws Exception {
        String color = "American Black Duck";

        Bird bird = new Bird();
        bird.setColor(color);

        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Bird> example = Example.of(bird, matcher);
        when(birdRepository.findAll(example)).thenReturn(new ArrayList<Bird>());
        this.mockMvc.perform(get("/bird/name/" + color)).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    void getBirdByNameColor() throws Exception {
        String name = "American Black Duck";
        String color = "Red";

        Bird bird = new Bird();
        bird.setName(name);
        bird.setColor(color);

        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Bird> example = Example.of(bird, matcher);
        when(birdRepository.findAll(example)).thenReturn(new ArrayList<Bird>());
        this.mockMvc.perform(get("/bird/name/" + name + "/color/" + color)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createBird() throws Exception {
        String name = "American Black Duck";
        Bird bird = new Bird();
        bird.setName(name);
        bird.setColor("frank 76");
        bird.setHeight(1234);
        bird.setWeight(5678);
        when(birdRepository.save(bird)).thenReturn(new Bird());
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/bird/")
                        .content(asJsonString(bird))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateBird() throws Exception {
        String name = "American Black Duck";
        Bird bird = new Bird();
        bird.setName(name);
        bird.setColor("Black");
        bird.setHeight(4321);
        bird.setWeight(8765);
        when(birdRepository.save(bird)).thenReturn(new Bird());
        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/bird/")
                        .content(asJsonString(bird))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }

    @Test
    void deleteBird() throws Exception {
        long birdId = 1;
        this.mockMvc.perform( MockMvcRequestBuilders
                        .delete("/bird/" + birdId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
