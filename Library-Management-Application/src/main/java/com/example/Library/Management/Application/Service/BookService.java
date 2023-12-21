package com.example.Library.Management.Application.Service;

import com.example.Library.Management.Application.Entities.Author;
import com.example.Library.Management.Application.Entities.Book;
import com.example.Library.Management.Application.Enums.Genre;
import com.example.Library.Management.Application.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.Application.Repository.AuthorRepository;
import com.example.Library.Management.Application.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book, Integer authorId) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Author Id is Invalid or Not in the Database");
        }
        Author author = optionalAuthor.get();

        // Setting FK
        book.setAuthor(author);
        author.getBookList().add(book);

        authorRepository.save(author);
        return "Book has been added to Database";

    }

    public List<String> getBookByGenre(Genre genre){
        List<Book> bookList = bookRepository.findBookByGenre(genre);
        List<String> bookNames = new ArrayList<>();
        for(Book books: bookList){
            bookNames.add(books.getBookName());
        }
        return bookNames;
    }
}
