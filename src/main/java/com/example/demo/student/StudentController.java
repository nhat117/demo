package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

//All resource for API
@RestController
@RequestMapping(path = "api/v1/student1")

public class StudentController {
    //N tier design pattern
    private final StudentService studentService;
    //Dependency injection, automatically instantiate
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudent();
   }

   //Take a payload and add to the system
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable ("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
