package br.com.locadora.filmes.filme.repository;

import br.com.locadora.filmes.filme.model.FilmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
}
