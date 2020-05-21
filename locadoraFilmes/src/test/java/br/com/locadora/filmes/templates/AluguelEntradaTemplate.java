package br.com.locadora.filmes.templates;

import br.com.locadora.filmes.aluguel.model.AluguelEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AluguelEntradaTemplate implements TemplateLoader {

    public static final String ALUGUEL_ENTRADA_TEMPLATE_VALIDO = "aluguel entrada template valido";

    @Override
    public void load() {
        Fixture.of(AluguelEntrada.class).addTemplate(ALUGUEL_ENTRADA_TEMPLATE_VALIDO, new Rule(){{
            List<Long> idFilme = new ArrayList<Long>(){{add(1L);}};
            add("listaFilmes", idFilme);
            add("dataAluguel", new Date(2010, 01, 16));
        }});
    }
}
