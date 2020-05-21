package br.com.locadora.filmes.filme.controller;

import br.com.locadora.filmes.filme.facade.FilmeFacade;
import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import br.com.locadora.filmes.templates.FilmeEntradaTemplate;
import br.com.locadora.filmes.templates.FilmeSaidaTemplate;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class FilmeControllerTest {

    @InjectMocks
    private FilmeController filmeController;

    @Mock
    private FilmeFacade filmeFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
        FixtureFactoryLoader.loadTemplates("br.com.locadora.filmes.templates");
    }

    @Test
    public void deveSalvarFilme() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_VALIDO);
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_SAIDA_TEMPLATE_VALIDO);
        Mockito.when(filmeFacade.salvarFilme(Mockito.any(FilmeEntrada.class))).thenReturn(filmeSaida);

        MvcResult result = mockMvc.perform(post("/locadora/filme/salvar")
                .content(asJsonString(filmeEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FilmeSaida saida = new ObjectMapper().readValue(json, FilmeSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeSaida.getId());
        Assert.assertEquals(saida.getNome(), filmeSaida.getNome());
        Assert.assertEquals(saida.getGenero(), filmeSaida.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeSaida.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeSaida.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeSaida.getValorFilme());
        Assert.assertEquals(saida.getStatusAluguel(), filmeSaida.getStatusAluguel());
        Assert.assertEquals(saida.getNome(), filmeEntrada.getNome());
        Assert.assertEquals(saida.getGenero(), filmeEntrada.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeEntrada.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeEntrada.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeEntrada.getValorFilme());
    }

    @Test
    public void deveListarFilmes() throws Exception {
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_SAIDA_TEMPLATE_VALIDO);
        List<FilmeSaida> listaFilmeSaida = new ArrayList<>();
        listaFilmeSaida.add(filmeSaida);
        Mockito.when(filmeFacade.listarFilmes()).thenReturn(listaFilmeSaida);

        MvcResult result = mockMvc.perform(get("/locadora/filme/listarFilmes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<FilmeSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, FilmeSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), filmeSaida.getId());
        Assert.assertEquals(saida.get(0).getNome(), filmeSaida.getNome());
        Assert.assertEquals(saida.get(0).getGenero(), filmeSaida.getGenero());
        Assert.assertEquals(saida.get(0).getDataLancamento(), filmeSaida.getDataLancamento());
        Assert.assertEquals(saida.get(0).getClassificacaoIndicativa(), filmeSaida.getClassificacaoIndicativa());
        Assert.assertEquals(saida.get(0).getValorFilme(), filmeSaida.getValorFilme());
        Assert.assertEquals(saida.get(0).getStatusAluguel(), filmeSaida.getStatusAluguel());
    }

    @Test
    public void deveAlterarValorFilme() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_TEMPLATE_VALIDO);
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_SAIDA_TEMPLATE_VALIDO);
        Long id = 1L;
        Mockito.when(filmeFacade.alterarValorFilme(Mockito.anyLong(), Mockito.any(FilmeEntrada.class))).thenReturn(filmeSaida);

        MvcResult result = mockMvc.perform(put("/locadora/filme/"+id+"/alterarValor")
                .content(asJsonString(filmeEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FilmeSaida saida = new ObjectMapper().readValue(json, FilmeSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeSaida.getId());
        Assert.assertEquals(saida.getNome(), filmeSaida.getNome());
        Assert.assertEquals(saida.getGenero(), filmeSaida.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeSaida.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeSaida.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeSaida.getValorFilme());
        Assert.assertEquals(saida.getStatusAluguel(), filmeSaida.getStatusAluguel());
        Assert.assertEquals(saida.getNome(), filmeEntrada.getNome());
        Assert.assertEquals(saida.getGenero(), filmeEntrada.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeEntrada.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeEntrada.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeEntrada.getValorFilme());
    }

    @Test
    public void deveBuscarFilme() throws Exception {
        Long id = 1L;
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_SAIDA_TEMPLATE_VALIDO);
        Mockito.when(filmeFacade.buscarFilme(Mockito.anyLong())).thenReturn(filmeSaida);

        MvcResult result = mockMvc.perform(get("/locadora/filme/"+id+"/buscarFilme")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        FilmeSaida saida = new ObjectMapper().readValue(json, FilmeSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), filmeSaida.getId());
        Assert.assertEquals(saida.getNome(), filmeSaida.getNome());
        Assert.assertEquals(saida.getGenero(), filmeSaida.getGenero());
        Assert.assertEquals(saida.getDataLancamento(), filmeSaida.getDataLancamento());
        Assert.assertEquals(saida.getClassificacaoIndicativa(), filmeSaida.getClassificacaoIndicativa());
        Assert.assertEquals(saida.getValorFilme(), filmeSaida.getValorFilme());
        Assert.assertEquals(saida.getStatusAluguel(), filmeSaida.getStatusAluguel());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
