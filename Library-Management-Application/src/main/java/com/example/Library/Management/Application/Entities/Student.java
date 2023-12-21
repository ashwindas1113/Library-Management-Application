package com.example.Library.Management.Application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Annotation is used to define Schema of Tables
@Table(name = "student") // Table name( if you don't define table name, it will take entity name as default table name)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Student {
  @Id // This is used to define your Primary Key of table
  private Integer studentId;

  private int age;

  private String name;

  private String mobNo;

  private String emailId;

  private String bloodGroup;

  // Bidirectional mapping = storing child info in parent table
  @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)// "Foreign Key variable name" in the child table
  private LibraryCard libraryCard;

}
