package com.kodigo.mvc_srn.tests;

import com.kodigo.mvc_srn.MvcProjectApplication;
import com.kodigo.mvc_srn.models.Teacher;
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

public class TeacherTest implements AbstractTest {

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
        Teacher teacher = restTemplate.getForObject(getRootUrl() + "/users/1", Teacher.class);
        System.out.println(teacher.getNameTeacher());
        Assert.assertNotNull(teacher);
    }

    @Override
    public void testCreateUser() {
        Teacher teacher = new Teacher();
        teacher.setNameTeacher("Rodrigo profe");

        ResponseEntity<Teacher> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", teacher, Teacher.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Override
    public void testUpdatePost() {
        int id = 1;
        Teacher teacher = restTemplate.getForObject(getRootUrl() + "/users/" + id, Teacher.class);
        teacher.setNameTeacher("Rodrigo profe(update)");

        restTemplate.put(getRootUrl() + "/users/" + id, teacher);

        Teacher updateTeacher = restTemplate.getForObject(getRootUrl() + "/users/" + id, Teacher.class);
        Assert.assertNotNull(updateTeacher);

    }

    @Override
    public void testDeletePost() {
        int id = 2;
        Teacher teacher = restTemplate.getForObject(getRootUrl() + "/users/" + id, Teacher.class);
        Assert.assertNotNull(teacher);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            teacher = restTemplate.getForObject(getRootUrl() + "/users/" + id, Teacher.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
