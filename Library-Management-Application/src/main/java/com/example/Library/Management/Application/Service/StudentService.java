package com.example.Library.Management.Application.Service;

import com.example.Library.Management.Application.Entities.Student;
import com.example.Library.Management.Application.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student){
        studentRepository.save(student);
        return "Student has been added to Database";
    }
}
