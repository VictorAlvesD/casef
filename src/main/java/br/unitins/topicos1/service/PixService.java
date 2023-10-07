package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PixDTO;
import br.unitins.topicos1.dto.PixResponseDTO;

public interface PixService {
    public PixResponseDTO insert(PixDTO dto);

    public PixResponseDTO update(PixDTO dto, Long id);

    public void delete(Long id);

    public PixResponseDTO findById(Long id);

    public List<PixResponseDTO> findByChave(String numero);

    public List<PixResponseDTO> findByAll(); 
}