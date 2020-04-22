package com.hearc.agendarc;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.Event;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.EventRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EventController{
    
	@Autowired
	EventRepository eventRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CalendarRepository calendarRepository;

    
    @GetMapping(value = "/events")
	public String liste(Model model) {
		model.addAttribute("events", eventRepository.findAll());
		return "events";
	}
 
	@GetMapping("/createEvent")
    public String create(Model model, Principal principal) 
	{
		model.addAttribute("event", new Event());
		User user = userRepository.findByUsername(principal.getName());
		
		List<Calendar> calendars = calendarRepository.findByOwner(user);
		model.addAttribute("calendars", calendars);

		model.addAttribute("selectedcalendar", new Calendar());
		
		return "createEvent";
	}

	@PostMapping("/createEvent")
	public String save(@ModelAttribute ("event") Event event,@ModelAttribute ("selectedcalendar") Calendar selectedcalendar, Principal principal) {
		User user = userRepository.findByUsername(principal.getName());
		event.setCreator(user);

		Optional<Calendar> optionalCal = calendarRepository.findById(selectedcalendar.getId());
		if (!optionalCal.isPresent())
			throw new NoSuchElementException();
		Calendar calendar = optionalCal.get();
		event.setCalendar(calendar);
		event.setId(null);
		
		eventRepository.save(event);

		return "redirect:/calendar?id="+calendar.getId();
	}

	
	@GetMapping(value = "/event")
	public String event(@RequestParam("id") Long id, Model model) {
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if (!optionalEvent.isPresent())
			throw new NoSuchElementException();
		Event event = optionalEvent.get();
		Calendar parentCalendar = event.getCalendar();
		model.addAttribute("event", event);
		model.addAttribute("calendar", parentCalendar);
		return "event";
	}

	@GetMapping(value="/deleteEve")
	public String deleteEvent(@RequestParam("id") Long id)
	{
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if (!optionalEvent.isPresent())
			throw new NoSuchElementException();
		Event event = optionalEvent.get();
		Long idCal=event.getCalendar().getId();

		eventRepository.delete(event);
		return "redirect:/calendar?id="+idCal;
	}

	@GetMapping(value="/updateEvent")
	public String update(Model model,@RequestParam("id") Long id)
	{
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if (!optionalEvent.isPresent())
			throw new NoSuchElementException();
		Event event = optionalEvent.get();
		model.addAttribute("event", event);
		return "updateEvent";
	}

	@PostMapping(path="/updateEvent")
	public String updateCalendar(@ModelAttribute ("event") Event event) {

		eventRepository.save(event);
		Long idCal=event.getCalendar().getId();

		return "redirect:/calendar?id="+idCal;
	}
}