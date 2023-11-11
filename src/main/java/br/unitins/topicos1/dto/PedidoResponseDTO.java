package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Pedido;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime dataHora,
    ClienteResponseDTO cliente,
    Double totalPedido
) { 
    public static PedidoResponseDTO valueOf(Pedido pedido){
        return new PedidoResponseDTO(
            pedido.getId(), 
            pedido.getDataHora(),
            ClienteResponseDTO.valueOf(pedido.getCliente()),
            pedido.getTotalPedido()
        );
    }
}
