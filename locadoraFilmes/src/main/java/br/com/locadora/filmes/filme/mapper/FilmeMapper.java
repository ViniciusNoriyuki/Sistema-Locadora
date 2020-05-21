package br.com.locadora.filmes.filme.mapper;

import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmeMapper {

    FilmeMapper INSTANCE = Mappers.getMapper(FilmeMapper.class);

    FilmeEntity converterEntradaParaEntity(FilmeEntrada filmeEntrada);
    FilmeSaida converterEntityParaSaida(FilmeEntity filmeEntity);

    List<FilmeSaida> converterListEntityParaListSaida(List<FilmeEntity> listaFilmeEntity);
}
