package br.com.locadora.filmes.filme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Filme")
public class FilmeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome")
    String nome;

    @Column(name = "genero")
    String genero;

    @Column(name = "dataLancamento")
    Date dataLancamento;

    @Column(name = "classificacaoIndicativa")
    Integer classificacaoIndicativa;

    @Column(name = "valorFilme")
    Double valorFilme;

    @Column(name = "statusAluguel")
    String statusAluguel;
}
