package com.gabriel.agendamento.controller;

import com.gabriel.agendamento.dto.AgendamentoCreateRequest;
import com.gabriel.agendamento.dto.AgendamentoResponse;
import com.gabriel.agendamento.dto.AgendamentoUpdateRequest;
import com.gabriel.agendamento.service.impl.AgendamentoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoServiceImpl service;

    public AgendamentoController(AgendamentoServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponse> criar(@Valid @RequestBody AgendamentoCreateRequest request) {
        AgendamentoResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody AgendamentoUpdateRequest request) {
        AgendamentoResponse response = service.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarPorId(@PathVariable Long id) {
        AgendamentoResponse response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AgendamentoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<AgendamentoResponse> responses = service.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(responses);
    }
}
