package br.com.locadora.gateway.model;

import br.com.locadora.gateway.aluguel.model.AluguelSaida;
import br.com.locadora.gateway.cliente.model.ClienteSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeAluguelSaida {
    ClienteSaida cliente;
    AluguelSaida aluguel;

}