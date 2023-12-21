package com.example.Library.Management.Application.Entities;

import com.example.Library.Management.Application.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "library card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private String nameOnCard;

    private Integer NoOfBooksIssued;

    // To Connect Two Tables
    //---------------------------------------------
    // Here Library card is child table to Parent "Student" table, So FK should exist in this table to connect tables
    // Library Card --> Student Card ( One to One Mapping)

    @OneToOne // Mapping Type
    @JoinColumn // For FK Column i.e. PK of Student Table
    private Student student;// Entity or Table to which it must be connected

    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
