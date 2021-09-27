package com.kodigo.mvc_srn.tests;

import com.kodigo.mvc_srn.MvcProjectApplication;
import com.kodigo.mvc_srn.models.Student;
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

public class StudentTest implements AbstractTest {

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
        Student student = restTemplate.getForObject(getRootUrl() + "/users/1", Student.class);
        System.out.println(student.getNameStudent());
        Assert.assertNotNull(student);
    }

    @Override
    public void testCreateUser() {
        Student student = new Student();
        student.setNameStudent("Juls");
        student.setLastnameStudent("Flores");
        student.setBirthdate("26/02/9999");
        student.setId(5);

        ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", student, Student.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Override
    public void testUpdatePost() {
        int id = 1;
        Student student = restTemplate.getForObject(getRootUrl() + "/users/" + id, Student.class);
        student.setNameStudent("Juls2(update)");

        restTemplate.put(getRootUrl() + "/users/" + id, student);

        Student updateStudent = restTemplate.getForObject(getRootUrl() + "/users/" + id, Student.class);
        Assert.assertNotNull(updateStudent);

    }

    @Override
    public void testDeletePost() {
        int id = 2;
        Student student = restTemplate.getForObject(getRootUrl() + "/users/" + id, Student.class);
        Assert.assertNotNull(student);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            student = restTemplate.getForObject(getRootUrl() + "/users/" + id, Student.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
