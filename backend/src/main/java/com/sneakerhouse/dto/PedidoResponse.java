package com.sneakerhouse.dto;

import com.sneakerhouse.entity.StatusPedido;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponse {

    private Long idPedido;
    private BigDecimal valorTotal;
    private StatusPedido status;
}
