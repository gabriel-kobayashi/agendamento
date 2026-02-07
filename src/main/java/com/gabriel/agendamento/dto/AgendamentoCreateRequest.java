package com.gabriel.agendamento.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoCreateRequest(
        @NotNull(message = "Usuário é obrigatório")
        Long usuarioId,

        @NotNull(message = "Data de início é obrigatório")
        @Future(message = "Data de início deve estar no futuro")
        LocalDateTime dataInicio,

        @NotNull(message = "Data de fim é obrigatório")
        @Future(message = "Data de fim deve estar no futuro")
        LocalDateTime dataFim

) {
}
