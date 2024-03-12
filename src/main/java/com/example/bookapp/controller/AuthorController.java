package com.example.bookapp.controller;

import com.example.bookapp.dto.requestDto.AuthorRequestDto;
import com.example.bookapp.dto.responseDto.AuthorResponseDto;
import com.example.bookapp.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors(){
        List<AuthorResponseDto> authors = this.authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable long id){
        AuthorResponseDto author = this.authorService.getAuthorById(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<String> addAuthor(@RequestBody AuthorRequestDto authorDto){
        try {
            this.authorService.addAuthor(authorDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Author added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding author: " + e.getMessage());
        }
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<String>  updateAuthor(@PathVariable long authorId, @RequestBody AuthorRequestDto authorDto){
        try {
            this.authorService.updateAuthor(authorId, authorDto);
            return ResponseEntity.status(HttpStatus.OK).body("Author updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating author: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable long id){
        try {
            this.authorService.deleteAuthorById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Author deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting author: " + e.getMessage());
        }
    }
}
