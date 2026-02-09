package com.gabriel.agendamento.service.impl;

import com.gabriel.agendamento.dto.AgendamentoCreateRequest;
import com.gabriel.agendamento.dto.AgendamentoResponse;
import com.gabriel.agendamento.dto.AgendamentoUpdateRequest;
import com.gabriel.agendamento.mapper.AgendamentoMapper;
import com.gabriel.agendamento.model.Agendamento;
import com.gabriel.agendamento.model.StatusAgendamento;
import com.gabriel.agendamento.model.Usuario;
import com.gabriel.agendamento.repository.AgendamentoRepository;
import com.gabriel.agendamento.repository.UsuarioRepository;
import com.gabriel.agendamento.service.AgendamentoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;

    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository, UsuarioRepository usuarioRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public AgendamentoResponse criar(AgendamentoCreateRequest request) {

        validarIntervalo(request.dataInicio(), request.dataFim());

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Agendamento agendamento = AgendamentoMapper.toEntity(request, usuario);

        agendamentoRepository.save(agendamento);

        return AgendamentoMapper.toResponse(agendamento);
    }

    @Override
    public AgendamentoResponse atualizar(Long id, AgendamentoUpdateRequest request) {

        validarIntervalo(request.dataInicio(), request.dataFim());

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        AgendamentoMapper.updateEntity(agendamento, request);

        return AgendamentoMapper.toResponse(agendamento);
    }

    @Override
    public void cancelar(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        agendamento.setStatus(StatusAgendamento.CANCELADO);
    }

    @Override
    @Transactional
    public AgendamentoResponse buscarPorId(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        return AgendamentoMapper.toResponse(agendamento);
    }

    @Override
    @Transactional
    public List<AgendamentoResponse> listarPorUsuario(Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return agendamentoRepository.findByUsuario(usuario)
                .stream()
                .map(AgendamentoMapper::toResponse)
                .toList();
    }

    private void validarIntervalo(LocalDateTime inicio, LocalDateTime fim) {
        if (!inicio.isBefore(fim)) {
            throw new IllegalArgumentException("Data de início tem que ser anterior à data de fim");
        }
    }
}
