package com.springboot.springweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springweb.entity.Bird;
import com.springboot.springweb.entity.Sighting;
import com.springboot.springweb.repository.SightingRepository;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = {BirdSightrestapiApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles(value = "local")
class SightingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SightingRepository sightingRepository;

    @Test
    void getSighting() throws Exception {
        String name = "American Black Duck";
        Bird bird = new Bird();
        bird.setName(name);
        Sighting sighting = new Sighting();
        sighting.setLocation("fake location");
        sighting.setBird(bird);
        when(sightingRepository.save(sighting)).thenReturn(new Sighting());
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/getSighting/")
                        .content(asJsonString(sighting))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getSightingBetween() throws Exception {
        String startTime = "2010-03-19 14:14:53";
        String endTime = "2020-03-19 14:14:53";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        when(sightingRepository.findAllBySightingTimeBetween(start, end)).thenReturn(new ArrayList<Sighting>());
        this.mockMvc.perform(get("/sighting/start/" + startTime + "/end/" + endTime)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createSighting() throws Exception {

        String name = "American Black Duck";

        Bird bird = new Bird();
        bird.setName(name);

        String startTime = "2010-03-19 14:14:53";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(startTime);

        Sighting sighting = new Sighting();
        sighting.setSightingTime(start);
        sighting.setBird(bird);
        sighting.setLocation("fake location");
        when(sightingRepository.save(sighting)).thenReturn(new Sighting());
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/sighting/")
                        .content(asJsonString(sighting))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateSighting() throws Exception {
        String name = "American Black Duck";

        Bird bird = new Bird();
        bird.setName(name);

        String startTime = "2010-03-19 14:14:53";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(startTime);

        Sighting sighting = new Sighting();
        sighting.setSightingTime(start);
        sighting.setBird(bird);
        sighting.setLocation("fake location");
        when(sightingRepository.save(sighting)).thenReturn(new Sighting());
        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/sighting/")
                        .content(asJsonString(sighting))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteSighting() throws Exception {
        long sightingId = 1;
        this.mockMvc.perform( MockMvcRequestBuilders
                        .delete("/sighting/" + sightingId)
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
