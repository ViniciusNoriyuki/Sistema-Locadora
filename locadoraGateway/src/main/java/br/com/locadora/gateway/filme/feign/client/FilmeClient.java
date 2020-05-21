package br.com.locadora.gateway.filme.feign.client;

import br.com.locadora.gateway.filme.model.FilmeEntrada;
import br.com.locadora.gateway.filme.model.FilmeSaida;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "filme", url = "http://localhost:8082/locadora/filme")
public interface FilmeClient {

    @PostMapping("/salvar")
    public FilmeSaida salvarFilme(@RequestBody FilmeEntrada filmeEntrada) throws Exception;

    @GetMapping("/listarFilmes")
    public List<FilmeSaida> listarFilmes() throws Exception;

    @PutMapping("/{idFilme}/alterarValor")
    public FilmeSaida alterarValorFilme(@PathVariable Long idFilme, @RequestBody FilmeEntrada filmeEntrada) throws Exception;

    @GetMapping("/{idFilme}/buscarFilme")
    public FilmeSaida buscarFilme(@PathVariable Long idFilme) throws Exception;
}