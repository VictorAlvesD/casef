package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.CidadeResponseDTO;

public interface CidadeService {
    public CidadeResponseDTO insert(CidadeDTO dto);

    public CidadeResponseDTO update(CidadeDTO dto, Long id);

    public void delete(Long id);

    public CidadeResponseDTO findById(Long id);

    public List<CidadeResponseDTO> findByNome(String nome);

    public List<CidadeResponseDTO> findByAll(); 
}
