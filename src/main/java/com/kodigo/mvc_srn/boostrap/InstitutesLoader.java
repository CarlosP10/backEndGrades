package com.kodigo.mvc_srn.boostrap;

import com.kodigo.mvc_srn.models.Institute;
import com.kodigo.mvc_srn.repository.InstituteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InstitutesLoader implements CommandLineRunner {
    public final InstituteRepository instituteRepository;

    public InstitutesLoader(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInstitutes();
    }

    private void loadInstitutes() {
        if (instituteRepository.count() == 0) {
            instituteRepository.save(
                    Institute.builder()
                            .institutionName("Colegio San Francisco")
                            .build()
            );
            instituteRepository.save(
                    Institute.builder()
                            .institutionName("Escuela San Francisco")
                            .build()
            );
            instituteRepository.save(
                    Institute.builder()
                            .institutionName("Colegio Denver")
                            .build()
            );
            System.out.println("Ejemplos cargados");
        }
    }
}
