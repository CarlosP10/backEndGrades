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
                            .teacherId(1)
                            .studentId(1)
                            .nameSubject("Sociales")
                            .nameDate("02/02/2021")
                            .build()
            );
            subjectRepository.save(
                    Subject.builder()
                            .teacherId(1)
                            .studentId(2)
                            .nameSubject("Sociales")
                            .nameDate("02/02/2021")
                            .build()
            );
            subjectRepository.save(
                    Subject.builder()
                            .teacherId(1)
                            .studentId(3)
                            .nameSubject("Sociales")
                            .nameDate("02/02/2021")
                            .build()
            );
            System.out.println("Ejemplos Subject cargados");
        }
    }
}
