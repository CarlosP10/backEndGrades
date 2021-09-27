package com.kodigo.mvc_srn.tests;

import com.kodigo.mvc_srn.MvcProjectApplication;
import com.kodigo.mvc_srn.models.Institute;
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

public class InstituteTest implements AbstractTest {

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
        Institute institute = restTemplate.getForObject(getRootUrl() + "/users/1", Institute.class);
        System.out.println(institute.getInstitutionName());
        Assert.assertNotNull(institute);
    }

    @Override
    public void testCreateUser() {
        Institute institute = new Institute();
        institute.setInstitutionName("Colegio Lourdes");

        ResponseEntity<Institute> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", institute, Institute.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Override
    public void testUpdatePost() {
        int id = 1;
        Institute institute = restTemplate.getForObject(getRootUrl() + "/users/" + id, Institute.class);
        institute.setInstitutionName("Colegio Lourdes(update)");

        restTemplate.put(getRootUrl() + "/users/" + id, institute);

        Institute updatedInstitute = restTemplate.getForObject(getRootUrl() + "/users/" + id, Institute.class);
        Assert.assertNotNull(updatedInstitute);

    }

    @Override
    public void testDeletePost() {
        int id = 2;
        Institute institute = restTemplate.getForObject(getRootUrl() + "/users/" + id, Institute.class);
        Assert.assertNotNull(institute);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            institute = restTemplate.getForObject(getRootUrl() + "/users/" + id, Institute.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
