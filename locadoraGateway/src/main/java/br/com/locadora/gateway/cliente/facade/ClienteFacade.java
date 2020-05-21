package br.com.locadora.gateway.cliente.facade;

import br.com.locadora.gateway.cliente.feign.client.ClienteClient;
import br.com.locadora.gateway.cliente.model.ClienteEntrada;
import br.com.locadora.gateway.cliente.model.ClienteSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteFacade {

    @Autowired
    ClienteClient clienteClient;

    public ClienteSaida salvar(ClienteEntrada clienteEntrada) throws Exception {
        return clienteClient.salvar(clienteEntrada);
    }

    public List<ClienteSaida> listarClientes() throws Exception{
        return clienteClient.listarClientes();
    };

    public ClienteSaida alterarIdade(Long idCliente, ClienteEntrada clienteEntrada) throws Exception{
        return clienteClient.alterarIdade(idCliente, clienteEntrada);
    };

    public ClienteSaida buscarCliente(Long idCliente) throws Exception{
        return clienteClient.buscarCliente(idCliente);
    };
}
