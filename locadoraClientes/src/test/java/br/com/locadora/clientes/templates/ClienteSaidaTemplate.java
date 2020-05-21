package br.com.locadora.clientes.templates;

import br.com.locadora.clientes.model.ClienteSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ClienteSaidaTemplate implements TemplateLoader {

    public static final String CLIENTE_SAIDA_TEMAPLTE_VALIDO = "cliente saida template valido";

    @Override
    public void load() {
        Fixture.of(ClienteSaida.class).addTemplate(CLIENTE_SAIDA_TEMAPLTE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "Vinicius");
            add("idade", 25);
        }});
    }
}
