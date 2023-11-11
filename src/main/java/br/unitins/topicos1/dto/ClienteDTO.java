package br.unitins.topicos1.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(
        @NotBlank(message = "O campo nome não pode ser nulo.") String nome,
        @NotBlank(message = "O campo email não pode ser nulo.") @Email(message = "O campo email não esta no formato correto!") String login,
        @NotBlank(message = "O campo senha não pode ser nulo.") String senha,
        @NotBlank(message = "O campo CPF não pode ser nulo.") @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$", message = "CPF inválido") String cpf,
        Date dataNascimento,
        List<TelefoneDTO> listaTelefone,
        List<EnderecoDTO> listaEndereco,
        @NotNull(message = "O campo Perfil não pode ser nulo.") Integer idPerfil) {

}