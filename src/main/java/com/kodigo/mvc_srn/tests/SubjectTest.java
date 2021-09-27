package com.kodigo.mvc_srn.tests;

import com.kodigo.mvc_srn.MvcProjectApplication;
import com.kodigo.mvc_srn.models.Subject;
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

public class SubjectTest implements AbstractTest {

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
        Subject subject = restTemplate.getForObject(getRootUrl() + "/users/1", Subject.class);
        System.out.println(subject.getNameSubject());
        Assert.assertNotNull(subject);
    }

    @Override
    public void testCreateUser() {
        Subject subject = new Subject();
        subject.setNameSubject("Matematica 4");

        ResponseEntity<Subject> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", subject, Subject.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Override
    public void testUpdatePost() {
        int id = 1;
        Subject subject = restTemplate.getForObject(getRootUrl() + "/users/" + id, Subject.class);
        subject.setNameSubject("Matematica(update)");

        restTemplate.put(getRootUrl() + "/users/" + id, subject);

        Subject updateSubject = restTemplate.getForObject(getRootUrl() + "/users/" + id, Subject.class);
        Assert.assertNotNull(updateSubject);
    }

    @Override
    public void testDeletePost() {
        int id = 2;
        Subject subject = restTemplate.getForObject(getRootUrl() + "/users/" + id, Subject.class);
        Assert.assertNotNull(subject);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            subject = restTemplate.getForObject(getRootUrl() + "/users/" + id, Subject.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
