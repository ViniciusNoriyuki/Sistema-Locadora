package br.com.locadora.clientes.templates;

import br.com.locadora.clientes.model.ClienteEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ClienteEntradaTemplate implements TemplateLoader {

    public static final String CLIENTE_ENTRADA_TEMPLATE_VALIDO = "cliente entrada template valido";
    public static final String CLIENTE_ENTRADA_TEMPLATE_VALIDO_IDADE = "cliente entrada template valido idade";
    public static final String CLIENTE_ENTRADA_TEMPLATE_INVALIDO_NOME_NULL = "cliente entrada template invalido nome null";
    public static final String CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NULL = "cliente entrada template invalido idade null";
    public static final String CLIENTE_ENTRADA_TEMPLATE_INVALIDO_NOME_VAZIO = "cliente entrada template invalido nome vazio";
    public static final String CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NEGATIVA = "cliente entrada template invalido idade negativa";

    @Override
    public void load() {
        Fixture.of(ClienteEntrada.class).addTemplate(CLIENTE_ENTRADA_TEMPLATE_VALIDO, new Rule(){{
            add("nome", "Vinicius");
            add("idade", 25);
        }}).addTemplate(CLIENTE_ENTRADA_TEMPLATE_VALIDO_IDADE, new Rule(){{
            add("nome", "Vinicius");
            add("idade", 30);
        }}).addTemplate(CLIENTE_ENTRADA_TEMPLATE_INVALIDO_NOME_NULL, new Rule(){{
            add("nome", null);
            add("idade", 25);
        }}).addTemplate(CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NULL, new Rule(){{
            add("nome", "Vinicius");
            add("idade", null);
        }}).addTemplate(CLIENTE_ENTRADA_TEMPLATE_INVALIDO_NOME_VAZIO, new Rule(){{
            add("nome", "");
            add("idade", 25);
        }}).addTemplate(CLIENTE_ENTRADA_TEMPLATE_INVALIDO_IDADE_NEGATIVA, new Rule(){{
            add("nome", "Vinicius");
            add("idade", -30);
        }});
    }
}
