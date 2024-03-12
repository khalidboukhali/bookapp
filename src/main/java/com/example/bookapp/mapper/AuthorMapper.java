package com.example.bookapp.mapper;

import com.example.bookapp.dto.requestDto.AuthorRequestDto;
import com.example.bookapp.dto.responseDto.AuthorResponseDto;
import com.example.bookapp.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);
    AuthorResponseDto toDto(Author author);
    Author toEntity(AuthorRequestDto authorDto);
    Author toEntity(AuthorResponseDto authorDto);
}