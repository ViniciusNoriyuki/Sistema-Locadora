package br.com.locadora.filmes.aluguel.repository;

import br.com.locadora.filmes.aluguel.model.AluguelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<AluguelEntity, Long> {
}
