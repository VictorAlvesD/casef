package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String numero,
        String complemento,
        String cep,
        Integer estado) {
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                endereco.getEstado().getId());
    }
}