package com.sneakerhouse.service;

import com.sneakerhouse.dto.*;
import com.sneakerhouse.entity.Cliente;
import com.sneakerhouse.exception.BusinessException;
import com.sneakerhouse.exception.ResourceNotFoundException;
import com.sneakerhouse.repository.ClienteRepository;
import com.sneakerhouse.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ClienteService(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public ClienteDTO cadastrar(ClienteRequest request) {
        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já cadastrado");
        }
        if (request.getCpf() != null && !request.getCpf().isBlank() && clienteRepository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF já cadastrado");
        }

        Cliente cliente = Cliente.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .telefone(request.getTelefone())
                .cpf(request.getCpf())
                .logradouro(request.getLogradouro())
                .numero(request.getNumero())
                .bairro(request.getBairro())
                .cidade(request.getCidade())
                .estado(request.getEstado())
                .cep(request.getCep())
                .build();

        return ClienteDTO.fromEntity(clienteRepository.save(cliente));
    }

    @Transactional
    public ClienteDTO atualizar(Long id, ClienteUpdateRequest request) {
        Cliente cliente = buscarEntidade(id);

        clienteRepository.findByEmail(request.getEmail())
                .filter(c -> !c.getId().equals(id))
                .ifPresent(c -> {
                    throw new BusinessException("Email já cadastrado");
                });

        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        cliente.setTelefone(request.getTelefone());
        cliente.setCpf(request.getCpf());
        cliente.setLogradouro(request.getLogradouro());
        cliente.setNumero(request.getNumero());
        cliente.setBairro(request.getBairro());
        cliente.setCidade(request.getCidade());
        cliente.setEstado(request.getEstado());
        cliente.setCep(request.getCep());

        return ClienteDTO.fromEntity(clienteRepository.save(cliente));
    }

    @Transactional(readOnly = true)
    public ClienteDTO consultar(Long id) {
        return ClienteDTO.fromEntity(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(request.getSenha(), cliente.getSenha())) {
            throw new BusinessException("Email ou senha inválidos");
        }

        String token = jwtService.generateToken(cliente.getEmail(), cliente.getId());

        return LoginResponse.builder()
                .token(token)
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();
    }

    @Transactional
    public void redefinirSenha(RedefinirSenhaRequest request) {
        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com este email"));

        cliente.setSenha(passwordEncoder.encode(request.getNovaSenha()));
        clienteRepository.save(cliente);
    }

    public Cliente buscarEntidade(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }
}
