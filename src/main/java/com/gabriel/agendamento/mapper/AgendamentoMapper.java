package com.gabriel.agendamento.mapper;

import com.gabriel.agendamento.dto.AgendamentoCreateRequest;
import com.gabriel.agendamento.dto.AgendamentoResponse;
import com.gabriel.agendamento.dto.AgendamentoUpdateRequest;
import com.gabriel.agendamento.model.Agendamento;
import com.gabriel.agendamento.model.StatusAgendamento;
import com.gabriel.agendamento.model.Usuario;

public class AgendamentoMapper {

    public static Agendamento toEntity(AgendamentoCreateRequest request, Usuario usuario) {
        Agendamento agendamento = new Agendamento();
        agendamento.setUsuario(usuario);
        agendamento.setDataInicio(request.dataInicio());
        agendamento.setDataFim(request.dataFim());
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        return agendamento;
    }

    public static void updateEntity(Agendamento agendamento, AgendamentoUpdateRequest request) {
        if (request.dataInicio() != null) {
            agendamento.setDataInicio(request.dataInicio());
        }
        if (request.dataFim() != null) {
            agendamento.setDataFim(request.dataFim());
        }
    }

    public static AgendamentoResponse toResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getUsuario().getId(),
                agendamento.getDataInicio(),
                agendamento.getDataFim(),
                agendamento.getStatus().name()
        );
    }
}
