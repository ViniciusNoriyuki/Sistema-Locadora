package br.com.locadora.clientes.facade;

import br.com.locadora.clientes.model.ClienteEntity;
import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.locadora.clientes.model.ClienteSaida;
import br.com.locadora.clientes.repository.ClienteRepository;
import br.com.locadora.clientes.templates.ClienteEntityTemplate;
import br.com.locadora.clientes.templates.ClienteEntradaTemplate;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteFacadeTest {

    @InjectMocks
    ClienteFacade clienteFacade;

    @Mock
    ClienteRepository clienteRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.locadora.clientes.templates");
    }

    @Test
    public void deveSalvar() throws Exception {
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_VALIDO);
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(clienteRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(clienteEntity);

        ClienteSaida saida = clienteFacade.salvar(clienteEntrada);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), clienteEntity.getId());
        Assert.assertEquals(saida.getNome(), clienteEntity.getNome());
        Assert.assertEquals(saida.getIdade(), clienteEntity.getIdade());
        Assert.assertEquals(saida.getNome(), clienteEntrada.getNome());
        Assert.assertEquals(saida.getIdade(), clienteEntrada.getIdade());
    }

    @Test(expected = Exception.class)
    public void deveGerarErroSalvarNomeNull() throws Exception {
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_INVALIDO_NOME_NULL);

        ClienteSaida saida = clienteFacade.salvar(clienteEntrada);
    }

    @Test(expected = Exception.class)
    public void deveGerarErroSalvarIdadeNull() throws Exception {
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NULL);

        ClienteSaida saida = clienteFacade.salvar(clienteEntrada);
    }

    @Test(expected = Exception.class)
    public void deveGerarErroSalvarNomeVazio() throws Exception {
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_INVALIDO_NOME_VAZIO);

        ClienteSaida saida = clienteFacade.salvar(clienteEntrada);
    }

    @Test(expected = Exception.class)
    public void deveGerarErroSalvarIdadeNegativa() throws Exception {
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NEGATIVA);

        ClienteSaida saida = clienteFacade.salvar(clienteEntrada);
    }

    @Test
    public void deveListarClientes() throws Exception {
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);
        List<ClienteEntity> listaClienteEntity = new ArrayList<>();
        listaClienteEntity.add(clienteEntity);
        Mockito.when(clienteRepository.findAll()).thenReturn(listaClienteEntity);

        List<ClienteSaida> saida = clienteFacade.listarClientes();

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.get(0).getId(), clienteEntity.getId());
        Assert.assertEquals(saida.get(0).getNome(), clienteEntity.getNome());
        Assert.assertEquals(saida.get(0).getIdade(), clienteEntity.getIdade());
    }

    @Test(expected = Exception.class)
    public void deveGerarErroListarClientesVazio() throws Exception {
        List<ClienteSaida> saida = clienteFacade.listarClientes();
    }

    @Test
    public void deveAlterarIdade() throws Exception {
        Long id = 1L;
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_VALIDO_IDADE);
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(clienteEntity));
        Mockito.when(clienteRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(clienteEntity);

        ClienteSaida saida = clienteFacade.alterarIdade(id, clienteEntrada);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), clienteEntity.getId());
        Assert.assertEquals(saida.getNome(), clienteEntity.getNome());
        Assert.assertEquals(saida.getIdade(), clienteEntity.getIdade());
        Assert.assertEquals(saida.getNome(), clienteEntrada.getNome());
        Assert.assertEquals(saida.getIdade(), clienteEntrada.getIdade());
    }

    @Test(expected = Exception.class)
    public void deveGerarErroAlterarIdadeRetornoBancoVazio() throws Exception {
        Long id = null;
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_VALIDO);
        ClienteSaida saida = clienteFacade.alterarIdade(id, clienteEntrada);
    }

    @Test(expected = Exception.class)
    public void deveGerarErroAlterarIdadeNull() throws Exception {
        Long id = 1L;
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NULL);
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(clienteEntity));
        ClienteSaida saida = clienteFacade.alterarIdade(id, clienteEntrada);
    }

    @Test(expected = Exception.class)
    public void deveGerarErroAlterarIdadeNegativa() throws Exception {
        Long id = 1L;
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NEGATIVA);
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(clienteEntity));
        ClienteSaida saida = clienteFacade.alterarIdade(id, clienteEntrada);
    }

    @Test(expected = Exception.class)
    public void deveGerarErroAlterarIdadeIgual() throws Exception {
        Long id = 1L;
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_VALIDO);
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(clienteEntity));
        ClienteSaida saida = clienteFacade.alterarIdade(id, clienteEntrada);
    }

    @Test
    public void deveBuscarCliente() throws Exception {
        Long id = 1L;
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(clienteEntity));

        ClienteSaida saida = clienteFacade.buscarCliente(id);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getId(), clienteEntity.getId());
        Assert.assertEquals(saida.getNome(), clienteEntity.getNome());
        Assert.assertEquals(saida.getIdade(), clienteEntity.getIdade());
    }

    @Test(expected = Exception.class)
    public void deveGerarErroBuscarCliente() throws Exception {
        Long id = null;

        ClienteSaida saida = clienteFacade.buscarCliente(id);
    }
}
