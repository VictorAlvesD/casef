package br.unitins.topicos1.dto;

import java.util.List;

public record PagamentoDTO(
        String tipo,
        Float valor,
        List<PixDTO> pix,
        List<BoletoBancarioDTO> boletoBancario,
        List<CartaoCreditoDTO> cartaoCredito) {
}
