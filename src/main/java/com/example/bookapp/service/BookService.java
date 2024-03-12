package com.example.bookapp.service;

import com.example.bookapp.dto.requestDto.BookRequestDto;
import com.example.bookapp.dto.responseDto.AuthorResponseDto;
import com.example.bookapp.dto.responseDto.BookResponseDto;
import com.example.bookapp.entities.Author;
import com.example.bookapp.entities.Book;
import com.example.bookapp.exception.EntityNotFoundException;
import com.example.bookapp.mapper.AuthorMapper;
import com.example.bookapp.mapper.BookMapper;
import com.example.bookapp.repository.AuthorRepository;
import com.example.bookapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    public BookResponseDto getBookById(long id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + id + " not found"));
        return BookMapper.INSTANCE.toDto(book);
    }

    public List<BookResponseDto> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return books.stream()
                .map(BookMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void addBook(long authorId, BookRequestDto bookDto) {
        AuthorResponseDto authorResponseDto = this.authorService.getAuthorById(authorId);
        Book book = BookMapper.INSTANCE.toEntity(bookDto);
        Author author = AuthorMapper.INSTANCE.toEntity(authorResponseDto);
        book.setAuthor(author);
        author.setId(authorId);
        this.authorRepository.save(author);
        this.bookRepository.save(book);
    }

    public void updateBook(long bookId, BookRequestDto updatedBookDto) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book with id " + bookId + " not found"));
        book.setTitre(updatedBookDto.titre());
        book.setPublicationDate(updatedBookDto.publicationDate());
        this.bookRepository.save(book);
    }

    public void deleteBookById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            this.bookRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }
    }
}
