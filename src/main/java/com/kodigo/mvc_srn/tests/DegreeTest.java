package com.kodigo.mvc_srn.tests;

import com.kodigo.mvc_srn.MvcProjectApplication;
import com.kodigo.mvc_srn.models.Degree;
import com.kodigo.mvc_srn.repository.AbstractTest;
import org.apache.tomcat.jni.User;
import org.junit.Assert;
import org.junit.Test;
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

public class DegreeTest implements AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(){
        return "http://localhost:" + port;
    }

    @Override
    public void contextLoad(){}

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
        Degree degree = restTemplate.getForObject(getRootUrl() + "/users/1", Degree.class);
        System.out.println(degree.getNameDegree());
        Assert.assertNotNull(degree);
    }

    @Override
    public void testCreateUser() {
        Degree degree = new Degree();
        degree.setNameDegree("Primer grado");

        ResponseEntity<Degree> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", degree, Degree.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Override
    public void testUpdatePost() {
        int id = 1;
        Degree degree = restTemplate.getForObject(getRootUrl() + "/users/" + id, Degree.class);
        degree.setNameDegree("segundo grado(update)");

        restTemplate.put(getRootUrl() + "/users/" + id, degree);

        Degree updatedDegree = restTemplate.getForObject(getRootUrl() + "/users/" + id, Degree.class);
        Assert.assertNotNull(updatedDegree);
    }

    @Override
    public void testDeletePost() {
        int id = 2;
        Degree degree = restTemplate.getForObject(getRootUrl() + "/users/" + id, Degree.class);
        Assert.assertNotNull(degree);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            degree = restTemplate.getForObject(getRootUrl() + "/users/" + id, Degree.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
