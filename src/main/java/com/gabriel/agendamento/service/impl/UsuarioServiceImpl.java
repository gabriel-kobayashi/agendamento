package com.gabriel.agendamento.service.impl;

import com.gabriel.agendamento.dto.UsuarioCreateRequest;
import com.gabriel.agendamento.dto.UsuarioResponse;
import com.gabriel.agendamento.exception.RecursoNaoEncontradoException;
import com.gabriel.agendamento.model.Usuario;
import com.gabriel.agendamento.repository.UsuarioRepository;
import com.gabriel.agendamento.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsuarioResponse criar(UsuarioCreateRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());

        repository.save(usuario);

        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

}
