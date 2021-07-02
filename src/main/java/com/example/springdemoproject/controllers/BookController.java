package com.example.springdemoproject.controllers;

import com.example.springdemoproject.data.Book;
import com.example.springdemoproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) throws Exception {
        return bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book with id # " + id +" not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book with id # " + id +" not found"));
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) throws Exception {
        if (book.getId() != id) {
            throw new Exception("Mismatch ID");
        }
        bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book with id # " + id +" not found"));
        return bookRepository.save(book);
    }
}

