package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Administrador;

public record AdministradorResponseDTO(
    Long id,
    String nome,
    Integer matricula
) { 
    public static AdministradorResponseDTO valueOf(Administrador adm){
        return new AdministradorResponseDTO(
            adm.getId(), 
            adm.getNome(),
            adm.getMatricula()
            );
    }
}
