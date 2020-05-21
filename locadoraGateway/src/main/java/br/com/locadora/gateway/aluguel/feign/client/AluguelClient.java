package br.com.locadora.gateway.aluguel.feign.client;

import br.com.locadora.gateway.aluguel.model.AluguelEntrada;
import br.com.locadora.gateway.aluguel.model.AluguelSaida;
import br.com.locadora.gateway.filme.model.FilmeSaida;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@FeignClient(name = "aluguel", url = "http://localhost:8082/locadora/aluguel")
public interface AluguelClient {

    @PostMapping("/{idCliente}/alugar")
    public AluguelSaida alugarFilme(@PathVariable Long idCliente, @RequestBody AluguelEntrada aluguelEntrada) throws Exception;

    @GetMapping("/extratoAluguelPorPeriodo")
    public List<AluguelSaida> extratoAluguelPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateFrom,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateTo) throws Exception;

    @PutMapping("/{idAluguel}/{idCliente}/devolver")
    public AluguelSaida devolverFilme(@PathVariable Long idAluguel, @PathVariable Long idCliente) throws Exception;

    @GetMapping("/relatorio")
    public List<AluguelSaida> relatorioAlugueisVencidosENaoDevolvidos(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd") Date dateFrom) throws Exception;

    @GetMapping("/listarTodosAlugueis")
    public List<AluguelSaida> listarTodosAlugueis() throws Exception;

    @GetMapping("/{idAluguel}/buscarAluguel")
    public AluguelSaida buscarAluguel(@PathVariable Long idAluguel) throws Exception;
}
