package br.com.locadora.clientes.facade;

import br.com.locadora.clientes.mapper.ClienteMapper;
import br.com.locadora.clientes.model.ClienteEntity;
import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.locadora.clientes.model.ClienteSaida;
import br.com.locadora.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteFacade {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteSaida salvar(ClienteEntrada clienteEntrada) throws Exception {

        if (clienteEntrada.getNome() == null || clienteEntrada.getIdade() == null || clienteEntrada.getNome().isEmpty() || clienteEntrada.getIdade() < 0){
            throw new Exception("Dado invalido para entrada");
        }

        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.converterEntradaParaEntity(clienteEntrada);

        clienteEntity = clienteRepository.save(clienteEntity);

        return ClienteMapper.INSTANCE.converterEntityParaSaida(clienteEntity);
    }

    public List<ClienteSaida> listarClientes() throws Exception {

        List<ClienteEntity> listaClienteEntity = clienteRepository.findAll();

        if (listaClienteEntity.isEmpty()){
            throw new Exception("Nao ha clientes na lista");
        }

        return ClienteMapper.INSTANCE.converterListEntityParaListSaida(listaClienteEntity);
    }

    public ClienteSaida alterarIdade(Long idCliente , ClienteEntrada entrada) throws Exception {

        ClienteEntity entidade;
        Optional<ClienteEntity> retornoBanco = clienteRepository.findById(idCliente);
        if (!retornoBanco.isPresent()){
            throw new Exception("Cliente nao encontrado");
        }

        entidade = retornoBanco.get();

        if (entrada.getIdade() == null || entrada.getIdade() < 0 || entidade.getIdade().equals(entrada.getIdade())){
            throw new Exception("Dado invalido para alterar idade");
        }

        entidade.setIdade(entrada.getIdade());

        entidade = clienteRepository.save(entidade);

        return ClienteMapper.INSTANCE.converterEntityParaSaida(entidade);
    }

    public ClienteSaida buscarCliente(Long idCliente) throws Exception {
        ClienteEntity entidade;

        Optional<ClienteEntity> retornoBanco = clienteRepository.findById(idCliente);
        if (!retornoBanco.isPresent()){
            throw new Exception("Cliente nao encontrado");
        }

        entidade = retornoBanco.get();

        return ClienteMapper.INSTANCE.converterEntityParaSaida(entidade);
    }

}
