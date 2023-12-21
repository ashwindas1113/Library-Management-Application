package com.example.Library.Management.Application.Repository;

import com.example.Library.Management.Application.Entities.Book;
import com.example.Library.Management.Application.Entities.LibraryCard;
import com.example.Library.Management.Application.Entities.Transaction;
import com.example.Library.Management.Application.Enums.TransactionStatus;
import com.example.Library.Management.Application.Service.TransactionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book, LibraryCard libraryCard, TransactionStatus transactionStatus);

}
