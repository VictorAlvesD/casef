package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO (
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    @NotBlank(message = "O campo login não pode ser nulo.")
    String login,
    @NotBlank(message = "O campo senha não pode ser nulo.")
    String senha,
    String cpf
) {

}