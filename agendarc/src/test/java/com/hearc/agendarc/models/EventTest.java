package com.hearc.agendarc.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.hearc.agendarc.model.Event;
import com.hearc.agendarc.model.User;



public class EventTest {


    @Test
    public void givenEvent_whenCallisCreator_thenReturnTrue() 
    {
        User user = new User();
        user.setName("dave");
        User anyone = new User();
        anyone.setName("anyone");
        Event event = new Event(); 
        event.setCreator(user);

        assertTrue(event.isCreator(user)); 
    }

    
    @Test
    public void givenEvent_whenCallIsNotCreator_thenReturnFalse() 
    {
        User user = new User();
        user.setName("dave");
        User anyone = new User();
        anyone.setName("anyone");
       Event event = new Event(); 
       event.setCreator(user);

        assertFalse(event.isCreator(anyone)); 
    }
}