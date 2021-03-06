package com.hearc.agendarc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hearc.agendarc.model.Role;
import com.hearc.agendarc.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  
  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException 
  {
    
	  com.hearc.agendarc.model.User utilisateur = userRepository.findByUsername(username);
	
    if (utilisateur == null)
      throw new UsernameNotFoundException(username);
   
    
    //ajouter verification username


    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (final Role role : utilisateur.getRoles()) {
		grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	}
	return new User(utilisateur.getUsername(), utilisateur.getPwd(), grantedAuthorities);
  }
  
  public List<com.hearc.agendarc.model.User> findByUsernameLike(String username)
  {
  	return userRepository.findByUsernameLike("%"+username+"%");
  }

  public void updateRoles(com.hearc.agendarc.model.User user)
  {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();

      List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
      for (final Role role : user.getRoles()) {
        updatedAuthorities.add(new SimpleGrantedAuthority(role.getName()));}

      Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
     // SecurityContextHolder.clearContext();
      //SecurityContextHolder.createEmptyContext();
      SecurityContextHolder.getContext().setAuthentication(newAuth);
  }

  

}