package br.com.locadora.filmes.templates;

import br.com.locadora.filmes.aluguel.model.AluguelSaida;
import br.com.locadora.filmes.filme.model.FilmeSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Date;

public class AluguelSaidaTemplate implements TemplateLoader {

    public static final String ALUGUEL_SAIDA_TEMPLATE_VALIDO = "aluguel saida template valido";

    @Override
    public void load() {
        Fixture.of(AluguelSaida.class).addTemplate(ALUGUEL_SAIDA_TEMPLATE_VALIDO, new Rule(){{
            add("id", 1L);
            add("idCliente", 1L);
            add("listaFilmes", has(1).of(FilmeSaida.class, FilmeSaidaTemplate.FILME_SAIDA_TEMPLATE_VALIDO));
            add("dataAluguel", new Date(2010, 01, 16));
            add("dataDevolucao", new Date(2010, 01, 23));
            add("statusDevolucao", "Nao devolvido");
            add("valorTotal", 20D);
        }});
    }
}
