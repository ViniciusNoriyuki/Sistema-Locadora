package br.com.locadora.clientes.controller;

import br.com.locadora.clientes.facade.ClienteFacade;
import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.locadora.clientes.model.ClienteSaida;
import br.com.locadora.clientes.templates.ClienteEntradaTemplate;
import br.com.locadora.clientes.templates.ClienteSaidaTemplate;
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
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteFacade clienteFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        FixtureFactoryLoader.loadTemplates("br.com.locadora.clientes.templates");
    }

    @Test
    public void deveSalvarCliente() throws Exception {
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_VALIDO);
        ClienteSaida clienteSaida = Fixture.from(ClienteSaida.class).gimme(ClienteSaidaTemplate.CLIENTE_SAIDA_TEMAPLTE_VALIDO);
        Mockito.when(clienteFacade.salvar(Mockito.any(ClienteEntrada.class))).thenReturn(clienteSaida);

        MvcResult result = mockMvc.perform(post("/locadora/cliente/salvar")
                .content(asJsonString(clienteEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ClienteSaida saida = new ObjectMapper().readValue(json, ClienteSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), clienteSaida.getId());
        Assert.assertEquals(saida.getNome(), clienteSaida.getNome());
        Assert.assertEquals(saida.getIdade(), clienteSaida.getIdade());
        Assert.assertEquals(saida.getNome(), clienteEntrada.getNome());
        Assert.assertEquals(saida.getIdade(), clienteEntrada.getIdade());
    }

    @Test
    public void deveListarClientes() throws Exception {
        ClienteSaida clienteSaida = Fixture.from(ClienteSaida.class).gimme(ClienteSaidaTemplate.CLIENTE_SAIDA_TEMAPLTE_VALIDO);
        List<ClienteSaida> listaClienteSaida = new ArrayList<>();
        listaClienteSaida.add(clienteSaida);
        Mockito.when(clienteFacade.listarClientes()).thenReturn(listaClienteSaida);

        MvcResult result = mockMvc.perform(get("/locadora/cliente/listarClientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<ClienteSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, ClienteSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), clienteSaida.getId());
        Assert.assertEquals(saida.get(0).getNome(), clienteSaida.getNome());
        Assert.assertEquals(saida.get(0).getIdade(), clienteSaida.getIdade());
    }

    @Test
    public void deveAlterarIdade() throws Exception {
        ClienteSaida clienteSaida = Fixture.from(ClienteSaida.class).gimme(ClienteSaidaTemplate.CLIENTE_SAIDA_TEMAPLTE_VALIDO);
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_VALIDO);
        Long id = 1L;
        Mockito.when(clienteFacade.alterarIdade(Mockito.anyLong(), Mockito.any(ClienteEntrada.class))).thenReturn(clienteSaida);

        MvcResult result = mockMvc.perform(put("/locadora/cliente/"+id+"/alterarIdade")
                .content(asJsonString(clienteEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ClienteSaida saida = new ObjectMapper().readValue(json, ClienteSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), clienteSaida.getId());
        Assert.assertEquals(saida.getNome(), clienteSaida.getNome());
        Assert.assertEquals(saida.getIdade(), clienteSaida.getIdade());
        Assert.assertEquals(saida.getNome(), clienteEntrada.getNome());
        Assert.assertEquals(saida.getIdade(), clienteEntrada.getIdade());
    }

    @Test
    public void deveBuscarCliente() throws Exception {
        ClienteSaida clienteSaida = Fixture.from(ClienteSaida.class).gimme(ClienteSaidaTemplate.CLIENTE_SAIDA_TEMAPLTE_VALIDO);
        Long id = 1L;
        Mockito.when(clienteFacade.buscarCliente(Mockito.anyLong())).thenReturn(clienteSaida);

        MvcResult result = mockMvc.perform(get("/locadora/cliente/"+id+"/buscarCliente")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ClienteSaida saida = new ObjectMapper().readValue(json, ClienteSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), clienteSaida.getId());
        Assert.assertEquals(saida.getNome(), clienteSaida.getNome());
        Assert.assertEquals(saida.getIdade(), clienteSaida.getIdade());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
