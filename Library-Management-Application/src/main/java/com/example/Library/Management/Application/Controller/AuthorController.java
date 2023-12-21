package com.example.Library.Management.Application.Controller;

import com.example.Library.Management.Application.Entities.Author;
import com.example.Library.Management.Application.Service.AuthorService;
import com.example.Library.Management.Application.Entities.Author;
import com.example.Library.Management.Application.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        String result = authorService.addAuthor(author);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getAllAuthorNames")
    public List<String> getAllAuthorNames(){
        return authorService.getAllAuthorNames();
    }

    @GetMapping("/getAuthor/{id}")
    public ResponseEntity getAuthorById(@PathVariable("id") Integer Id){
        try{
            Author author = authorService.getAuthorById(Id);
            return new ResponseEntity<>(author,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookNameList")
    public ResponseEntity getBookList(@RequestParam("authorId") Integer authorId){
        try {
            List<String> result = authorService.getNameOfBooksList(authorId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
