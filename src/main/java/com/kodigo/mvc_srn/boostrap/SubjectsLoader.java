package com.kodigo.mvc_srn.boostrap;

import com.kodigo.mvc_srn.models.Subject;
import com.kodigo.mvc_srn.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SubjectsLoader implements CommandLineRunner {
    public final SubjectRepository subjectRepository;

    public SubjectsLoader(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSubjects();
    }

    private void loadSubjects() {
        if (subjectRepository.count() == 0) {
            subjectRepository.save(
                    Subject.builder()
                            .nameSubject("Sociales")
                            .build()
            );
            subjectRepository.save(
                    Subject.builder()
                            .nameSubject("Sociales")
                            .build()
            );
            subjectRepository.save(
                    Subject.builder()
                            .nameSubject("Sociales")
                            .build()
            );
            System.out.println("Ejemplos Subject cargados");
        }
    }
}
