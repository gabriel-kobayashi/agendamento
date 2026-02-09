package com.gabriel.agendamento.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @Email(message = "Email inválido")
        String email
) {
}
