package com.sneakerhouse.dto;

import com.sneakerhouse.entity.Cliente;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private LocalDateTime dataCadastro;

    public static ClienteDTO fromEntity(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .cpf(cliente.getCpf())
                .logradouro(cliente.getLogradouro())
                .numero(cliente.getNumero())
                .bairro(cliente.getBairro())
                .cidade(cliente.getCidade())
                .estado(cliente.getEstado())
                .cep(cliente.getCep())
                .dataCadastro(cliente.getDataCadastro())
                .build();
    }
}
