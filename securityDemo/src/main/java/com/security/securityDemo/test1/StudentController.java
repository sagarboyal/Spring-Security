package com.security.securityDemo.test1;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private final List<Student> studentList = new ArrayList<>(List.of(
            new Student(1, "Sagar", 85),
            new Student(2, "Santu", 86),
            new Student(3, "Saikat", 85),
            new Student(4, "Arnab", 86),
            new Student(5, "Ankur", 85),
            new Student(6, "Sanjib", 86)
    ));

    @GetMapping("/students")
    public List<Student> getHome(){
        return studentList;
    }

    @PostMapping("/students")
    public List<Student> addStudent(@RequestBody Student student){
        studentList.add(student);
        return getHome();
    }
}
