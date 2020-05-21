package br.com.locadora.clientes.mapper;

import br.com.locadora.clientes.model.ClienteEntity;
import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.locadora.clientes.model.ClienteSaida;
import br.com.locadora.clientes.templates.ClienteEntityTemplate;
import br.com.locadora.clientes.templates.ClienteEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClienteMapperTest {

    private ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.locadora.clientes.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        ClienteEntrada clienteEntrada = Fixture.from(ClienteEntrada.class).gimme(ClienteEntradaTemplate.CLIENTE_ENTRADA_TEMPLATE_VALIDO);

        //when
        ClienteEntity clienteEntity = clienteMapper.converterEntradaParaEntity(clienteEntrada);

        //then
        Assert.assertNotNull(clienteEntity);
        Assert.assertEquals(clienteEntity.getNome(), clienteEntrada.getNome());
        Assert.assertEquals(clienteEntity.getIdade(), clienteEntrada.getIdade());
    }

    @Test
    public void deveConverterEntityParaSaida(){
        //given
        ClienteEntity clienteEntity = Fixture.from(ClienteEntity.class).gimme(ClienteEntityTemplate.CLIENTE_ENTITY_TEMPLATE_VALIDO);

        //when
        ClienteSaida clienteSaida = clienteMapper.converterEntityParaSaida(clienteEntity);

        //then
        Assert.assertNotNull(clienteSaida);
        Assert.assertEquals(clienteSaida.getId(), clienteEntity.getId());
        Assert.assertEquals(clienteSaida.getNome(), clienteEntity.getNome());
        Assert.assertEquals(clienteSaida.getIdade(), clienteEntity.getIdade());
    }
}
