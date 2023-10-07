package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.CartaoCredito;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartaoCreditoRepository implements PanacheRepository<CartaoCredito>{
    public List<CartaoCredito> findByBandeira(String bandeira) {
        return find("UPPER(bandeira) LIKE UPPER(?1) ", "%"+ bandeira +"%").list();
    }
}