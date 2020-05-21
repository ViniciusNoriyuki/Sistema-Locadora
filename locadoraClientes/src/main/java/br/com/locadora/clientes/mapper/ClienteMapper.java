package br.com.locadora.clientes.mapper;
import br.com.locadora.clientes.model.ClienteEntity;
import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.locadora.clientes.model.ClienteSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteEntity converterEntradaParaEntity(ClienteEntrada clienteEntrada);
    ClienteSaida converterEntityParaSaida(ClienteEntity clienteEntity);

    List<ClienteSaida> converterListEntityParaListSaida(List<ClienteEntity> listaClienteEntity);
}