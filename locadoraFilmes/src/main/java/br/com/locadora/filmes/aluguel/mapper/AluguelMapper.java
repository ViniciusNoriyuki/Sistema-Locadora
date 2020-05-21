package br.com.locadora.filmes.aluguel.mapper;

import br.com.locadora.filmes.aluguel.model.AluguelEntity;
import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

@Mapper
public interface AluguelMapper {

    AluguelMapper INSTANCE = Mappers.getMapper(AluguelMapper.class);

    AluguelSaida converterEntityParaSaida(AluguelEntity aluguelEntity);

    List<AluguelSaida> converterListEntityParaListSaida(List<AluguelEntity> listaAluguelEntity);

    @Mappings({
            @Mapping(source = "idCliente", target = "idCliente"),
            @Mapping(source = "listaFilmeEntity", target = "listaFilmes"),
            @Mapping(source = "dataAluguel", target = "dataAluguel"),
            @Mapping(source = "statusDevolucao", target = "statusDevolucao"),
            @Mapping(source = "valorTotal", target = "valorTotal"),
    })
    AluguelEntity mapToEntity(Long idCliente, List<FilmeEntity> listaFilmeEntity, Date dataAluguel, Date dataDevolucao, String statusDevolucao, Double valorTotal);

    List<FilmeSaida> listToSaida(List<FilmeEntity> listaFilmes);
}
