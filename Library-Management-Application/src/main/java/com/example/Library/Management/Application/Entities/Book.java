package com.example.Library.Management.Application.Entities;

import com.example.Library.Management.Application.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String bookName;

    private int price;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private int noOfPages;

    private double rating;

    private boolean isAvailable;

    // Here Book table is Child table to Author Table
    // So FK should exist here to connect two tables
    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
