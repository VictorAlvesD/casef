package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        @NotBlank(message = "O campo logradouro não pode ser nulo") String logradouro,
        @NotBlank(message = "O campo numero não pode ser nulo") String numero,
        String complemento,
        @NotBlank(message = "O campo bairro não pode ser nulo") String bairro,
        @NotBlank(message = "O campo CEP não pode ser nulo.") @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "CEP inválido use esse modelo: 00000-000") String cep,
        @NotNull(message = "O campo cidade não pode ser nulo") Long idCidade
         ){
    }

