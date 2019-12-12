package com.chughes.events.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chughes.events.model.Event;
import com.chughes.events.model.Message;
import com.chughes.events.model.User;
import com.chughes.events.service.EventService;
import com.chughes.events.service.UserService;

@Controller
@RequestMapping("/events")
public class EventController {

	private EventService eventService;
	private UserService userService;

	public EventController(EventService eventService, UserService userService) {
		this.eventService = eventService;
		this.userService = userService;
	}

	@RequestMapping("")
	public String eventsPage(Model model, HttpSession session, @ModelAttribute("event") Event event) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			 return "redirect:/login";
		}
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("eventsInState", eventService.getEventsInState(user.getLocationState()));
		model.addAttribute("otherEvents", eventService.getEventsOutOfState(user.getLocationState()));
		model.addAttribute("now",new Date());
		return "eventsPage.jsp";
	}
	
	@PostMapping("")
	public String createEvent(@ModelAttribute("event") Event event, HttpSession session){
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			 return "redirect:/login";
		}
		eventService.createEvent(event);
		return "redirect:/events";
	}
	
	@RequestMapping("/{event_id}/a/join")
	public String joinEvent(HttpSession session, @PathVariable("event_id") Long eventId) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			 return "redirect:/login";
		}
		User user = userService.findUserById(userId);
		user.getAttending().add(eventService.getEventById(eventId));
		
		userService.saveUser(user);
		
		return "redirect:/events";
		
	}
	
	@RequestMapping("/{event_id}/a/cancel")
	public String cancelJoinEvent(HttpSession session, @PathVariable("event_id") Long eventId) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			 return "redirect:/login";
		}
		User user = userService.findUserById(userId);
		user.getAttending().remove(eventService.getEventById(eventId));
		
		userService.saveUser(user);
		
		return "redirect:/events";
		
	}
	
	@RequestMapping("/{event_id}")
	public String eventPage(@PathVariable("event_id") Long eventId, Model model, @ModelAttribute("message") Message message, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			 return "redirect:/login";
		}
		
		model.addAttribute("event", eventService.getEventById(eventId));
		
		return "eventPage.jsp";
	}

}
