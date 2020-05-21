package br.com.locadora.filmes.filme.mapper;

import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import br.com.locadora.filmes.templates.FilmeEntityTemplate;
import br.com.locadora.filmes.templates.FilmeEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FilmeMapperTest {

    private FilmeMapper filmeMapper = Mappers.getMapper(FilmeMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.locadora.filmes.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_VALIDO);

        FilmeEntity filmeEntity = filmeMapper.converterEntradaParaEntity(filmeEntrada);

        Assert.assertNotNull(filmeEntity);
        Assert.assertEquals(filmeEntity.getNome(), filmeEntrada.getNome());
        Assert.assertEquals(filmeEntity.getGenero(), filmeEntrada.getGenero());
        Assert.assertEquals(filmeEntity.getDataLancamento(), filmeEntrada.getDataLancamento());
        Assert.assertEquals(filmeEntity.getClassificacaoIndicativa(), filmeEntrada.getClassificacaoIndicativa());
        Assert.assertEquals(filmeEntity.getValorFilme(), filmeEntrada.getValorFilme());
    }

    @Test
    public void deveConverterEntityParaSaida(){
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);

        FilmeSaida filmeSaida = filmeMapper.converterEntityParaSaida(filmeEntity);

        Assert.assertNotNull(filmeSaida);
        Assert.assertEquals(filmeSaida.getId(), filmeEntity.getId());
        Assert.assertEquals(filmeSaida.getNome(), filmeEntity.getNome());
        Assert.assertEquals(filmeSaida.getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(filmeSaida.getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(filmeSaida.getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(filmeSaida.getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(filmeSaida.getStatusAluguel(), filmeEntity.getStatusAluguel());
    }

    @Test
    public void deveConverterListEntityParaListSaida(){
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        List<FilmeEntity> listaFilmeEntity = new ArrayList<>();
        listaFilmeEntity.add(filmeEntity);

        List<FilmeSaida> listaFilmeSaida = filmeMapper.converterListEntityParaListSaida(listaFilmeEntity);

        Assert.assertNotNull(listaFilmeSaida);
        Assert.assertEquals(listaFilmeSaida.get(0).getId(), filmeEntity.getId());
        Assert.assertEquals(listaFilmeSaida.get(0).getNome(), filmeEntity.getNome());
        Assert.assertEquals(listaFilmeSaida.get(0).getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(listaFilmeSaida.get(0).getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(listaFilmeSaida.get(0).getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(listaFilmeSaida.get(0).getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(listaFilmeSaida.get(0).getStatusAluguel(), filmeEntity.getStatusAluguel());
    }
}
