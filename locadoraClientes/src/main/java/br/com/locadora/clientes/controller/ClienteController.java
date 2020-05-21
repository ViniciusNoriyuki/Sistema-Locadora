package br.com.locadora.clientes.controller;
import br.com.locadora.clientes.facade.ClienteFacade;
import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.locadora.clientes.model.ClienteSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "locadora/cliente", produces = "application/json")
@Configuration
@CrossOrigin
public class ClienteController {

    @Autowired
    ClienteFacade clienteFacade;

    @PostMapping("/salvar")
    public ClienteSaida salvar(@RequestBody ClienteEntrada clienteEntrada) throws Exception {
        return clienteFacade.salvar(clienteEntrada);
    }

    @GetMapping("/listarClientes")
    public List<ClienteSaida> listarClientes() throws Exception {
        return clienteFacade.listarClientes();
    }

    @PutMapping("/{idCliente}/alterarIdade")
    public ClienteSaida alterarIdade(@PathVariable Long idCliente, @RequestBody ClienteEntrada clienteEntrada) throws Exception {

        return clienteFacade.alterarIdade(idCliente, clienteEntrada);
    }

    @GetMapping("/{idCliente}/buscarCliente")
    public ClienteSaida buscarCliente(@PathVariable Long idCliente) throws Exception {
        return clienteFacade.buscarCliente(idCliente);
    }

}