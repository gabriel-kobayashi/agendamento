package com.gabriel.agendamento.service;

import com.gabriel.agendamento.model.Agendamento;

public interface AgendamentoService {

    Agendamento criar(AgendamentoCreateRequest request);

    Agendamento atualizar(Long id, AgendamentoUpdateRequest request);

    void cancelar(Long id);

    Agendamento buscarPorId(Long id);
}
