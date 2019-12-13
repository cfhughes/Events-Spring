package com.chughes.events.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.chughes.events.model.Event;

@DataJpaTest
public class EventRepositoryTests {
	
	@Autowired
	EventRepository eventRepo;
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void testFindByLocationState() {
		Event event1 = new Event();
		event1.setLocationState("NM");
		entityManager.persist(event1);
		
		Event event2 = new Event();
		event2.setLocationState("CA");
		entityManager.persist(event2);
		
		entityManager.flush();
		
		List<Event> found = eventRepo.findByLocationState("CA");
		
		assertThat(found.size()).isEqualTo(1);
		
		assertThat(found.get(0).getLocationState()).isEqualTo("CA");
		
	}

}
