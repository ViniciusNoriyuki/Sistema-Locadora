package br.com.locadora.clientes.templates;

import br.com.locadora.clientes.model.ClienteEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ClienteEntityTemplate implements TemplateLoader {

    public static final String CLIENTE_ENTITY_TEMPLATE_VALIDO = "cliente entity template valido";

    @Override
    public void load() {
        Fixture.of(ClienteEntity.class).addTemplate(CLIENTE_ENTITY_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "Vinicius");
            add("idade", 25);
        }});
    }
}
