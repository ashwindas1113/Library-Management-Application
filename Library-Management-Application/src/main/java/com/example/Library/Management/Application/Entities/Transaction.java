package com.example.Library.Management.Application.Entities;

import com.example.Library.Management.Application.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private Date issuedDate;

    private Date returnedDate;

    private Integer fine;

    @CreationTimestamp
    private Date createdOn;

    private Date LastModifiedOn;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;

}
