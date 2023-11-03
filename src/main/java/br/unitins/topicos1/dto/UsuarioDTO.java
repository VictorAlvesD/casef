package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO (
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O campo login não pode ser nulo.")
    String login,
    @NotBlank(message = "O campo senha não pode ser nulo.")
    String senha,
    @NotBlank(message = "O campo CPF não pode ser nulo.")
    String cpf,
    @NotNull(message = "O campo perfil não pode ser nulo.")
    Integer idPerfil
) {

}