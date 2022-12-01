package tech.learningprogramming.bootdemo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sms")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public List<Student> getStudents() {

        return studentService.getStudents();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable("id") Long id) {

        return studentService.getStudent(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {

        studentService.addStudent(student);

    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {

        studentService.deleteStudent(id);

    }

    @PutMapping("{studentId}")
    public void updateStudent(

            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email
    ) {

        studentService.updateStudent(id, firstName, lastName, email);
    }
}
