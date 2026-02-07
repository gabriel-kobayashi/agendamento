package com.gabriel.agendamento.service;

import com.gabriel.agendamento.model.Agendamento;

public interface AgendamentoService {

    Agendamento criar(Long usuarioId);

    Agendamento atualizar(Long id);

    void cancelar(Long id);

    Agendamento buscarPorId(Long id);
}
