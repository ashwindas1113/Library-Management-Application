package com.example.Library.Management.Application.Service;

import com.example.Library.Management.Application.Entities.Book;
import com.example.Library.Management.Application.Entities.LibraryCard;
import com.example.Library.Management.Application.Entities.Transaction;
import com.example.Library.Management.Application.Enums.CardStatus;
import com.example.Library.Management.Application.Enums.TransactionStatus;
import com.example.Library.Management.Application.Exceptions.*;
import com.example.Library.Management.Application.Repository.BookRepository;
import com.example.Library.Management.Application.Repository.CardRepository;
import com.example.Library.Management.Application.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    private static final Integer maximum_limit = 3;
    private static final Integer finePerDay = 5;

    public String issueBook(Integer bookId, Integer cardId) throws Exception{

        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.Pending);

        //Validation checks
        //1. Valid Book ID & availability
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(!optionalBook.isPresent()){
            throw new BookNotFoundException("Book Id is not valid or not In Database");
        }
        Book book = optionalBook.get();
//
        if(!book.isAvailable()){
            throw new BookNotAvailableException("Book is currently Not available");
        }

        //2. Valid Card ID & status
        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardId);

        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFoundException("Card Id not valid or not in Database");
        }

        LibraryCard libraryCard = optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.Active)){
            throw new InvalidCardStatusException("Card is Not Active");
        }

        //limiting maximum borrowings
        Integer noOfBooksIssued = libraryCard.getNoOfBooksIssued() == null ? 0 : libraryCard.getNoOfBooksIssued();

        if(noOfBooksIssued ==maximum_limit){
            throw new MaximumLimitOfBoooksException("Maximum limit :" + maximum_limit + "No of Books has been issued");
        }

        // setting transaction entity for relevant  variables
        transaction.setTransactionStatus(TransactionStatus.Issued);
        libraryCard.setNoOfBooksIssued(noOfBooksIssued + 1);
        book.setAvailable(false);

        //setting FK
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        // saving entities
        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

        transactionRepository.save(transaction);

        return "The Book with bookId" +bookId+ " has been issued to card with cardId" + cardId;
    }

    public String returnBook(Integer bookId, Integer cardId){
        Book book = bookRepository.findById(bookId).get();
        LibraryCard libraryCard = cardRepository.findById(cardId).get();

        Transaction transaction = transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus(book,libraryCard,TransactionStatus.Issued);
        Date issueDate = transaction.getCreatedOn();

        long milliseconds = Math.abs(System.currentTimeMillis()-issueDate.getTime());
        long days = TimeUnit.DAYS.convert(milliseconds,TimeUnit.MILLISECONDS);

        int fine = 0;

        if(days > 15){
         fine = (int) ((days-15) * finePerDay);
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionStatus(TransactionStatus.Completed);

        newTransaction.setReturnedDate(new Date());
        newTransaction.setFine(fine);

        newTransaction.setBook(book);
        newTransaction.setLibraryCard(libraryCard);

        book.setAvailable(true);

        Integer noOfBooksIssued = libraryCard.getNoOfBooksIssued() == null ? 0 : libraryCard.getNoOfBooksIssued();

        libraryCard.setNoOfBooksIssued(noOfBooksIssued - 1);

        book.getTransactionList().add(newTransaction);
        libraryCard.getTransactionList().add(newTransaction);

        transactionRepository.save(newTransaction);

        return "Book has been returned";

    }


}
