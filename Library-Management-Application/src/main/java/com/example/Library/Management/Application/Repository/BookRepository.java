package com.example.Library.Management.Application.Repository;

import com.example.Library.Management.Application.Entities.Book;
import com.example.Library.Management.Application.Enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

   List<Book> findBookByGenre(Genre genre);
}
