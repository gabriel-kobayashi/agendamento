package com.gabriel.agendamento.exception;

public record FieldErrorResponse(
        String field,
        String message
) {
}
