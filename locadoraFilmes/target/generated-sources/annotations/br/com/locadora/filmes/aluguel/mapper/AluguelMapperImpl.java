package br.com.locadora.filmes.aluguel.mapper;

import br.com.locadora.filmes.aluguel.model.AluguelEntity;
import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-13T11:35:16-0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_241 (Oracle Corporation)"
)
public class AluguelMapperImpl implements AluguelMapper {

    @Override
    public AluguelSaida converterEntityParaSaida(AluguelEntity aluguelEntity) {
        if ( aluguelEntity == null ) {
            return null;
        }

        AluguelSaida aluguelSaida = new AluguelSaida();

        aluguelSaida.setId( aluguelEntity.getId() );
        aluguelSaida.setIdCliente( aluguelEntity.getIdCliente() );
        aluguelSaida.setListaFilmes( listToSaida( aluguelEntity.getListaFilmes() ) );
        aluguelSaida.setDataAluguel( aluguelEntity.getDataAluguel() );
        aluguelSaida.setDataDevolucao( aluguelEntity.getDataDevolucao() );
        aluguelSaida.setStatusDevolucao( aluguelEntity.getStatusDevolucao() );
        aluguelSaida.setValorTotal( aluguelEntity.getValorTotal() );

        return aluguelSaida;
    }

    @Override
    public List<AluguelSaida> converterListEntityParaListSaida(List<AluguelEntity> listaAluguelEntity) {
        if ( listaAluguelEntity == null ) {
            return null;
        }

        List<AluguelSaida> list = new ArrayList<AluguelSaida>( listaAluguelEntity.size() );
        for ( AluguelEntity aluguelEntity : listaAluguelEntity ) {
            list.add( converterEntityParaSaida( aluguelEntity ) );
        }

        return list;
    }

    @Override
    public AluguelEntity mapToEntity(Long idCliente, List<FilmeEntity> listaFilmeEntity, Date dataAluguel, Date dataDevolucao, String statusDevolucao, Double valorTotal) {
        if ( idCliente == null && listaFilmeEntity == null && dataAluguel == null && dataDevolucao == null && statusDevolucao == null && valorTotal == null ) {
            return null;
        }

        AluguelEntity aluguelEntity = new AluguelEntity();

        if ( idCliente != null ) {
            aluguelEntity.setIdCliente( idCliente );
        }
        if ( listaFilmeEntity != null ) {
            List<FilmeEntity> list = listaFilmeEntity;
            if ( list != null ) {
                aluguelEntity.setListaFilmes( new ArrayList<FilmeEntity>( list ) );
            }
        }
        if ( dataAluguel != null ) {
            aluguelEntity.setDataAluguel( dataAluguel );
        }
        if ( dataDevolucao != null ) {
            aluguelEntity.setDataDevolucao( dataDevolucao );
        }
        if ( statusDevolucao != null ) {
            aluguelEntity.setStatusDevolucao( statusDevolucao );
        }
        if ( valorTotal != null ) {
            aluguelEntity.setValorTotal( valorTotal );
        }

        return aluguelEntity;
    }

    @Override
    public List<FilmeSaida> listToSaida(List<FilmeEntity> listaFilmes) {
        if ( listaFilmes == null ) {
            return null;
        }

        List<FilmeSaida> list = new ArrayList<FilmeSaida>( listaFilmes.size() );
        for ( FilmeEntity filmeEntity : listaFilmes ) {
            list.add( filmeEntityToFilmeSaida( filmeEntity ) );
        }

        return list;
    }

    protected FilmeSaida filmeEntityToFilmeSaida(FilmeEntity filmeEntity) {
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
}
