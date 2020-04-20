package com.hearc.agendarc.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.hearc.agendarc.model.Calendar;
import com.hearc.agendarc.model.User;



public class CalendarTest {


    @Test
    public void givenCalendar_whenCallisOwner_thenReturnTrue() 
    {
        User user = new User();
        user.setName("dave");
        User anyone = new User();
        anyone.setName("anyone");
        Calendar calendar = new Calendar();
        calendar.setOwner(user);

        assertTrue(calendar.isOwner(user));
       
         
    }
    @Test
    public void givenCalendar_whenCallisNotOwner_thenReturnFalse() 
    {
        User user = new User();
        user.setName("dave");
        User anyone = new User();
        anyone.setName("anyone");
        Calendar calendar = new Calendar();
        calendar.setOwner(user);

        assertFalse(calendar.isOwner(anyone));
       
         
    }
}