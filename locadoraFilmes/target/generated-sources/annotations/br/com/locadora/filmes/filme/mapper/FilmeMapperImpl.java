package br.com.locadora.filmes.filme.mapper;

import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-13T11:35:16-0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class FilmeMapperImpl implements FilmeMapper {

    @Override
    public FilmeEntity converterEntradaParaEntity(FilmeEntrada filmeEntrada) {
        if ( filmeEntrada == null ) {
            return null;
        }

        FilmeEntity filmeEntity = new FilmeEntity();

        filmeEntity.setNome( filmeEntrada.getNome() );
        filmeEntity.setGenero( filmeEntrada.getGenero() );
        filmeEntity.setDataLancamento( filmeEntrada.getDataLancamento() );
        filmeEntity.setClassificacaoIndicativa( filmeEntrada.getClassificacaoIndicativa() );
        filmeEntity.setValorFilme( filmeEntrada.getValorFilme() );

        return filmeEntity;
    }

    @Override
    public FilmeSaida converterEntityParaSaida(FilmeEntity filmeEntity) {
        if ( filmeEntity == null ) {
            return null;
        }

        FilmeSaida filmeSaida = new FilmeSaida();

        filmeSaida.setId( filmeEntity.getId() );
        filmeSaida.setNome( filmeEntity.getNome() );
        filmeSaida.setGenero( filmeEntity.getGenero() );
        filmeSaida.setDataLancamento( filmeEntity.getDataLancamento() );
        filmeSaida.setClassificacaoIndicativa( filmeEntity.getClassificacaoIndicativa() );
        filmeSaida.setValorFilme( filmeEntity.getValorFilme() );
        filmeSaida.setStatusAluguel( filmeEntity.getStatusAluguel() );

        return filmeSaida;
    }

    @Override
    public List<FilmeSaida> converterListEntityParaListSaida(List<FilmeEntity> listaFilmeEntity) {
        if ( listaFilmeEntity == null ) {
            return null;
        }

        List<FilmeSaida> list = new ArrayList<FilmeSaida>( listaFilmeEntity.size() );
        for ( FilmeEntity filmeEntity : listaFilmeEntity ) {
            list.add( converterEntityParaSaida( filmeEntity ) );
        }

        return list;
    }
}
