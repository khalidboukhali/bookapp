package com.example.bookapp.mapper;

import com.example.bookapp.dto.requestDto.BookRequestDto;
import com.example.bookapp.dto.responseDto.BookResponseDto;
import com.example.bookapp.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    BookResponseDto toDto(Book book);
    Book toEntity(BookRequestDto bookDto);
}
