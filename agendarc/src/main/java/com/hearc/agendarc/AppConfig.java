package com.hearc.agendarc;


import javax.annotation.PostConstruct;

import com.hearc.agendarc.repository.CalendarRepository;
import com.hearc.agendarc.repository.EventRepository;
import com.hearc.agendarc.repository.RoleRepository;
import com.hearc.agendarc.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {
	
	@Autowired
    UserRepository userRepository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Autowired
    CalendarRepository calendarRepository;
    
        
	@Autowired
    EventRepository eventRepository;
    
    @Autowired
	RoleRepository roleRepository;
	
	@PostConstruct
	public void init() {

	}
}
