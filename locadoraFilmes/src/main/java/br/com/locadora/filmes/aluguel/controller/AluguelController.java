package br.com.locadora.filmes.aluguel.controller;

import br.com.locadora.filmes.aluguel.facade.AluguelFacade;
import br.com.locadora.filmes.aluguel.model.AluguelEntrada;
import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.filme.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "locadora/aluguel", produces = "application/json")
@Configuration
@CrossOrigin
public class AluguelController {

    @Autowired
    AluguelFacade aluguelFacade;

    @PostMapping("/{idCliente}/alugar")
    public AluguelSaida alugarFilme(@PathVariable Long idCliente, @RequestBody AluguelEntrada aluguelEntrada) throws Exception {
        return aluguelFacade.alugarFilme(idCliente, aluguelEntrada);
    }

    @GetMapping("/extratoAluguelPorPeriodo")
    public List<AluguelSaida> extratoAluguelPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateFrom,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateTo) throws Exception {
        return aluguelFacade.extratoAluguelPorPeriodo(dateFrom, dateTo);
    }

    @PutMapping("/{idAluguel}/{idCliente}/devolver")
    public AluguelSaida devolverFilme(@PathVariable Long idAluguel, @PathVariable Long idCliente) throws Exception {
        return aluguelFacade.devolverFilme(idAluguel, idCliente);
    }

    @GetMapping("/relatorio")
    public List<AluguelSaida> relatorioAlugueisVencidosENaoDevolvidos(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateFrom) throws Exception {
        return aluguelFacade.relatorioAlugueisVencidosENaoDevolvidos(dateFrom);
    }

    @GetMapping("/listarTodosAlugueis")
    public List<AluguelSaida> listarTodosAlugueis() throws Exception {
        return aluguelFacade.listarTodosAlugueis();
    }

    @GetMapping("/{idAluguel}/buscarAluguel")
    public AluguelSaida buscarAluguel(@PathVariable Long idAluguel) throws Exception {
        return aluguelFacade.buscarAluguel(idAluguel);
    }
}
