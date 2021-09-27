package com.kodigo.mvc_srn.tests;

import com.kodigo.mvc_srn.MvcProjectApplication;
import com.kodigo.mvc_srn.models.Quiz;
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

public class QuizTest implements AbstractTest {

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
        Quiz quiz = restTemplate.getForObject(getRootUrl() + "/users/1", Quiz.class);
        System.out.println(quiz.getQuizName());
        Assert.assertNotNull(quiz);
    }

    @Override
    public void testCreateUser() {
        Quiz quiz = new Quiz();
        quiz.setQuizName("Quiz creado");

        ResponseEntity<Quiz> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", quiz, Quiz.class);
        Assert.assertNotNull(postResponse);
        Assert.assertNotNull(postResponse.getBody());
    }

    @Override
    public void testUpdatePost() {
        int id = 1;
        Quiz quiz = restTemplate.getForObject(getRootUrl() + "/users/" + id, Quiz.class);
        quiz.setQuizName("Otro nombre(update)");

        restTemplate.put(getRootUrl() + "/users/" + id, quiz);

        Quiz updateQuiz = restTemplate.getForObject(getRootUrl() + "/users/" + id, Quiz.class);
        Assert.assertNotNull(updateQuiz);
    }

    @Override
    public void testDeletePost() {
        int id = 2;
        Quiz quiz = restTemplate.getForObject(getRootUrl() + "/users/" + id, Quiz.class);
        Assert.assertNotNull(quiz);

        restTemplate.delete(getRootUrl() + "/users/" + id);

        try {
            quiz = restTemplate.getForObject(getRootUrl() + "/users/" + id, Quiz.class);
        } catch (final HttpClientErrorException e) {
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
