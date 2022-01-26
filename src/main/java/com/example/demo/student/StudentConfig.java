package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//A configuration to save in database
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student mariam = new Student("Mariam","mariam@gmail.com", LocalDate.of(2000, Month.APRIL,5));
            Student alex = new  Student("Alex","AlexandruFechet@gmail.com", LocalDate.of(1992, Month.APRIL,5));
            //Save 2 object into a db
            studentRepository.saveAll(List.of(mariam, alex));
        };

    }
}
