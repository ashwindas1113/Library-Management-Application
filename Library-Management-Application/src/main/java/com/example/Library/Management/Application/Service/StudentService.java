package com.example.Library.Management.Application.Service;

import com.example.Library.Management.Application.Entities.Student;
import com.example.Library.Management.Application.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.lang.String;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String addStudent(Student student){
        studentRepository.save(student);

        for(int i = 0; i < 300; i++){
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            String body = "Dekho aap maa bn gyi, Mithai lekin abhi tk ni aayi :(";
            mailMessage.setFrom("sayhellotoworld2023@gmail.com");
            mailMessage.setTo("humerakhedawala2@gmail.com");
            mailMessage.setSubject("Humera? We got some exciting new for you!!!");
            mailMessage.setText(body);
            mailSender.send(mailMessage);
        }
        return "Student has been added to Database";
    }

}
