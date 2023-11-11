package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    String nome,
    String login,
    Perfil perfil
) { 
    public static UsuarioResponseDTO valueOf(Usuario usuario){
        return new UsuarioResponseDTO(
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getPerfil()
        );
    }
}