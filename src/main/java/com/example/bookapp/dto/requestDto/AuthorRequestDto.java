package com.example.bookapp.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AuthorRequestDto (
        @NotNull(message = "Last name is required")
        @Size(min = 3, message = "Last name should at least contain {min} characters")
        String lastName,

        @NotNull(message = "First name is required")
        @Size(min = 3, message = "First name should at least contain {min} characters")
        String firstName
) implements Serializable {}
