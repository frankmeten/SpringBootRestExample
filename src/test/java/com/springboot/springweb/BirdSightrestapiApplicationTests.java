package com.springboot.springweb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.springboot.springweb.entity.Bird;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class BirdSightrestapiApplicationTests {

	@Value("${studentrestapi.services.url}")
	private String baseURL;
	@Test
	public void testGetProduct() {
		System.out.println(baseURL);
		RestTemplate restTemp = new RestTemplate();
		Bird bird = restTemp.getForObject(baseURL+"7", Bird.class);
		assertNotNull(bird);
		assertEquals("devi", bird.getName());
	}
	
	@Test
	public void testCreateProduct() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = new Bird();
		bird.setName("dhanalakshmi");
		bird.setWeight(96.90);
		bird.setHeight(34.90);
		Bird newstudent = restTemp.postForObject(baseURL, bird, Bird.class);
		assertNotNull(newstudent);
		assertNotNull(newstudent.getId());
		assertEquals("dhanalakshmi",newstudent.getName());
	}
	
	@Test
	public void testUpdateProduct() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = restTemp.getForObject(baseURL+"7", Bird.class);
		bird.setWeight(96.9);
		restTemp.put(baseURL, bird);
	}
	

}
