package com.example.demo.student;

//////////////////Business logic goes here

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    //Dependency Injection
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    //POST
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw  new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    //DELETE
    public void deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId)) {
            throw new IllegalStateException(studentId + " Not found");
        }
        studentRepository.deleteById(studentId);
    }

    //PUT
    @Transactional  //Entities Data JPA
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(studentId + " not found"));
        if(name != null && name.length() > 0 && !Objects.equals(name, student.getName())) {student.setName(name);}
        if(email != null && email.length() > 0 && !Objects.equals(email,student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) throw new IllegalStateException(email + "  is taken");
            student.setEmail(email);
        }
    }

}
