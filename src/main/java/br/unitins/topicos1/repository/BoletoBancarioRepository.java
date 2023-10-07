package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.BoletoBancario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoletoBancarioRepository implements PanacheRepository<BoletoBancario>{
    public List<BoletoBancario> findByNumeroBoleto(String numeroBoleto) {
        return find("UPPER(numeroBoleto) LIKE UPPER(?1) ", "%"+numeroBoleto+"%").list();
    }

}