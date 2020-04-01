package com.hearc.agendarc;

import java.security.Principal;
import java.util.List;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.Event;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.EventRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class EventController{
    
	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CalendarRepository calendarRepository;

    
    @RequestMapping(value = "/events", method=RequestMethod.GET)
	public String liste(Model model) {
		model.addAttribute("events", eventRepository.findAll());
		return "events";
	}
 
	@RequestMapping("/createEvent")
    public String register(Model model, Principal principal) 
	{
		model.addAttribute("event", new Event());
		User user = userRepository.findByUsername(principal.getName());
		
		List<Calendar> calendars = calendarRepository.findByOwner(user);
		model.addAttribute("calendars", calendars);

		model.addAttribute("selectedcalendar", new Calendar());
		
		return "createEvent";
	}

	@PostMapping("/createEvent")
	public String newCalendar(@ModelAttribute ("event") Event event,@ModelAttribute ("selectedcalendar") Calendar selectedcalendar, Principal principal) {
		User user = userRepository.findByUsername(principal.getName());
		event.setCreator(user);
		Calendar calendar = calendarRepository.findById(selectedcalendar.getId()).get();
		event.setCalendar(calendar);
		eventRepository.save(event);

		return "redirect:/";
	}
}