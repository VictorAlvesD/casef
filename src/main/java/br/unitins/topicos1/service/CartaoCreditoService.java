package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CartaoCreditoDTO;
import br.unitins.topicos1.dto.CartaoCreditoResponseDTO;

public interface CartaoCreditoService {
    public CartaoCreditoResponseDTO insert(CartaoCreditoDTO dto);

    public CartaoCreditoResponseDTO update(CartaoCreditoDTO dto, Long id);

    public void delete(Long id);

    public CartaoCreditoResponseDTO findById(Long id);

    public List<CartaoCreditoResponseDTO> findByBandeira(String bandeira);

    public List<CartaoCreditoResponseDTO> findByAll(); 
}