package com.springboot.springweb;

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
    void getBirdById() throws Exception {
        long birdId = 11112;
        when(birdRepository.findById(birdId)).thenReturn(Optional.of(new Bird()));
        this.mockMvc.perform(get("/bird/" + birdId)).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
