package com.example.bookapp.controller;

import com.example.bookapp.dto.requestDto.BookRequestDto;
import com.example.bookapp.dto.responseDto.BookResponseDto;
import com.example.bookapp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        List<BookResponseDto> books = this.bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable long id){
        BookResponseDto book = this.bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/{authorId}")
    public ResponseEntity<String> addBook(@PathVariable long authorId, @RequestBody @Valid BookRequestDto bookDto){
        try {
            this.bookService.addBook(authorId, bookDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding book: " + e.getMessage());
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String>  updateBook(@PathVariable long bookId, @RequestBody @Valid BookRequestDto bookDto){
        try {
            this.bookService.updateBook(bookId, bookDto);
            return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating book: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        try {
            this.bookService.deleteBookById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting book: " + e.getMessage());
        }
    }
}

