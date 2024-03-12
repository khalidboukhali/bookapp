package com.example.bookapp.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public record BookRequestDto(
        @NotNull(message = "Titre is required")
        @Size(min = 3, message = "Titre should at least contain {min} characters")
        String titre,

        @NotNull(message = "Date of publication is required")
        @PastOrPresent(message = "Date of publication must be in the past")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date publicationDate
) implements Serializable {}
