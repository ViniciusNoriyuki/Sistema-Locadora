package br.com.locadora.gateway.aluguel.facade;

import br.com.locadora.gateway.aluguel.feign.client.AluguelClient;
import br.com.locadora.gateway.aluguel.model.AluguelEntrada;
import br.com.locadora.gateway.aluguel.model.AluguelSaida;
import br.com.locadora.gateway.cliente.facade.ClienteFacade;
import br.com.locadora.gateway.cliente.model.ClienteSaida;
import br.com.locadora.gateway.filme.facade.FilmeFacade;
import br.com.locadora.gateway.filme.model.FilmeSaida;
import br.com.locadora.gateway.model.FilmeAluguelSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AluguelFacade {

    @Autowired
    AluguelClient aluguelClient;

    @Autowired
    ClienteFacade clienteFacade;

    @Autowired
    FilmeFacade filmeFacade;

    public FilmeAluguelSaida alugarFilme(Long idCliente, AluguelEntrada aluguelEntrada) throws Exception{

        ClienteSaida clienteSaida = clienteFacade.buscarCliente(idCliente);

        for (Long idFilme : aluguelEntrada.getListaFilmes()){
            FilmeSaida filmeSaida = filmeFacade.buscarFilme(idFilme);
            if (clienteSaida.getIdade() < filmeSaida.getClassificacaoIndicativa()){
                throw new Exception("Filme nao permitido para a idade do cliente");
            }
        }

        AluguelSaida aluguelSaida = aluguelClient.alugarFilme(idCliente, aluguelEntrada);

        return setFilmeAluguelSaida(clienteSaida, aluguelSaida);
    };

    public List<FilmeAluguelSaida> extratoAluguelPorPeriodo(Date dateFrom, Date dateTo) throws Exception{

        List<AluguelSaida> listaAluguelSaida = aluguelClient.extratoAluguelPorPeriodo(dateFrom, dateTo);

        return atribuirClienteEAluguel(listaAluguelSaida);
    };

    public FilmeAluguelSaida devolverFilme(Long idAluguel, Long idCliente) throws Exception{

        ClienteSaida clienteSaida = clienteFacade.buscarCliente(idCliente);
        aluguelClient.buscarAluguel(idAluguel);

        AluguelSaida aluguelSaida = aluguelClient.devolverFilme(idAluguel, idCliente);

        return setFilmeAluguelSaida(clienteSaida, aluguelSaida);
    };

    public List<FilmeAluguelSaida> relatorioAlugueisVencidosENaoDevolvidos(Date dateFrom) throws Exception{

        List<AluguelSaida> listaAluguelSaida = aluguelClient.relatorioAlugueisVencidosENaoDevolvidos(dateFrom);

        return atribuirClienteEAluguel(listaAluguelSaida);
    };

    public List<FilmeAluguelSaida> listarTodosAlugueis() throws Exception{

        List<AluguelSaida> listaAluguelSaida = aluguelClient.listarTodosAlugueis();

        return atribuirClienteEAluguel(listaAluguelSaida);
    };

    public FilmeAluguelSaida buscarAluguel(Long idAluguel) throws Exception {

        AluguelSaida aluguelSaida = aluguelClient.buscarAluguel(idAluguel);
        ClienteSaida clienteSaida = clienteFacade.buscarCliente(aluguelSaida.getIdCliente());

        return setFilmeAluguelSaida(clienteSaida, aluguelSaida);
    }

    public List<FilmeAluguelSaida> atribuirClienteEAluguel(List<AluguelSaida> listaAluguelSaida) throws Exception {

        List<FilmeAluguelSaida> listaFilmeAluguelSaida = new ArrayList<>();

        for (AluguelSaida aluguelSaida : listaAluguelSaida){
            ClienteSaida clienteSaida = clienteFacade.buscarCliente(aluguelSaida.getIdCliente());

            FilmeAluguelSaida filmeAluguelSaida = setFilmeAluguelSaida(clienteSaida, aluguelSaida);

            listaFilmeAluguelSaida.add(filmeAluguelSaida);
        }
        return listaFilmeAluguelSaida;
    }

    public FilmeAluguelSaida setFilmeAluguelSaida(ClienteSaida clienteSaida, AluguelSaida aluguelSaida){

        FilmeAluguelSaida filmeAluguelSaida = new FilmeAluguelSaida();
        filmeAluguelSaida.setCliente(clienteSaida);
        filmeAluguelSaida.setAluguel(aluguelSaida);

        return filmeAluguelSaida;
    }
}
