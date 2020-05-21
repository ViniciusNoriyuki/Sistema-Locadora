package br.com.locadora.gateway.filme.model;

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
    Long id;
    String nome;
    String genero;
    Date dataLancamento;
    Integer classificacaoIndicativa;
    Double valorFilme;
    String statusAluguel;

}
