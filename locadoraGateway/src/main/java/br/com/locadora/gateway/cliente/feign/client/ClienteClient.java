package br.com.locadora.gateway.cliente.feign.client;

import br.com.locadora.gateway.cliente.model.ClienteEntrada;
import br.com.locadora.gateway.cliente.model.ClienteSaida;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cliente", url = "http://localhost:8080/locadora/cliente")
public interface ClienteClient {

    @PostMapping("/salvar")
    public ClienteSaida salvar(@RequestBody ClienteEntrada clienteEntrada) throws Exception;

    @GetMapping("/listarClientes")
    public List<ClienteSaida> listarClientes() throws Exception;

    @PutMapping("/{idCliente}/alterarIdade")
    public ClienteSaida alterarIdade(@PathVariable Long idCliente, @RequestBody ClienteEntrada clienteEntrada) throws Exception;

    @GetMapping("/{idCliente}/buscarCliente")
    public ClienteSaida buscarCliente(@PathVariable Long idCliente) throws Exception;
}
