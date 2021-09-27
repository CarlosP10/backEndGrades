package com.kodigo.mvc_srn.tests;

import com.kodigo.mvc_srn.MvcProjectApplication;
import com.kodigo.mvc_srn.models.Note;
import com.kodigo.mvc_srn.repository.AbstractTest;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MvcProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class NoteTest implements AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }

    @Override
    public void contextLoad() {}

    @Override
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
                HttpMethod.GET, entity, String.class);

        Assert.assertNotNull(response.getBody());
    }

    @Override
    public void testGetUserById() {
        Note note = restTemplate.getForObject(getRootUrl() + "/users/1", Note.class);
        System.out.println(note.getNoteName());
        Assert.assertNotNull(note);
    }

    @Override
    public void testCreateUser() {
        Note note = new Note();
        note.setNoteName("Nota creada");

        ResponseEntity<Note> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", note, Note.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Override
    public void testUpdatePost() {
        int id = 1;
        Note note = restTemplate.getForObject(getRootUrl() + "/users/" + id, Note.class);
        note.setNoteName("Otra nota(update)");

        restTemplate.put(getRootUrl() + "/users/" + id, note);

        Note updateNote = restTemplate.getForObject(getRootUrl() + "/users/" + id, Note.class);
        Assert.assertNotNull(updateNote);
    }

    @Override
    public void testDeletePost(){
        int id = 2;
        Note note = restTemplate.getForObject(getRootUrl() + "/users/" + id, Note.class);
        Assert.assertNotNull(note);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            note = restTemplate.getForObject(getRootUrl() + "/users/" + id, Note.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
