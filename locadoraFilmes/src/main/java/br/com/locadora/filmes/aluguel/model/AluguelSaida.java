package br.com.locadora.filmes.aluguel.model;

import br.com.locadora.filmes.filme.model.FilmeSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AluguelSaida {

    private Long id;
    private Long idCliente;
    private List<FilmeSaida> listaFilmes;
    private Date dataAluguel;
    private Date dataDevolucao;
    private String statusDevolucao;
    private Double valorTotal;
}
