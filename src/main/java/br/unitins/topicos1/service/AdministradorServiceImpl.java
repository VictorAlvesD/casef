package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.AdministradorDTO;
import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.model.Administrador;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.repository.AdministradorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AdministradorServiceImpl implements AdministradorService {

    @Inject
    AdministradorRepository repository;

    @Override
    @Transactional
    public AdministradorResponseDTO insert(AdministradorDTO dto) {
        Administrador novoAdministrador = new Administrador();
        novoAdministrador.setNome(dto.nome());
        novoAdministrador.setCpf(dto.cpf());
        novoAdministrador.setEmail(dto.email());
        novoAdministrador.setSenha(dto.senha());
        novoAdministrador.setMatricula(dto.matricula());
        novoAdministrador.setPerfil(Perfil.valueOf(dto.idPerfil()));

        repository.persist(novoAdministrador);

        return AdministradorResponseDTO.valueOf(novoAdministrador);
    }

    @Override
    @Transactional
    public AdministradorResponseDTO update(AdministradorDTO dto, Long id) {

        Administrador admExistente = repository.findById(id);

        admExistente.setNome(dto.nome());
        admExistente.setCpf(dto.cpf());
        admExistente.setEmail(dto.email());
        admExistente.setSenha(dto.senha());
        admExistente.setMatricula(dto.matricula());
        admExistente.setPerfil(Perfil.valueOf(dto.idPerfil()));

        return AdministradorResponseDTO.valueOf(admExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public AdministradorResponseDTO findById(Long id) {
        Administrador administrador = repository.findById(id);
        if (administrador == null) {
            throw new EntityNotFoundException("Administrador não encontrado com ID: " + id);
        }
        return AdministradorResponseDTO.valueOf(administrador);
    }

    @Override
    public List<AdministradorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AdministradorResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> AdministradorResponseDTO.valueOf(e)).toList();
    }

}
