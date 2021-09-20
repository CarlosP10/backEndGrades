package com.kodigo.mvc_srn.boostrap;

import com.kodigo.mvc_srn.models.Degree;
import com.kodigo.mvc_srn.repository.DegreeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DegreeLoader implements CommandLineRunner {
    public final DegreeRepository degreeRepository;

    public DegreeLoader(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadDegrees();
    }

    private void loadDegrees() {
        if (degreeRepository.count() == 0) {
            degreeRepository.save(
                    Degree.builder()
                            .teacherId(1)
                            .studentId(3)
                            .subjectId(2)
                            .nameDegree("Mathematics")
                            .build()
            );
            degreeRepository.save(
                    Degree.builder()
                            .teacherId(2)
                            .studentId(1)
                            .subjectId(2)
                            .nameDegree("Sociales")
                            .build()
            );
            degreeRepository.save(
                    Degree.builder()
                            .teacherId(1)
                            .studentId(3)
                            .subjectId(2)
                            .nameDegree("Ciencias")
                            .build()
            );
            System.out.println("Ejemplos Degrees cargados");
        }
    }
}
