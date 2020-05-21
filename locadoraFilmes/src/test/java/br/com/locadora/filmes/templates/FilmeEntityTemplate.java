package br.com.locadora.filmes.templates;

import br.com.locadora.filmes.filme.model.FilmeEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Date;

public class FilmeEntityTemplate implements TemplateLoader {

    public static final String FILME_ENTITY_TEMPLATE_VALIDO = "filme entity template valido";
    public static final String FILME_ENTITY_TEMPLATE_INVALIDO_ALUGADO = "filme entity template invalido alugado";

    @Override
    public void load() {
        Fixture.of(FilmeEntity.class).addTemplate(FILME_ENTITY_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
            add("statusAluguel", "Disponivel");
        }}).addTemplate(FILME_ENTITY_TEMPLATE_INVALIDO_ALUGADO, new Rule(){{
            add("id", 1L);
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
            add("statusAluguel", "Alugado");
        }});
    }
}
