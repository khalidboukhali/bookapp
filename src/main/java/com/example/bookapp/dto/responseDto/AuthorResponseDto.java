package com.example.bookapp.dto.responseDto;

import java.io.Serializable;

public record AuthorResponseDto(
        String lastName,
        String firstName
) implements Serializable {}
