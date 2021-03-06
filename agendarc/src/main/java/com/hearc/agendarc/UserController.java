package com.hearc.agendarc;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.UserRepository;



@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailServiceImpl userService;
	
	@Autowired
	CalendarRepository calendarRepository;

	
	
    @GetMapping("/profile")
    public String profile(Model model,Principal principal)
    {
		User user = userRepository.findByUsername(principal.getName());

		model.addAttribute("userConnected", user);
		
		List<Calendar> calendars = calendarRepository.findByOwner(user);
		model.addAttribute("number", calendars.size());
		model.addAttribute("calendars", calendars);
		
    	return "profile";
    }
    
	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(defaultValue="")  String username) {
		model.addAttribute("users", userService.findByUsernameLike(username));
		return "users";
	}

}
