package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.model.Administrador;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.repository.TelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {

    @Inject
    TelefoneRepository repository;

    @Override
    @Transactional
    public TelefoneResponseDTO insert(TelefoneDTO dto) {
        Telefone novoTelefone = new Telefone();
        novoTelefone.setCodArea(dto.codigoArea());
        novoTelefone.setNumero(dto.numero());

        repository.persist(novoTelefone);

        return TelefoneResponseDTO.valueOf(novoTelefone);
    }

    @Override
    @Transactional
    public TelefoneResponseDTO update(TelefoneDTO dto, Long id) {

        Telefone novoTelefone = repository.findById(id);

        novoTelefone.setCodArea(dto.codigoArea());
        novoTelefone.setNumero(dto.numero());

        return TelefoneResponseDTO.valueOf(novoTelefone);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public TelefoneResponseDTO findById(Long id) {
        Telefone tell = repository.findById(id);
        if (tell == null) {
            throw new EntityNotFoundException("Telefone não encontrado com ID: " + id);
        }
        return TelefoneResponseDTO.valueOf(tell);
    }

    @Override
    public List<TelefoneResponseDTO> findByNumero(String numero) {
        return repository.findByNumero(numero).stream()
                .map(e -> TelefoneResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<TelefoneResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> TelefoneResponseDTO.valueOf(e)).toList();
    }

}
