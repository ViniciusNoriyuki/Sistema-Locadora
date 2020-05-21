package br.com.locadora.filmes.filme.facade;

import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import br.com.locadora.filmes.filme.repository.FilmeRepository;
import br.com.locadora.filmes.templates.FilmeEntityTemplate;
import br.com.locadora.filmes.templates.FilmeEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class FilmeFacadeTest {

    @InjectMocks
    FilmeFacade filmeFacade;

    @Mock
    FilmeRepository filmeRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.locadora.filmes.templates");
    }

    @Test
    public void deveSalvarFilme() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_VALIDO);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.save(Mockito.any(FilmeEntity.class))).thenReturn(filmeEntity);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeEntity.getId());
        Assert.assertEquals(saida.getNome(), filmeEntity.getNome());
        Assert.assertEquals(saida.getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(saida.getStatusAluguel(), filmeEntity.getStatusAluguel());
        Assert.assertEquals(saida.getNome(), filmeEntrada.getNome());
        Assert.assertEquals(saida.getGenero(), filmeEntrada.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeEntrada.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeEntrada.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeEntrada.getValorFilme());
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaNomeNull() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_NOME_NULL);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradGeneroNull() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_GENERO_NULL);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaDataLancamentoNull() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_DATA_LANCAMENTO_NULL);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaClassificacaoIndicativaNull() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_CLASSIFICACAO_INDICATIVA_NULL);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaValorFilmeNull() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NULL);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaNomeEmpty() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_NOME_EMPTY);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaDataGeneroEmpty() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_GENERO_EMPITY);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaClassificacaoIndicativaNegativa() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_CLASSIFICACAO_INDICATIVA_NEGATIVA);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroValidarDadosEntradaValorFilmeNegativo() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NEGATIVO);

        FilmeSaida saida = filmeFacade.salvarFilme(filmeEntrada);
    }

    @Test
    public void deveListarFilmes() throws Exception {
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        List<FilmeEntity> listFilmeEntity = new ArrayList<>();
        listFilmeEntity.add(filmeEntity);
        Mockito.when(filmeRepository.findAll()).thenReturn(listFilmeEntity);

        List<FilmeSaida> saida = filmeFacade.listarFilmes();

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), filmeEntity.getId());
        Assert.assertEquals(saida.get(0).getNome(), filmeEntity.getNome());
        Assert.assertEquals(saida.get(0).getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(saida.get(0).getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(saida.get(0).getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(saida.get(0).getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(saida.get(0).getStatusAluguel(), filmeEntity.getStatusAluguel());
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroListarFilmesEmpty() throws Exception {
        List<FilmeSaida> saida = filmeFacade.listarFilmes();
    }

    @Test
    public void deveAlterarValorFilme() throws Exception {
        Long id = 1L;
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_VALIDO_NOVO_VALOR_FILME);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));
        Mockito.when(filmeRepository.save(Mockito.any(FilmeEntity.class))).thenReturn(filmeEntity);

        FilmeSaida saida = filmeFacade.alterarValorFilme(id, filmeEntrada);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeEntity.getId());
        Assert.assertEquals(saida.getNome(), filmeEntity.getNome());
        Assert.assertEquals(saida.getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getStatusAluguel(), filmeEntity.getStatusAluguel());
        Assert.assertEquals(saida.getNome(), filmeEntrada.getNome());
        Assert.assertEquals(saida.getGenero(), filmeEntrada.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeEntrada.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeEntrada.getClassificacaoIndicativa());
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAlterarValorFilmeBancoEmpty() throws Exception {
        Long id = null;
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_VALIDO);

        FilmeSaida saida = filmeFacade.alterarValorFilme(id, filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAlterarValorFilmeNull() throws Exception {
        Long id = 1L;
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NULL);

        FilmeSaida saida = filmeFacade.alterarValorFilme(id, filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAlterarValorFilmeNegativo() throws Exception {
        Long id = 1L;
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NEGATIVO);

        FilmeSaida saida = filmeFacade.alterarValorFilme(id, filmeEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroAlterarValorFilmeIgual() throws Exception {
        Long id = 1L;
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_VALIDO);

        FilmeSaida saida = filmeFacade.alterarValorFilme(id, filmeEntrada);
    }

    @Test
    public void deveBuscarFilme() throws Exception {
        Long id = 1L;
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));

        FilmeSaida saida = filmeFacade.buscarFilme(id);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeEntity.getId());
        Assert.assertEquals(saida.getNome(), filmeEntity.getNome());
        Assert.assertEquals(saida.getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(saida.getStatusAluguel(), filmeEntity.getStatusAluguel());
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroBuscarFilmeBancoEmpty() throws Exception {
        Long id = null;

        FilmeSaida saida = filmeFacade.buscarFilme(id);
    }
}
