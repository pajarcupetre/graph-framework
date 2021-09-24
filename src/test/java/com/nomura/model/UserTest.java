package com.nomura.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void testUserInit()
    {
        String name = "GabrielM";
        String id = "1";
        User testUser = new User(id, name);
        assertTrue(testUser.getId().equals(id));
        assertTrue(testUser.getName().equals(name));
    }
}
