package br.com.locadora.gateway.aluguel.model;

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
public class AluguelEntrada {

    List<Long> listaFilmes;
    Date dataAluguel;
}
