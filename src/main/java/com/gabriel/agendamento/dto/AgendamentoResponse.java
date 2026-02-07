package com.gabriel.agendamento.dto;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        Long usuarioId,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        String status
) {
}
