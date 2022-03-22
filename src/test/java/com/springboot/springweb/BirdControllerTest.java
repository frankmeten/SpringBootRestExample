package com.springboot.springweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springweb.entity.Bird;
import com.springboot.springweb.repository.BirdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    void getBird() throws Exception {
        String name = "American Black Duck";
        Bird bird = new Bird();
        bird.setName(name);
        bird.setColor("frank 76");
        bird.setHeight(1234.0);
        bird.setWeight(5678.0);
        when(birdRepository.save(bird)).thenReturn(new Bird());
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/getBird/")
                        .content(asJsonString(bird))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createBird() throws Exception {
        String name = "American Black Duck";
        Bird bird = new Bird();
        bird.setName(name);
        bird.setColor("frank 76");
        bird.setHeight(1234.0);
        bird.setWeight(5678.0);
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
        bird.setHeight(4321.0);
        bird.setWeight(8765.0);
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
