package com.chughes.events.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.chughes.events.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	
	public List<Event> findByLocationState(String state);
	public List<Event> findByLocationStateNot(String state);

}
