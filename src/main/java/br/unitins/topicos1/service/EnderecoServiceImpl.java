package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Override
    @Transactional
    public EnderecoResponseDTO insert(EnderecoDTO dto) {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep(dto.cep());
        novoEndereco.setBairro(dto.bairro());
        novoEndereco.setLogradouro(dto.logradouro());
        novoEndereco.setNumero(dto.numero());
        novoEndereco.setComplemento(dto.complemento());
        if (novoEndereco.getCidade() != null) {
            novoEndereco.getCidade().setId(dto.idCidade());
        }
        

        repository.persist(novoEndereco);
        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(EnderecoDTO dto, Long id) {
        Endereco updEndereco = repository.findById(id);

        updEndereco.setCep(dto.cep());
        updEndereco.setBairro(dto.bairro());
        updEndereco.setLogradouro(dto.logradouro());
        updEndereco.setNumero(dto.numero());
        updEndereco.setComplemento(dto.complemento());
        updEndereco.getCidade().setId(dto.idCidade());

        repository.persist(updEndereco);
        return EnderecoResponseDTO.valueOf(updEndereco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        Endereco end = repository.findById(id);
        if (end == null) {
            throw new EntityNotFoundException("Endereco não encontrado com ID: " + id);
        }
        return EnderecoResponseDTO.valueOf(end);
    }

    @Override
    public List<EnderecoResponseDTO> findByCep(String cep) {
        return repository.findByCep(cep).stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EnderecoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

}
