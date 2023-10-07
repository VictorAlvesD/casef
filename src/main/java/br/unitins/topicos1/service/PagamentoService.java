package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;

public interface PagamentoService {
    public PagamentoResponseDTO insert(PagamentoDTO dto);

    public PagamentoResponseDTO update(PagamentoDTO dto, Long id);

    public void delete(Long id);

    public PagamentoResponseDTO findById(Long id);

    public List<PagamentoResponseDTO> findByTipo(String numero);

    public List<PagamentoResponseDTO> findByAll(); 
}