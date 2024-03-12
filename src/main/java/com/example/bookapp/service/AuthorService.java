package com.example.bookapp.service;

import com.example.bookapp.dto.requestDto.AuthorRequestDto;
import com.example.bookapp.dto.responseDto.AuthorResponseDto;
import com.example.bookapp.entities.Author;
import com.example.bookapp.exception.EntityNotFoundException;
import com.example.bookapp.mapper.AuthorMapper;
import com.example.bookapp.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDto getAuthorById(long id) {
        Author author = this.authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + id + " not found"));
        return AuthorMapper.INSTANCE.toDto(author);
    }

    public List<AuthorResponseDto> getAllAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        return authors.stream()
                .map(AuthorMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public void addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = AuthorMapper.INSTANCE.toEntity(authorRequestDto);
        this.authorRepository.save(author);
    }

    public void updateAuthor(long authorId, AuthorRequestDto updatedAuthorDto) {
        getAuthorById(authorId);
        Author author = AuthorMapper.INSTANCE.toEntity(updatedAuthorDto);
        author.setId(authorId);
        this.authorRepository.save(author);
    }

    public void deleteAuthorById(long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            this.authorRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Author with id " + id + " not found");
        }
    }
}
