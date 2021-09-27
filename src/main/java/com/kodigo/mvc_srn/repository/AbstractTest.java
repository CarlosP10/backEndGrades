package com.kodigo.mvc_srn.repository;

import org.junit.Test;

public interface AbstractTest {
    @Test
    public void contextLoad();

    @Test
    public void testGetAllUsers();

    @Test
    public void testGetUserById();

    @Test
    public void testCreateUser();

    @Test
    public void testUpdatePost();

    @Test
    public void testDeletePost();
}
