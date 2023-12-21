package com.example.Library.Management.Application.Controller;

import com.example.Library.Management.Application.Entities.Book;
import com.example.Library.Management.Application.Enums.Genre;
import com.example.Library.Management.Application.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId){
        try{
            String result = bookService.addBook(book, authorId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookByGenre")
    public List<String> getBookByGenre(Genre genre){
         List<String> ans = bookService.getBookByGenre(genre);
         return ans;
    }
}
