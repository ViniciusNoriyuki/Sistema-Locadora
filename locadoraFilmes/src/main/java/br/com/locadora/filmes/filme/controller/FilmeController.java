package br.com.locadora.filmes.filme.controller;

import br.com.locadora.filmes.filme.facade.FilmeFacade;
import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "locadora/filme", produces = "application/json")
@Configuration
@CrossOrigin
public class FilmeController {

    @Autowired
    FilmeFacade filmeFacade;

    @PostMapping("/salvar")
    public FilmeSaida salvarFilme(@RequestBody FilmeEntrada filmeEntrada) throws Exception {
        return filmeFacade.salvarFilme(filmeEntrada);
    }

    @GetMapping("/listarFilmes")
    public List<FilmeSaida> listarFilmes() throws Exception {
        return filmeFacade.listarFilmes();
    }

    @PutMapping("/{idFilme}/alterarValor")
    public FilmeSaida alterarValorFilme(@PathVariable Long idFilme, @RequestBody FilmeEntrada filmeEntrada) throws Exception {
        return filmeFacade.alterarValorFilme(idFilme, filmeEntrada);
    }

    @GetMapping("/{idFilme}/buscarFilme")
    public FilmeSaida buscarFilme(@PathVariable Long idFilme) throws Exception {
        return filmeFacade.buscarFilme(idFilme);
    }
}
