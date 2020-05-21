package br.com.locadora.filmes.aluguel.model;

import br.com.locadora.filmes.filme.model.FilmeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "FilmeAluguel")
public class AluguelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "idCliente")
    Long idCliente;

    @ManyToMany
    List<FilmeEntity> listaFilmes;

    @Column(name = "dataAluguel")
    Date dataAluguel;

    @Column(name = "dataDevolucao")
    Date dataDevolucao;

    @Column(name = "statusDevolucao")
    String statusDevolucao;

    @Column(name = "valorTotal")
    Double valorTotal;
}
