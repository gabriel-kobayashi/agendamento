package com.gabriel.agendamento.service;

import com.gabriel.agendamento.dto.UsuarioCreateRequest;
import com.gabriel.agendamento.dto.UsuarioResponse;
import com.gabriel.agendamento.model.Usuario;

import java.util.Optional;

public interface UsuarioService {

    UsuarioResponse criar(UsuarioCreateRequest request);

    Usuario buscarUsuarioPorId(Long id);
}
