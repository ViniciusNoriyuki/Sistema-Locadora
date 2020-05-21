package br.com.locadora.filmes.templates;

import br.com.locadora.filmes.aluguel.model.AluguelEntity;
import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Date;

public class AluguelEntityTemplate implements TemplateLoader {

    public static final String ALUGUEL_ENTITY_TEMPLATE_VALIDO = "aluguel entity template valido";
    public static final String ALUGUEL_ENTITY_TEMPLATE_INVALIDO_DEVOLVIDO = "aluguel entity template invalido devolvido";

    @Override
    public void load() {
        Fixture.of(AluguelEntity.class).addTemplate(ALUGUEL_ENTITY_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("idCliente", 1L);
            add("listaFilmes", has(1).of(FilmeEntity.class, FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO));
            add("dataAluguel", new Date(2010, 01, 16));
            add("dataDevolucao", new Date(2010, 01, 23));
            add("statusDevolucao", "Nao devolvido");
            add("valorTotal", 20D);
        }}).addTemplate(ALUGUEL_ENTITY_TEMPLATE_INVALIDO_DEVOLVIDO, new Rule(){{
            add("id", 1L);
            add("idCliente", 1L);
            add("listaFilmes", has(1).of(FilmeEntity.class, FilmeEntityTemplate.FILME_ENTITY_TEMPLATE_VALIDO));
            add("dataAluguel", new Date(2010, 01, 16));
            add("dataDevolucao", new Date(2010, 01, 23));
            add("statusDevolucao", "Devolvido");
            add("valorTotal", 20D);
        }});
    }
}
