package com.example.Library.Management.Application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(nullable = false)
    private String authorName;

    private int age;

    private double rating;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

}
