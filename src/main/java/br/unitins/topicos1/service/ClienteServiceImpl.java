package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.model.Administrador;
import br.unitins.topicos1.model.Cliente;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository repository;

    @Override
    @Transactional
    public ClienteResponseDTO insert(ClienteDTO dto) throws Exception {
        if (repository.findByEmail(dto.email()) != null) {
            throw new Exception("Login já existe.");
        }
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(dto.nome());
        novoCliente.setCpf(dto.cpf());
        novoCliente.setEmail(dto.email());
        novoCliente.setSenha(dto.senha());
        novoCliente.setDataNascimento(dto.dataNascimento());

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            novoCliente.setTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                novoCliente.getTelefone().add(telefone);
            }
        }

        if (dto.listaEndereco() != null && !dto.listaEndereco().isEmpty()) {
            novoCliente.setEndereco(new ArrayList<Endereco>());
            for (EnderecoDTO end : dto.listaEndereco()) {
                Endereco endereco = new Endereco();
                endereco.setCep(end.cep());
                ;
                endereco.setBairro(end.bairro());
                endereco.setCidade(end.cidade());
                endereco.setNumero(end.numero());
                endereco.setLogradouro(end.logradouro());
                endereco.setComplemento(end.complemento());

                Estado estado = Estado.valueOf(end.estado());
                endereco.setEstado(estado);

                novoCliente.getEndereco().add(endereco);
            }
        }

        repository.persist(novoCliente);

        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {

        Cliente clienteExistente = repository.findById(id);

        // Atualize os campos do cliente com base no DTO
        clienteExistente.setNome(dto.nome());
        clienteExistente.setCpf(dto.cpf());
        clienteExistente.setEmail(dto.email());
        clienteExistente.setSenha(dto.senha());
        clienteExistente.setDataNascimento(dto.dataNascimento());

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            clienteExistente.getTelefone().clear();
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                clienteExistente.getTelefone().add(telefone);
            }
        }

        if (dto.listaEndereco() != null && !dto.listaEndereco().isEmpty()) {
            clienteExistente.getEndereco().clear();
            for (EnderecoDTO end : dto.listaEndereco()) {
                Endereco endereco = new Endereco();
                endereco.setCep(end.cep());
                endereco.setBairro(end.bairro());
                endereco.setCidade(end.cidade());
                endereco.setNumero(end.numero());
                endereco.setLogradouro(end.logradouro());
                endereco.setComplemento(end.complemento());
                
                Estado estado = Estado.valueOf(end.estado());
                endereco.setEstado(estado);

                clienteExistente.getEndereco().add(endereco);
            }
        }

        return ClienteResponseDTO.valueOf(clienteExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = repository.findById(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Administrador não encontrado com ID: " + id);
        }
        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

}
