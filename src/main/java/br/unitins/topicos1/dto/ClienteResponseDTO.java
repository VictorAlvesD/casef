package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String email,
    List<TelefoneDTO> listaTelefone
) { 
    public static ClienteResponseDTO valueOf(Cliente cliente){
        if (cliente == null) 
            return null; 
        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getTelefone()
                .stream()
                .map(t -> TelefoneDTO.valueOf(t)).toList()
            
        );
    }
}
