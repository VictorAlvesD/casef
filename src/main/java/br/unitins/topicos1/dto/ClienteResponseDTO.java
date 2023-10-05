package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Cliente;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String email,
    List<TelefoneDTO> listaTelefone,
    List<EnderecoDTO> listaEndereco
) { 
    public static ClienteResponseDTO valueOf(Cliente cliente){

        return new ClienteResponseDTO(
            cliente.getId(), 
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getTelefone()
                .stream()
                .map(t -> TelefoneDTO.valueOf(t)).toList(),
            cliente.getEndereco()
                .stream()
                .map(t -> EnderecoDTO.valueOf(t)).toList()
        );
    }
}
