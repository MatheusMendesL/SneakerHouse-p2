package com.sneakerhouse.controller;

import com.sneakerhouse.dto.*;
import com.sneakerhouse.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar cliente")
    public ResponseEntity<ClienteDTO> cadastrar(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.cadastrar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar cliente")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteUpdateRequest request) {
        return ResponseEntity.ok(clienteService.atualizar(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar cliente")
    public ResponseEntity<ClienteDTO> consultar(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.consultar(id));
    }

    @PostMapping("/login")
    @Operation(summary = "Login JWT")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(clienteService.login(request));
    }

    @PostMapping("/redefinir-senha")
    @Operation(summary = "Redefinir senha")
    public ResponseEntity<Void> redefinirSenha(@Valid @RequestBody RedefinirSenhaRequest request) {
        clienteService.redefinirSenha(request);
        return ResponseEntity.ok().build();
    }
}
