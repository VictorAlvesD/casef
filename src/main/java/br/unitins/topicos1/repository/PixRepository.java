package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pix;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PixRepository implements PanacheRepository<Pix>{
    public List<Pix> findByChave(String chavePix) {
        return find("UPPER(chavePix) LIKE UPPER(?1) ", "%"+ chavePix +"%").list();
    }
}
