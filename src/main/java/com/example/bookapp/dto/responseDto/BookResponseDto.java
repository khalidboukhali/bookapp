package com.example.bookapp.dto.responseDto;

import java.io.Serializable;
import java.util.Date;

public record BookResponseDto(
        String titre,
        Date publicationDate,
        AuthorResponseDto author
) implements Serializable {}
