package com.hearc.agendarc.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import com.hearc.agendarc.model.Role;
import com.hearc.agendarc.model.User;



public class RoleTest {

    @Test
    public void givenRole_whenRoleHasGoodUsers_thenReturnTrue() 
    {
        User user = new User();
        user.setName("dave");
        
        Set<User> setUsers = new HashSet<User>();
        setUsers.add(user);
        Role role = new Role();
        role.setName("Test role");
        role.setUsers(setUsers);
        
        assertTrue(role.getUsers()==setUsers);

    }
    @Test
    public void givenRole_whenRoleHasNotGoodUsers_thenReturnFalse() 
    {
        User user = new User();
        user.setName("dave");
        User anyone = new User();
        anyone.setName("anyone");
        
        Set<User> setUsers = new HashSet<User>();
        setUsers.add(user);
        Set<User> setUsersTest = new HashSet<User>();
        setUsers.add(anyone);
        Role role = new Role();
        role.setName("Test role");
        role.setUsers(setUsers);
        
        assertFalse(role.getUsers()==setUsersTest);

    }
}