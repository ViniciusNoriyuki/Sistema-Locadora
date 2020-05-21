package br.com.locadora.gateway.aluguel.model;

import br.com.locadora.gateway.filme.model.FilmeSaida;
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

    Long id;
    Long idCliente;
    List<FilmeSaida> listaFilmes;
    Date dataAluguel;
    Date dataDevolucao;
    String statusDevolucao;
    Double valorTotal;
}
