package br.com.locadora.filmes.aluguel.controller;

import br.com.locadora.filmes.aluguel.facade.AluguelFacade;
import br.com.locadora.filmes.aluguel.model.AluguelEntrada;
import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.templates.AluguelEntradaTemplate;
import br.com.locadora.filmes.templates.AluguelSaidaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AluguelControllerTest {

    @InjectMocks
    private AluguelController aluguelController;

    @Mock
    private AluguelFacade aluguelFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(aluguelController).build();
        FixtureFactoryLoader.loadTemplates("br.com.locadora.filmes.templates");
    }

    @Test
    public void deveAlugarFilme() throws Exception {
        Long id = 1L;
        AluguelEntrada aluguelEntrada = Fixture.from(AluguelEntrada.class).gimme(AluguelEntradaTemplate.ALUGUEL_ENTRADA_TEMPLATE_VALIDO);
        AluguelSaida aluguelSaida = Fixture.from(AluguelSaida.class).gimme(AluguelSaidaTemplate.ALUGUEL_SAIDA_TEMPLATE_VALIDO);
        Mockito.when(aluguelFacade.alugarFilme(Mockito.anyLong(), Mockito.any(AluguelEntrada.class))).thenReturn(aluguelSaida);

        MvcResult result = mockMvc.perform(post("/locadora/aluguel/"+id+"/alugar")
                .content(asJsonString(aluguelEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        AluguelSaida saida = new ObjectMapper().readValue(json, AluguelSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), aluguelSaida.getId());
        Assert.assertEquals(saida.getIdCliente(), aluguelSaida.getIdCliente());
        Assert.assertEquals(saida.getListaFilmes().get(0).getId(), aluguelSaida.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.getListaFilmes().get(0).getNome(), aluguelSaida.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.getListaFilmes().get(0).getGenero(), aluguelSaida.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.getListaFilmes().get(0).getDataLancamento(), aluguelSaida.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelSaida.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.getListaFilmes().get(0).getValorFilme(), aluguelSaida.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.getListaFilmes().get(0).getStatusAluguel(), aluguelSaida.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.getDataAluguel(), aluguelSaida.getDataAluguel());
        Assert.assertEquals(saida.getDataDevolucao(), aluguelSaida.getDataDevolucao());
        Assert.assertEquals(saida.getStatusDevolucao(), aluguelSaida.getStatusDevolucao());
        Assert.assertEquals(saida.getValorTotal(), aluguelSaida.getValorTotal());
        Assert.assertEquals(saida.getDataAluguel(), aluguelEntrada.getDataAluguel());
    }

    @Test
    public void deveMostraraExtratoAluguelPorPeriodo() throws Exception {
        String dateFrom = "2010-02-10";
        String dateTo = "2010-02-20";
        AluguelSaida aluguelSaida = Fixture.from(AluguelSaida.class).gimme(AluguelSaidaTemplate.ALUGUEL_SAIDA_TEMPLATE_VALIDO);
        List<AluguelSaida> listaAluguelSaida = new ArrayList<>();
        listaAluguelSaida.add(aluguelSaida);
        Mockito.when(aluguelFacade.extratoAluguelPorPeriodo(Mockito.any(Date.class), Mockito.any(Date.class))).thenReturn(listaAluguelSaida);

        MvcResult result = mockMvc.perform(get("/locadora/aluguel/extratoAluguelPorPeriodo")
                .param("dateFrom", String.valueOf(dateFrom)).param("dateTo", String.valueOf(dateTo))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<AluguelSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, AluguelSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), aluguelSaida.getId());
        Assert.assertEquals(saida.get(0).getIdCliente(), aluguelSaida.getIdCliente());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getId(), aluguelSaida.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getNome(), aluguelSaida.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getGenero(), aluguelSaida.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getDataLancamento(), aluguelSaida.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelSaida.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getValorFilme(), aluguelSaida.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getStatusAluguel(), aluguelSaida.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.get(0).getDataAluguel(), aluguelSaida.getDataAluguel());
        Assert.assertEquals(saida.get(0).getDataDevolucao(), aluguelSaida.getDataDevolucao());
        Assert.assertEquals(saida.get(0).getStatusDevolucao(), aluguelSaida.getStatusDevolucao());
        Assert.assertEquals(saida.get(0).getValorTotal(), aluguelSaida.getValorTotal());
    }

    @Test
    public void deveDevolverFilme() throws Exception {
        AluguelSaida aluguelSaida = Fixture.from(AluguelSaida.class).gimme(AluguelSaidaTemplate.ALUGUEL_SAIDA_TEMPLATE_VALIDO);
        Long idAluguel = 1L;
        Long idCliente = 1L;
        Mockito.when(aluguelFacade.devolverFilme(Mockito.anyLong(), Mockito.anyLong())).thenReturn(aluguelSaida);

        MvcResult result = mockMvc.perform(put("/locadora/aluguel/"+idAluguel+"/"+idCliente+"/devolver")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        AluguelSaida saida = new ObjectMapper().readValue(json, AluguelSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), aluguelSaida.getId());
        Assert.assertEquals(saida.getIdCliente(), aluguelSaida.getIdCliente());
        Assert.assertEquals(saida.getListaFilmes().get(0).getId(), aluguelSaida.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.getListaFilmes().get(0).getNome(), aluguelSaida.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.getListaFilmes().get(0).getGenero(), aluguelSaida.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.getListaFilmes().get(0).getDataLancamento(), aluguelSaida.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelSaida.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.getListaFilmes().get(0).getValorFilme(), aluguelSaida.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.getListaFilmes().get(0).getStatusAluguel(), aluguelSaida.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.getDataAluguel(), aluguelSaida.getDataAluguel());
        Assert.assertEquals(saida.getDataDevolucao(), aluguelSaida.getDataDevolucao());
        Assert.assertEquals(saida.getStatusDevolucao(), aluguelSaida.getStatusDevolucao());
        Assert.assertEquals(saida.getValorTotal(), aluguelSaida.getValorTotal());
    }

    @Test
    public void deveTrazerRelatorioAlugueisVencidosENaoDevolvidos() throws Exception {
        String dateFrom = "2010-02-25";
        AluguelSaida aluguelSaida = Fixture.from(AluguelSaida.class).gimme(AluguelSaidaTemplate.ALUGUEL_SAIDA_TEMPLATE_VALIDO);
        List<AluguelSaida> listaAluguelSaida = new ArrayList<>();
        listaAluguelSaida.add(aluguelSaida);
        Mockito.when(aluguelFacade.relatorioAlugueisVencidosENaoDevolvidos(Mockito.any(Date.class))).thenReturn(listaAluguelSaida);

        MvcResult result = mockMvc.perform(get("/locadora/aluguel/relatorio")
                .param("dateFrom", String.valueOf(dateFrom))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<AluguelSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, AluguelSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), aluguelSaida.getId());
        Assert.assertEquals(saida.get(0).getIdCliente(), aluguelSaida.getIdCliente());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getId(), aluguelSaida.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getNome(), aluguelSaida.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getGenero(), aluguelSaida.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getDataLancamento(), aluguelSaida.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelSaida.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getValorFilme(), aluguelSaida.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getStatusAluguel(), aluguelSaida.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.get(0).getDataAluguel(), aluguelSaida.getDataAluguel());
        Assert.assertEquals(saida.get(0).getDataDevolucao(), aluguelSaida.getDataDevolucao());
        Assert.assertEquals(saida.get(0).getStatusDevolucao(), aluguelSaida.getStatusDevolucao());
        Assert.assertEquals(saida.get(0).getValorTotal(), aluguelSaida.getValorTotal());
    }

    @Test
    public void deveListarTodosAlugueis() throws Exception {
        AluguelSaida aluguelSaida = Fixture.from(AluguelSaida.class).gimme(AluguelSaidaTemplate.ALUGUEL_SAIDA_TEMPLATE_VALIDO);
        List<AluguelSaida> listaAluguelSaida = new ArrayList<>();
        listaAluguelSaida.add(aluguelSaida);
        Mockito.when(aluguelFacade.listarTodosAlugueis()).thenReturn(listaAluguelSaida);

        MvcResult result = mockMvc.perform(get("/locadora/aluguel/listarTodosAlugueis")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<AluguelSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, AluguelSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), aluguelSaida.getId());
        Assert.assertEquals(saida.get(0).getIdCliente(), aluguelSaida.getIdCliente());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getId(), aluguelSaida.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getNome(), aluguelSaida.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getGenero(), aluguelSaida.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getDataLancamento(), aluguelSaida.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelSaida.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getValorFilme(), aluguelSaida.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.get(0).getListaFilmes().get(0).getStatusAluguel(), aluguelSaida.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.get(0).getDataAluguel(), aluguelSaida.getDataAluguel());
        Assert.assertEquals(saida.get(0).getDataDevolucao(), aluguelSaida.getDataDevolucao());
        Assert.assertEquals(saida.get(0).getStatusDevolucao(), aluguelSaida.getStatusDevolucao());
        Assert.assertEquals(saida.get(0).getValorTotal(), aluguelSaida.getValorTotal());
    }

    @Test
    public void deveBuscarAluguel() throws Exception {
        Long idAluguel = 1L;
        AluguelSaida aluguelSaida = Fixture.from(AluguelSaida.class).gimme(AluguelSaidaTemplate.ALUGUEL_SAIDA_TEMPLATE_VALIDO);
        Mockito.when(aluguelFacade.buscarAluguel(Mockito.anyLong())).thenReturn(aluguelSaida);

        MvcResult result = mockMvc.perform(get("/locadora/aluguel/"+idAluguel+"/buscarAluguel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        AluguelSaida saida = new ObjectMapper().readValue(json, AluguelSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), aluguelSaida.getId());
        Assert.assertEquals(saida.getIdCliente(), aluguelSaida.getIdCliente());
        Assert.assertEquals(saida.getListaFilmes().get(0).getId(), aluguelSaida.getListaFilmes().get(0).getId());
        Assert.assertEquals(saida.getListaFilmes().get(0).getNome(), aluguelSaida.getListaFilmes().get(0).getNome());
        Assert.assertEquals(saida.getListaFilmes().get(0).getGenero(), aluguelSaida.getListaFilmes().get(0).getGenero());
        Assert.assertEquals(saida.getListaFilmes().get(0).getDataLancamento(), aluguelSaida.getListaFilmes().get(0).getDataLancamento());
        Assert.assertEquals(saida.getListaFilmes().get(0).getClassificacaoIndicativa(), aluguelSaida.getListaFilmes().get(0).getClassificacaoIndicativa());
        Assert.assertEquals(saida.getListaFilmes().get(0).getValorFilme(), aluguelSaida.getListaFilmes().get(0).getValorFilme());
        Assert.assertEquals(saida.getListaFilmes().get(0).getStatusAluguel(), aluguelSaida.getListaFilmes().get(0).getStatusAluguel());
        Assert.assertEquals(saida.getDataAluguel(), aluguelSaida.getDataAluguel());
        Assert.assertEquals(saida.getDataDevolucao(), aluguelSaida.getDataDevolucao());
        Assert.assertEquals(saida.getStatusDevolucao(), aluguelSaida.getStatusDevolucao());
        Assert.assertEquals(saida.getValorTotal(), aluguelSaida.getValorTotal());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
