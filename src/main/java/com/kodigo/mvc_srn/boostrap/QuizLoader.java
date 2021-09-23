package com.kodigo.mvc_srn.boostrap;

import com.kodigo.mvc_srn.models.Quiz;
import com.kodigo.mvc_srn.repository.QuizRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QuizLoader implements CommandLineRunner {
    public final QuizRepository quizRepository;

    public QuizLoader(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadQuizs();
    }

    private void loadQuizs() {
        if (quizRepository.count() == 0) {
            quizRepository.save(
                    Quiz.builder()
                            .quizName("Primer examen")
                            .quizNote(7.6)
                            .build()
            );
            quizRepository.save(
                    Quiz.builder()
                            .quizName("Segundo examen")
                            .quizNote(8.6)
                            .build()
            );
            quizRepository.save(
                    Quiz.builder()
                            .quizName("Tercer examen")
                            .quizNote(9.6)
                            .build()
            );
            System.out.println("Ejemplos Quiz cargados");
        }
    }
}
