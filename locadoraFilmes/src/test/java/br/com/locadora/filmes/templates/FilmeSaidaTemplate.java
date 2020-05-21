package br.com.locadora.filmes.templates;

import br.com.locadora.filmes.filme.model.FilmeSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Date;

public class FilmeSaidaTemplate implements TemplateLoader {

    public static final String FILME_SAIDA_TEMPLATE_VALIDO = "filme saida template valido";

    @Override
    public void load() {
        Fixture.of(FilmeSaida.class).addTemplate(FILME_SAIDA_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
            add("statusAluguel", "Disponivel");
        }});
    }
}
