package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Administrador;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdministradorRepository implements PanacheRepository<Administrador>{
    public List<Administrador> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }
}
