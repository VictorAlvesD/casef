package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.AdministradorDTO;
import br.unitins.topicos1.dto.AdministradorResponseDTO;

public interface AdministradorService {

   public AdministradorResponseDTO insert(AdministradorDTO dto);

    public AdministradorResponseDTO update(AdministradorDTO dto, Long id);

    public void delete(Long id);

    public AdministradorResponseDTO findById(Long id);

    public List<AdministradorResponseDTO> findByNome(String nome);

    public List<AdministradorResponseDTO> findByAll(); 
    
}
