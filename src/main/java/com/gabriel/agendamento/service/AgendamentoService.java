package com.gabriel.agendamento.service;

import com.gabriel.agendamento.dto.AgendamentoCreateRequest;
import com.gabriel.agendamento.dto.AgendamentoResponse;
import com.gabriel.agendamento.dto.AgendamentoUpdateRequest;
import com.gabriel.agendamento.model.Agendamento;

public interface AgendamentoService {

    AgendamentoResponse criar(AgendamentoCreateRequest request);

    AgendamentoResponse atualizar(Long id, AgendamentoUpdateRequest request);

    void cancelar(Long id);

    AgendamentoResponse buscarPorId(Long id);
}
