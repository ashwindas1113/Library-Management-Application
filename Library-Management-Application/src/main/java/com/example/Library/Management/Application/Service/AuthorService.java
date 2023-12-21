package com.example.Library.Management.Application.Service;

import com.example.Library.Management.Application.Entities.Author;
import com.example.Library.Management.Application.Entities.Book;
import com.example.Library.Management.Application.Exceptions.AuthorNotFoundException;
import com.example.Library.Management.Application.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author){
        authorRepository.save(author);
        return "Author has been added to Database";
    }

    public List<String> getAllAuthorNames(){
        List<Author> authorList = authorRepository.findAll();
        List<String> authorNames = new ArrayList<>();
        for(Author author: authorList){
            authorNames.add(author.getAuthorName());
        }
        return authorNames;
    }

    public Author getAuthorById(Integer Id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(Id);
        if(!optionalAuthor.isPresent()){
            throw new Exception("The Id entered is not available in the database");
        }
        Author author = optionalAuthor.get();
        return author;
    }

    public List<String> getNameOfBooksList(Integer authorId) throws AuthorNotFoundException {
        List<String> ans = new ArrayList<>();
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(!optionalAuthor.isPresent()){
            throw new AuthorNotFoundException("Author Id is not in the database");
        }
        Author author = optionalAuthor.get();
        List<Book> bookList = author.getBookList();
        for(Book books: bookList){
            ans.add(books.getBookName());
        }
        return ans;
    }

}
