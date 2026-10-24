package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    private List<String> students = Arrays.asList(
        "Joey - Computer Science",
        "Chris  - Mechanical",
        "Monica - Electrical",
        "Rachel - Electronics",
        "Chandler - Information Technology"
    );

    public List<String> getStudents() {
        return students;
    }
}