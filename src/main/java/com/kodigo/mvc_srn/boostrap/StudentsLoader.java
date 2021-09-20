package com.kodigo.mvc_srn.boostrap;

import com.kodigo.mvc_srn.models.Student;
import com.kodigo.mvc_srn.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentsLoader implements CommandLineRunner {
    public final StudentRepository studentRepository;

    public StudentsLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadStudents();
    }

    private void loadStudents() {
        if (studentRepository.count() == 0) {
            studentRepository.save(
                    Student.builder()
                            .institutionId(1)
                            .nameStudent("Carlos")
                            .lastnameStudent("Paredes")
                            .birthdate("19/03/1998")
                            .build()
            );
            studentRepository.save(
                    Student.builder()
                            .institutionId(1)
                            .nameStudent("Daniela")
                            .lastnameStudent("Lopez")
                            .birthdate("06/06/1998")
                            .build()
            );
            studentRepository.save(
                    Student.builder()
                            .institutionId(1)
                            .nameStudent("Julio")
                            .lastnameStudent("Giron")
                            .birthdate("26/08/1999")
                            .build()
            );
            System.out.println("Ejemplos cargados");
        }
    }
}
