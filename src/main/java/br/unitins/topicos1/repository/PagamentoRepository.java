package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagamentoRepository implements PanacheRepository<Pagamento>{
    public List<Pagamento> findByTipo(String tipo) {
        return find("UPPER(tipo) LIKE UPPER(?1) ", "%"+tipo+"%").list();
    }
}