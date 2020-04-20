package com.hearc.agendarc;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.Event;
import com.hearc.agendarc.model.Role;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.EventRepository;
import com.hearc.agendarc.repository.RoleRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CalendarController{
    
	@Autowired
	CalendarRepository calendarRepository;

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailServiceImpl userService;
	
	@Autowired
	CalendarService calendarService;

	@Autowired
	RoleRepository roleRepository;

    @GetMapping(value="/")
    public String test() {
        return "home";
    }
    
    @GetMapping(value = "/calendars")
	public String liste(Model model, @RequestParam(defaultValue="")  String name,@RequestParam(defaultValue="0") int page) {
		Pageable pageable = PageRequest.of(page,2);
		model.addAttribute("calendars", calendarService.findByNameLikeIgnoreCase(name,pageable));
		if(calendarService.findByNameLikeIgnoreCase(name,pageable).getTotalPages()<1)
		{
			int totalPages = 0;
			model.addAttribute("totalPages", totalPages);
		}
		else
		{
			int totalPages = calendarService.findByNameLikeIgnoreCase(name,pageable).getTotalPages()-1;
			model.addAttribute("totalPages", totalPages);
		}
				
		model.addAttribute("currentPage", page);
		return "calendars";
	}

	@GetMapping(value = "/calendar")
	public String calendar(@RequestParam("id") Long id, Model model) {
		Optional<Calendar> optionalCal = calendarRepository.findById(id);
		if (!optionalCal.isPresent())
			throw new NoSuchElementException();
		Calendar calendar = optionalCal.get();
		
		model.addAttribute("calendar", calendar);
		List<Event> events = eventRepository.findByCalendar(calendar);
		model.addAttribute("events", events);
		return "calendar";
	}

	@GetMapping("/newCalendar")
    public String register(Model model) 
	{
		model.addAttribute("calendar", new Calendar());

    	return "newCalendar";
	}

	@PostMapping("/newCalendar")
	public String newCalendar(Model model,@ModelAttribute ("calendar") Calendar calendar, Principal principal) {

		User user = userRepository.findByUsername(principal.getName());

		calendar.setOwner(user);
		calendarRepository.save(calendar);

		final Role r = new Role();
        r.setName("ROLE_ADMIN_"+calendar.getId());
		roleRepository.save(r);

		user.addRole(r);
		userRepository.save(user);

		calendar.setRoleName(r.getName());
		calendarRepository.save(calendar);


		userService.updateRoles(user);

		
		return "redirect:/calendars";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_'.concat(#id))")
	@GetMapping(value="/updateCalendar")
	public String update(Model model,@RequestParam("id") Long id)
	{
		Optional<Calendar> optionalCal = calendarRepository.findById(id);
		if (!optionalCal.isPresent())
			throw new NoSuchElementException();
		Calendar calendar = optionalCal.get();
		model.addAttribute("calendar", calendar);
		return "updateCalendar";
	}

	@PostMapping(path="/updateCalendar")
	public String updateCalendar(@ModelAttribute ("calendar") Calendar calendar) {

	calendarRepository.save(calendar);
	return "redirect:/calendar?id="+calendar.getId();
	}

	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_'.concat(#id))")
	@GetMapping(value="/deleteCal")
	public String deleteEvent(@RequestParam("id") Long id)
	{
		Optional<Calendar> optionalCal = calendarRepository.findById(id);
		if (!optionalCal.isPresent())
			throw new NoSuchElementException();
		Calendar calendar = optionalCal.get();

		calendarRepository.delete(calendar);
		return "redirect:/calendars";
	}
}