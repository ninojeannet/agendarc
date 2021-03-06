package com.hearc.agendarc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	  
   }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
	    .authorizeRequests() 
		.antMatchers("/admin").authenticated()
		.antMatchers("/createEvent").authenticated()
		.antMatchers("/profile").authenticated()
		.antMatchers("/newCalendar").authenticated()
		.antMatchers("/updateCalendar").authenticated()
		.antMatchers("/updateEvent").authenticated()
		.antMatchers("/users").authenticated()
        .antMatchers("/h2").permitAll()
        .antMatchers("/add").permitAll()
		.and()
		.formLogin();
	  
	  http.csrf().disable();
      http.headers().frameOptions().disable();
	}
}
