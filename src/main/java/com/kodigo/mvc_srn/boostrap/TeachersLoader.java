package com.kodigo.mvc_srn.boostrap;

import com.kodigo.mvc_srn.models.Teacher;
import com.kodigo.mvc_srn.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TeachersLoader implements CommandLineRunner {
    public final TeacherRepository teacherRepository;

    public TeachersLoader(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadTeachers();
    }

    private void loadTeachers() {
        if (teacherRepository.count() == 0) {
            teacherRepository.save(
                    Teacher.builder()
                            .institutionId(1)
                            .nameTeacher("Juan Jose Ramos")
                            .build()
            );
            teacherRepository.save(
                    Teacher.builder()
                            .institutionId(1)
                            .nameTeacher("Ernesto De La Cruz")
                            .build()
            );
            System.out.println("Ejemplos Teachers cargados");
        }
    }
}
