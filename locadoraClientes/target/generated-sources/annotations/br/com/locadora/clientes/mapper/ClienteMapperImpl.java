package br.com.locadora.clientes.mapper;

import br.com.locadora.clientes.model.ClienteEntity;
import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.locadora.clientes.model.ClienteSaida;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-13T14:20:58-0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteEntity converterEntradaParaEntity(ClienteEntrada clienteEntrada) {
        if ( clienteEntrada == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome( clienteEntrada.getNome() );
        clienteEntity.setIdade( clienteEntrada.getIdade() );

        return clienteEntity;
    }

    @Override
    public ClienteSaida converterEntityParaSaida(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }

        ClienteSaida clienteSaida = new ClienteSaida();

        clienteSaida.setId( clienteEntity.getId() );
        clienteSaida.setNome( clienteEntity.getNome() );
        clienteSaida.setIdade( clienteEntity.getIdade() );

        return clienteSaida;
    }

    @Override
    public List<ClienteSaida> converterListEntityParaListSaida(List<ClienteEntity> listaClienteEntity) {
        if ( listaClienteEntity == null ) {
            return null;
        }

        List<ClienteSaida> list = new ArrayList<ClienteSaida>( listaClienteEntity.size() );
        for ( ClienteEntity clienteEntity : listaClienteEntity ) {
            list.add( converterEntityParaSaida( clienteEntity ) );
        }

        return list;
    }
}
