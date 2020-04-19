package com.hearc.agendarc;

import java.time.LocalDateTime;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.Event;
import com.hearc.agendarc.model.Role;
import com.hearc.agendarc.model.User;
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

    
        final Role r4 = new Role();
        r4.setName("ROLE_ADMIN_1");
        roleRepository.save(r4);

        final Role r5 = new Role();
        r5.setName("ROLE_ADMIN_2");
        roleRepository.save(r5);


        final Role r2 = new Role();
        r2.setName("ROLE_VISITOR");
        roleRepository.save(r2);

        final User u = new User();
        u.setName("dave");
        u.setSurname("Silva");
        u.setUsername("dave");
        u.setPwd((bCryptPasswordEncoder.encode("test")));
        u.addRole(r4);
        u.addRole(r5);
        userRepository.save(u);

        final Calendar c = new Calendar();
        c.setName("Euro 2020");
        c.setOwner(u);
        c.setRoleName("ROLE_ADMIN_1");
        calendarRepository.save(c);

        final Calendar c1 = new Calendar();
        c1.setName("JO Tokyo");
        c1.setOwner(u);
        c1.setRoleName("ROLE_ADMIN_2");
        calendarRepository.save(c1);

    
        LocalDateTime start = LocalDateTime.of(2020, 4, 9, 10, 0, 0);
        LocalDateTime stop = LocalDateTime.of(2020, 4, 9, 11, 0, 0);

        final Event e1 = new Event();
        e1.setTitle("Suisse-Italie");
        //e1.setDate(localDate);
        e1.setStart(start);
        e1.setEnd(stop);
        e1.setCalendar(c);
        e1.setDescription("Match des 8emes de finale");
        eventRepository.save(e1);
        
        start = LocalDateTime.of(2020, 4, 10, 15, 0, 0);
        stop = LocalDateTime.of(2020, 4, 11, 15, 0, 0);
        final Event e2 = new Event();
        e2.setTitle("France-Allemagne");
        e2.setStart(start);
        e2.setEnd(stop);
        e2.setCalendar(c);
        e2.setDescription("Match de poule");
        eventRepository.save(e2);
/*
        final Event e3 = new Event();
        e3.setName("Serbie-Albanie");
        //e3.setDate(localDate);
        e3.setCalendar(c);
        e3.setDetails("Match de catch");
        eventRepository.save(e3);

        final Event e4 = new Event();
        e4.setName("Federer-Nadal");
        //e4.setDate(localDate);
        e4.setCalendar(c1);
        e4.setDetails("Match pour la médaille d\'or ");
        eventRepository.save(e4);

        final Event e5 = new Event();
        e5.setName("Djokovic-Stanimal");
        //e5.setDate(localDate);
        e5.setCalendar(c1);
        e5.setDetails("Match pour la médaille d\'argent");
        eventRepository.save(e5);
*/
        
        
	}
}
