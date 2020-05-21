package br.com.locadora.gateway.filme.facade;

import br.com.locadora.gateway.filme.feign.client.FilmeClient;
import br.com.locadora.gateway.filme.model.FilmeEntrada;
import br.com.locadora.gateway.filme.model.FilmeSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FilmeFacade {

    @Autowired
    FilmeClient filmeClient;

    public FilmeSaida salvarFilme(FilmeEntrada filmeEntrada) throws Exception {
        return filmeClient.salvarFilme(filmeEntrada);
    }

    public List<FilmeSaida> listarFilmes() throws Exception {
        return filmeClient.listarFilmes();
    }

    public FilmeSaida alterarValorFilme (Long idFilme, FilmeEntrada filmeEntrada) throws Exception{
        return filmeClient.alterarValorFilme(idFilme, filmeEntrada);
    }

    public FilmeSaida buscarFilme(@PathVariable Long idFilme) throws Exception{
        return filmeClient.buscarFilme(idFilme);
    }

}