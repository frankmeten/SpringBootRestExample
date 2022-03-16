package com.springboot.springweb;

import com.springboot.springweb.entity.Bird;
import com.springboot.springweb.entity.Sighting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BirdSightrestapiApplicationTests {

	@Value("${birdsightapi.bird.services.url}")
	private String birdBaseURL;

	@Value("${birdsightapi.sighting.services.url}")
	private String sightingBaseURL;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Test
	public void testGetBird() {
		System.out.println(birdBaseURL);
		RestTemplate restTemp = new RestTemplate();
		Bird bird = restTemp.getForObject(birdBaseURL+"1", Bird.class);
//		assertNull(bird);
	}

	@Test
	public void testCreateBird() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = new Bird();
		bird.setName("African Black Duck");
		bird.setColor("Black");
		bird.setWeight(96.90);
		bird.setHeight(34.90);
		Bird bird1 = restTemp.postForObject(birdBaseURL, bird, Bird.class);
		assertNotNull(bird1);
		assertNotNull(bird1.getId());
		assertEquals("African Black Duck",bird1.getName());
	}

	@Test
	public void testCreateBird2() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = new Bird();
		bird.setName("American Black Duck");
		bird.setColor("Black");
		bird.setWeight(96.90);
		bird.setHeight(34.90);
		Bird bird1 = restTemp.postForObject(birdBaseURL, bird, Bird.class);
		assertNotNull(bird1);
		assertNotNull(bird1.getId());
		assertEquals("American Black Duck",bird1.getName());
	}

	@Test
	public void testCreateBird3() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = new Bird();
		bird.setName("Australian Wood Duck");
		bird.setColor("Wood");
		bird.setWeight(96.90);
		bird.setHeight(34.90);
		Bird bird1 = restTemp.postForObject(birdBaseURL, bird, Bird.class);
		assertNotNull(bird1);
		assertNotNull(bird1.getId());
		assertEquals("Australian Wood Duck",bird1.getName());
	}

	@Test
	public void testCreateBird4() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = new Bird();
		bird.setName("Lake Duck");
		bird.setColor("Red");
		bird.setWeight(96.90);
		bird.setHeight(34.90);
		Bird bird1 = restTemp.postForObject(birdBaseURL, bird, Bird.class);
		assertNotNull(bird1);
		assertNotNull(bird1.getId());
		assertEquals("Lake Duck",bird1.getName());
	}


	@Test
	public void testCreateBird5() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = new Bird();
		bird.setName("Pacific Black Duck");
		bird.setColor("Black");
		bird.setWeight(96.90);
		bird.setHeight(34.90);
		Bird bird1 = restTemp.postForObject(birdBaseURL, bird, Bird.class);
		assertNotNull(bird1);
		assertNotNull(bird1.getId());
		assertEquals("Pacific Black Duck",bird1.getName());
	}


	@Test
	public void testCreateBird6() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = new Bird();
		bird.setName("Red-Billed Duck");
		bird.setColor("Red");
		bird.setWeight(96.90);
		bird.setHeight(34.90);
		Bird bird1 = restTemp.postForObject(birdBaseURL, bird, Bird.class);
		assertNotNull(bird1);
		assertNotNull(bird1.getId());
		assertEquals("Red-Billed Duck",bird1.getName());
	}

	@Test
	public void testUpdateBird() {
		RestTemplate restTemp = new RestTemplate();
		Bird bird = restTemp.getForObject(birdBaseURL+"2", Bird.class);
		bird.setWeight(9436.9);
		restTemp.put(birdBaseURL, bird);
	}


	/////////////////////////


	@Test
	public void testGetSighting() {
		System.out.println(sightingBaseURL);
		RestTemplate restTemp = new RestTemplate();
		Sighting sighting = restTemp.getForObject(sightingBaseURL+"2", Sighting.class);
//		assertNull(sighting);
	}

	@Test
	public void testCreateSighting() {
		RestTemplate restTemp = new RestTemplate();
		Sighting sighting = new Sighting();
		sighting.setBird_id(1);

		Date date = new Date();

		try {
			date = sdf.parse("2021-03-14 22:01:21");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		sighting.setSightingTime(date);
		sighting.setLocation("Beijing China");
		Sighting sighting1 = restTemp.postForObject(sightingBaseURL, sighting, Sighting.class);
		assertNotNull(sighting1);
		assertNotNull(sighting1.getId());
		assertEquals("Beijing China",sighting1.getLocation());
	}
	@Test
	public void testCreateSighting2() {
		RestTemplate restTemp = new RestTemplate();
		Sighting sighting = new Sighting();
		sighting.setBird_id(1);

		Date date = new Date();

		try {
			date = sdf.parse("2019-03-14 22:01:21");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		sighting.setSightingTime(date);
		sighting.setLocation("Shanghai China");
		Sighting sighting1 = restTemp.postForObject(sightingBaseURL, sighting, Sighting.class);
		assertNotNull(sighting1);
		assertNotNull(sighting1.getId());
		assertEquals("Shanghai China",sighting1.getLocation());
	}
	@Test
	public void testCreateSighting3() {
		RestTemplate restTemp = new RestTemplate();
		Sighting sighting = new Sighting();
		sighting.setBird_id(1);

		Date date = new Date();

		try {
			date = sdf.parse("2021-01-14 22:01:21");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		sighting.setSightingTime(date);
		sighting.setLocation("Tianjin China");
		Sighting sighting1 = restTemp.postForObject(sightingBaseURL, sighting, Sighting.class);
		assertNotNull(sighting1);
		assertNotNull(sighting1.getId());
		assertEquals("Tianjin China",sighting1.getLocation());
	}
	@Test
	public void testCreateSighting4() {
		RestTemplate restTemp = new RestTemplate();
		Sighting sighting = new Sighting();
		sighting.setBird_id(1);

		Date date = new Date();

		try {
			date = sdf.parse("2020-03-14 22:01:21");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		sighting.setSightingTime(date);
		sighting.setLocation("Shandong China");
		Sighting sighting1 = restTemp.postForObject(sightingBaseURL, sighting, Sighting.class);
		assertNotNull(sighting1);
		assertNotNull(sighting1.getId());
		assertEquals("Shandong China",sighting1.getLocation());
	}


	@Test
	public void testGetSighting2() {
		System.out.println(sightingBaseURL);
		RestTemplate restTemp = new RestTemplate();
		Sighting sighting = restTemp.getForObject(sightingBaseURL+"3", Sighting.class);
		assertNotNull(sighting);
//		assertEquals("Tianjin China", sighting.getLocation());
	}
}
