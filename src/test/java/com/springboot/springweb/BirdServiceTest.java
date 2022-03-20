package com.springboot.springweb;

import com.springboot.springweb.entity.Bird;
import com.springboot.springweb.repository.BirdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BirdServiceTest
{


    private static final Logger LOGGER = LoggerFactory.getLogger(BirdServiceTest.class);

    @Mock
    BirdRepository birdRepository;

//    EmployeeService employeeService;
//
//    @BeforeEach
//    void initTestCase()
//    {
//        employeeService = new EmployeeServiceImpl(birdRepository, protocolRepository, employeeTypeRepository);
//    }

    @Test
    void getAllBirds()
    {
        when(birdRepository.findAll()).thenReturn(Collections.singletonList(new Bird()));
//        System.out.println(employeeService.findBirdById(1L).getName());
//        assertNotNull(employeeService.findBirdById(1L));
    }


    @Test
    public void testCreateBird() {
        Bird bird = new Bird();
        bird.setName("China Black Duck");
        bird.setColor("Black");
        bird.setWeight(96.90);
        bird.setHeight(34.90);

        Bird bird2 = new Bird();
        bird2.setName("China Black Duck");
        bird2.setColor("Black");
        bird2.setWeight(96.90);
        bird2.setHeight(34.90);

        birdRepository.save(bird);

        birdRepository.save(bird2);

        List<Bird> birds = birdRepository.findAll();
        LOGGER.info("All birds are: " + birds.size());

        LOGGER.info("All birds Id are: " + birds.get(0).getId());
        LOGGER.info("All birds Color are: " + birds.get(0).getColor());
        LOGGER.info("All birds Height are: " + birds.get(0).getHeight());
        LOGGER.info("All birds Name are: " + birds.get(0).getName());
        LOGGER.info("All birds Weight are: " + birds.get(0).getWeight());

        assertNotNull(birds);
        assertNotNull(birds.get(0).getId());
        assertEquals("China Black Duck",birds.get(0).getName());
    }
}