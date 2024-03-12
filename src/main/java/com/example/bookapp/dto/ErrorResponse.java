package com.example.bookapp.dto;

public record ErrorResponse (
     int statusCode,
     String message
){}
