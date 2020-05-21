package br.com.locadora.gateway.aluguel.controller;

import br.com.locadora.gateway.aluguel.facade.AluguelFacade;
import br.com.locadora.gateway.aluguel.model.AluguelEntrada;
import br.com.locadora.gateway.aluguel.model.AluguelSaida;
import br.com.locadora.gateway.filme.model.FilmeSaida;
import br.com.locadora.gateway.model.FilmeAluguelSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "locadora/gateway/aluguel", produces = "application/json")
@Configuration
@CrossOrigin
public class AluguelController {

    @Autowired
    AluguelFacade aluguelFacade;

    @PostMapping("/{idCliente}/alugar")
    public FilmeAluguelSaida alugarFilme(@PathVariable Long idCliente, @RequestBody AluguelEntrada aluguelEntrada) throws Exception {
        return aluguelFacade.alugarFilme(idCliente, aluguelEntrada);
    }

    @GetMapping("/extratoAluguelPorPeriodo")
    public List<FilmeAluguelSaida> extratoAluguelPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateFrom,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateTo) throws Exception {
        return aluguelFacade.extratoAluguelPorPeriodo(dateFrom, dateTo);
    }

    @PutMapping("/{idAluguel}/{idCliente}/devolver")
    public FilmeAluguelSaida devolverFilme(@PathVariable Long idAluguel, @PathVariable Long idCliente) throws Exception {
        return aluguelFacade.devolverFilme(idAluguel, idCliente);
    }

    @GetMapping("/relatorio")
    public List<FilmeAluguelSaida> relatorioAlugueisVencidosENaoDevolvidos(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateFrom) throws Exception {
        return aluguelFacade.relatorioAlugueisVencidosENaoDevolvidos(dateFrom);
    }

    @GetMapping("/listarTodosAlugueis")
    public List<FilmeAluguelSaida> listarTodosAlugueis() throws Exception {
        return aluguelFacade.listarTodosAlugueis();
    }

    @GetMapping("/{idAluguel}/buscarAluguel")
    public FilmeAluguelSaida buscarAluguel(@PathVariable Long idAluguel) throws Exception {
        return aluguelFacade.buscarAluguel(idAluguel);
    }
}