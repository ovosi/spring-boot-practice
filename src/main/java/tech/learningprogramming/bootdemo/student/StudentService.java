package tech.learningprogramming.bootdemo.student;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

    }

    public List<Student> getStudents() {

        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {

        if (!studentRepository.findById(id).isPresent())
            throw new IllegalStateException("No student with this id " + id);

        return studentRepository.getReferenceById(id);
    }

    public void addStudent(Student student) {

        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) throw new IllegalStateException("email taken");
        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) throw new IllegalStateException("No student with this id " + id);

        studentRepository.deleteById(id);

    }

    @Transactional
    public void updateStudent(Long id, String firstName, String lastName, String email) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                String.format("Student with id %d does not exist", id)));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName))
            student.setFirstName(firstName);
        if (lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName))
            student.setFirstName(lastName);

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);
            if (optionalStudent.isPresent()) throw new IllegalStateException("Email taken");
            student.setEmail(email);

        }

    }


}
