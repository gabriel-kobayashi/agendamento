package com.gabriel.agendamento.controller;

import com.gabriel.agendamento.dto.UsuarioCreateRequest;
import com.gabriel.agendamento.dto.UsuarioResponse;
import com.gabriel.agendamento.model.Usuario;
import com.gabriel.agendamento.service.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl service;

    public UsuarioController(UsuarioServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody UsuarioCreateRequest request) {
        UsuarioResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        Usuario usuario = service.buscarUsuarioPorId(id);
        return ResponseEntity.ok(new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }
}
