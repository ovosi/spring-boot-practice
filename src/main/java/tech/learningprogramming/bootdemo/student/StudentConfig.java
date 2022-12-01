package tech.learningprogramming.bootdemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {

        return args -> {
            ArrayList<Student> students = new ArrayList<Student>();
            students.add(new Student("Luqman", "Muhammad", "Kogi", LocalDate.of(1992, Month.APRIL,3), "ovosioluqman@gmail.com"));
            students.add(new Student("Ayuba", "Umar", "Lagos", LocalDate.of(2003, Month.JANUARY,4), "eneye@gmail.com"));

            studentRepository.saveAll(students);
        };


    }
}