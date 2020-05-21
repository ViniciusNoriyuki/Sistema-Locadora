package br.com.locadora.filmes.filme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeSaida {

    private Long id;
    private String nome;
    private String genero;
    private Date dataLancamento;
    private Integer classificacaoIndicativa;
    private Double valorFilme;
    private String statusAluguel;
}
