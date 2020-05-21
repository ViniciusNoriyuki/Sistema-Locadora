package br.com.locadora.filmes.aluguel.facade;

import br.com.locadora.filmes.aluguel.model.AluguelEntity;
import br.com.locadora.filmes.aluguel.model.AluguelEntrada;
import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.aluguel.repository.AluguelRepository;
import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.locadora.filmes.filme.repository.FilmeRepository;
import br.com.locadora.filmes.templates.AluguelEntityTemplate;
import br.com.locadora.filmes.templates.AluguelEntradaTemplate;
import br.com.locadora.filmes.templates.FilmeEntityTemplate;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class AluguelFacadeTest {

    @InjectMocks
    AluguelFacade aluguelFacade;

    @Mock
    AluguelRepository aluguelRepository;

    @Mock
    FilmeRepository filmeRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.locadora.filmes.templates");
    }

    @Test
    public void deveAlugarFilme() throws Exception {
        Long id = 1L;
        AluguelEntrada aluguelEntrada = Fixture.from(AluguelEntrada.class).gimme(AluguelEntradaTemplate.ALUGUEL_ENTRADA_TEMPLATE_VALIDO);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));

        AluguelSaida saida = aluguelFacade.alugarFilme(id, aluguelEntrada);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getListaFilmes().get(0).getId(), filmeEntity.getId());
        Assert.assertEquals(saida.getListaFilmes().get(0).getNome(), filmeEntity.getNome());
        Assert.assertEquals(saida.getListaFilmes().get(0).getGenero(), filmeEntity.getGenero());
        Assert.assertEquals(saida.getListaFilmes().get(0).getDataLancamento(), filmeEntity.getDataLancamento());
        Assert.assertEquals(saida.getListaFilmes().get(0).getClassificacaoIndicativa(), filmeEntity.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getListaFilmes().get(0).getValorFilme(), filmeEntity.getValorFilme());
        Assert.assertEquals(saida.getListaFilmes().get(0).getStatusAluguel(), filmeEntity.getStatusAluguel());
        Assert.assertEquals(saida.getDataAluguel(), aluguelEntrada.getDataAluguel());
    }

    @Test
    public void deveBuscarExtratoAluguelPorPeriodo() throws Exception {
        Date dateFrom = new Date(2010, 01, 10);
        Date datTo = new Date(2010, 01, 20);
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);
        Mockito.when(aluguelRepository.findAll()).thenReturn(listaAluguelEntity);

        List<AluguelSaida> saida = aluguelFacade.extratoAluguelPorPeriodo(dateFrom, datTo);

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

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroBuscarExtratoAluguelPorPeriodoDateFromMaior() throws Exception {
        Date dateFrom = new Date(2030, 01, 10);
        Date datTo = new Date(2010, 01, 20);
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);
        Mockito.when(aluguelRepository.findAll()).thenReturn(listaAluguelEntity);

        List<AluguelSaida> saida = aluguelFacade.extratoAluguelPorPeriodo(dateFrom, datTo);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroBuscarExtratoAluguelPorPeriodoDateToMenor() throws Exception {
        Date dateFrom = new Date(2010, 01, 10);
        Date datTo = new Date(2001, 01, 20);
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);
        Mockito.when(aluguelRepository.findAll()).thenReturn(listaAluguelEntity);

        List<AluguelSaida> saida = aluguelFacade.extratoAluguelPorPeriodo(dateFrom, datTo);
    }

    @Test
    public void deveDevolverFilme() throws Exception {
        Long idAluguel = 1L;
        Long idCliente = 1L;
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(aluguelRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(aluguelEntity));
        Mockito.when(aluguelRepository.save(Mockito.any(AluguelEntity.class))).thenReturn(aluguelEntity);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.save(Mockito.any(FilmeEntity.class))).thenReturn(filmeEntity);

        AluguelSaida saida = aluguelFacade.devolverFilme(idAluguel, idCliente);

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

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroDevolverFilmeClienteDiferente() throws Exception {
        Long idAluguel = 1L;
        Long idCliente = -1L;
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(aluguelRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(aluguelEntity));

        AluguelSaida saida = aluguelFacade.devolverFilme(idAluguel, idCliente);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroDevolverFilmeBancoNotPresent() throws Exception {
        Long idAluguel = null;
        Long idCliente = 1L;

        AluguelSaida saida = aluguelFacade.devolverFilme(idAluguel, idCliente);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroDevolverFilmeDevolvido() throws Exception {
        Long idAluguel = 1L;
        Long idCliente = 1L;
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_INVALIDO_DEVOLVIDO);
        Mockito.when(aluguelRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(aluguelEntity));
        Mockito.when(aluguelRepository.save(Mockito.any(AluguelEntity.class))).thenReturn(aluguelEntity);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.save(Mockito.any(FilmeEntity.class))).thenReturn(filmeEntity);

        AluguelSaida saida = aluguelFacade.devolverFilme(idAluguel, idCliente);
    }

    @Test
    public void deveTrazerRelatorioAlugueisVencidosENaoDevolvidos() throws Exception {
        Date dateFrom = new Date(2010, 01, 25);
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);
        Mockito.when(aluguelRepository.findAll()).thenReturn(listaAluguelEntity);

        List<AluguelSaida> saida = aluguelFacade.relatorioAlugueisVencidosENaoDevolvidos(dateFrom);

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

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroTrazerRelatorioAlugueisVencidosENaoDevolvidosEmpty() throws Exception {
        Date dateFrom = new Date(2001, 01, 25);
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);
        Mockito.when(aluguelRepository.findAll()).thenReturn(listaAluguelEntity);

        List<AluguelSaida> saida = aluguelFacade.relatorioAlugueisVencidosENaoDevolvidos(dateFrom);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroTrazerRelatorioAlugueisVencidosENaoDevolvidos() throws Exception {
        Date dateFrom = new Date(2010, 01, 25);
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_INVALIDO_DEVOLVIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);
        Mockito.when(aluguelRepository.findAll()).thenReturn(listaAluguelEntity);

        List<AluguelSaida> saida = aluguelFacade.relatorioAlugueisVencidosENaoDevolvidos(dateFrom);
    }

    @Test
    public void deveListarTodosAlugueis() throws Exception {
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        List<AluguelEntity> listaAluguelEntity = new ArrayList<>();
        listaAluguelEntity.add(aluguelEntity);
        Mockito.when(aluguelRepository.findAll()).thenReturn(listaAluguelEntity);

        List<AluguelSaida> saida = aluguelFacade.listarTodosAlugueis();

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

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroListarTodosAlugueisNotPresente() throws Exception {
        List<AluguelSaida> saida = aluguelFacade.listarTodosAlugueis();
    }

    @Test
    public void deveBuscarAluguel() throws Exception {
        Long id = 1L;
        AluguelEntity aluguelEntity = Fixture.from(AluguelEntity.class).gimme(AluguelEntityTemplate.ALUGUEL_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(aluguelRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(aluguelEntity));

        AluguelSaida saida = aluguelFacade.buscarAluguel(id);

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

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroBuscarAluguelNotPresent() throws Exception {
        Long id = null;

        AluguelSaida saida = aluguelFacade.buscarAluguel(id);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroPreencherListaFilmesAlugado() throws Exception {
        AluguelEntrada aluguelEntrada = Fixture.from(AluguelEntrada.class).gimme(AluguelEntradaTemplate.ALUGUEL_ENTRADA_TEMPLATE_VALIDO);
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_INVALIDO_ALUGADO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));

        List<FilmeEntity> saida = aluguelFacade.preencherListaFilmes(aluguelEntrada);
    }

    @Test(expected = java.lang.Exception.class)
    public void deveGerarErroRetornarFilmeEntityBancoNotPresente() throws Exception {
        Long id = null;

        FilmeEntity saida = aluguelFacade.retornarFilmeEntity(id);
    }
}
