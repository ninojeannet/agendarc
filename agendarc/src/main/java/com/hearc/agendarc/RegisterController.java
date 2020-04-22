package com.hearc.agendarc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hearc.agendarc.model.User;
import com.hearc.agendarc.repository.RoleRepository;
import com.hearc.agendarc.repository.UserRepository;



@Controller
public class RegisterController {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @GetMapping("/register")
    public String register(Model model) 
	{
		model.addAttribute("user", new User());

    	return "register";
	}
    

    
	@PostMapping("/add")
	public String add(@ModelAttribute ("user") User user) {
		user.setPwd((bCryptPasswordEncoder.encode(user.getPwd())));
		userRepo.save(user);
			
		return "redirect:/login";

	}
	
}
