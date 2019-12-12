package com.chughes.events.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chughes.events.model.Event;
import com.chughes.events.repository.EventRepository;

@Service
public class EventService {
	
	private EventRepository eventRepo;
	
	public EventService(EventRepository eventRepo) {
		super();
		this.eventRepo = eventRepo;
	}

	public List<Event> getEventsInState(String locationState) {
		return eventRepo.findByLocationState(locationState);
	}

	public List<Event> getEventsOutOfState(String locationState) {
		return eventRepo.findByLocationStateNot(locationState);
	}

	public Event getEventById(Long eventId) {
		return eventRepo.findById(eventId).orElse(null);
	}

	public void createEvent(Event event) {
		eventRepo.save(event);
		
	}

}
