package br.com.locadora.filmes.aluguel.mapper;

import br.com.locadora.filmes.aluguel.model.AluguelEntity;
import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import br.com.locadora.filmes.templates.AluguelEntityTemplate;
import br.com.locadora.filmes.templates.FilmeEntityTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AluguelMapperTest {

    private AluguelMapper aluguelMapper = Mappers.getMapper(AluguelMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.locadora.filmes.templates");
    }

    @Test
    public void deveConverterEntityParaSaida(){
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);

        AluguelSaida saida = aluguelMapper.converterEntityParaSaida(aluguelEntity);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), aluguelEntity.getId());
        Assert.assertEquals(saida.getIdCliente(), aluguelEntity.getIdCliente());
        Assert.assertEquals(saida.getListaFilmes().get(0).getId(), aluguelEntity.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.getListaFilmes().get(0).getNome(), aluguelEntity.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.getListaFilmes().get(0).getGenero(), aluguelEntity.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.getListaFilmes().get(0).getDataLancamento(), aluguelEntity.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelEntity.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.getListaFilmes().get(0).getValorFilme(), aluguelEntity.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.getListaFilmes().get(0).getStatusAluguel(), aluguelEntity.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.getDataAluguel(), aluguelEntity.getDataAluguel());
        Assert.assertEquals(saida.getDataDevolucao(), aluguelEntity.getDataDevolucao());
        Assert.assertEquals(saida.getStatusDevolucao(), aluguelEntity.getStatusDevolucao());
        Assert.assertEquals(saida.getValorTotal(), aluguelEntity.getValorTotal());
    }

    @Test
    public void deveConverterListEntityParaListSaida(){
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);

        List<AluguelSaida> saida = aluguelMapper.converterListEntityParaListSaida(listaAluguelEntity);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), aluguelEntity.getId());
        Assert.assertEquals(saida.get(0).getIdCliente(), aluguelEntity.getIdCliente());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getId(), aluguelEntity.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getNome(), aluguelEntity.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getGenero(), aluguelEntity.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getDataLancamento(), aluguelEntity.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelEntity.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getValorFilme(), aluguelEntity.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getStatusAluguel(), aluguelEntity.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.get(0).getDataAluguel(), aluguelEntity.getDataAluguel());
        Assert.assertEquals(saida.get(0).getDataDevolucao(), aluguelEntity.getDataDevolucao());
        Assert.assertEquals(saida.get(0).getStatusDevolucao(), aluguelEntity.getStatusDevolucao());
        Assert.assertEquals(saida.get(0).getValorTotal(), aluguelEntity.getValorTotal());
    }

    @Test
    public void deveListToSaida(){
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        List<FilmeEntity> listaFilmes = new ArrayList<>();
        listaFilmes.add(filmeEntity);

        List<FilmeSaida> saida = aluguelMapper.listToSaida(listaFilmes);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), filmeEntity.getId());
        Assert.assertEquals(saida.get(0).getNome(), filmeEntity.getNome());
        Assert.assertEquals(saida.get(0).getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(saida.get(0).getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(saida.get(0).getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(saida.get(0).getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(saida.get(0).getStatusAluguel(), filmeEntity.getStatusAluguel());
    }

    @Test
    public void deveMapToEntity(){
        Long idCliente = 1L;
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        List<FilmeEntity> listaFilmeEntity = new ArrayList<>();
        listaFilmeEntity.add(filmeEntity);
        Date dataAluguel = new Date(2010, 01, 16);
        Date dataDevolucao = new Date(2010, 01, 23);
        String statusDevolucao = "Nao devolvido";
        Double valorTotal = 20D;

        AluguelEntity saida = aluguelMapper.mapToEntity(idCliente, listaFilmeEntity, dataAluguel, dataDevolucao, statusDevolucao, valorTotal);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getIdCliente(), idCliente);
        Assert.assertEquals(saida.getListaFilmes().get(0).getId(), filmeEntity.getId());
        Assert.assertEquals(saida.getListaFilmes().get(0).getNome(), filmeEntity.getNome());
        Assert.assertEquals(saida.getListaFilmes().get(0).getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(saida.getListaFilmes().get(0).getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(saida.getListaFilmes().get(0).getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getListaFilmes().get(0).getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(saida.getListaFilmes().get(0).getStatusAluguel(), filmeEntity.getStatusAluguel());
        Assert.assertEquals(saida.getDataAluguel(), dataAluguel);
        Assert.assertEquals(saida.getDataDevolucao(), dataDevolucao);
        Assert.assertEquals(saida.getStatusDevolucao(), statusDevolucao);
        Assert.assertEquals(saida.getValorTotal(), valorTotal);
    }
}
