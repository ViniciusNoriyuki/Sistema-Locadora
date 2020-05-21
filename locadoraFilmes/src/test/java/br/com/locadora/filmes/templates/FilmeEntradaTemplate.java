package br.com.locadora.filmes.templates;

import br.com.locadora.filmes.filme.model.FilmeEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.Date;


public class FilmeEntradaTemplate implements TemplateLoader {

    public static final String FILME_ENTRADA_TEMPLATE_VALIDO = "filme entrada template valido";
    public static final String FILME_ENTRADA_TEMPLATE_VALIDO_NOVO_VALOR_FILME = "filme entrada template valido novo valor filme";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_NOME_NULL = "filme entrada template invalido nome null";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_GENERO_NULL = "filme entrada template invalido genero null";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_DATA_LANCAMENTO_NULL = "filme entrada template invalido data lancamento null";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_CLASSIFICACAO_INDICATIVA_NULL = "filme entrada template invalido classificacao indicativa null";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NULL = "filme entrada template invalido valor filme null";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_NOME_EMPTY = "filme entrada template invalido nome empty";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_GENERO_EMPITY = "filme entrada template invalido genero empty";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_CLASSIFICACAO_INDICATIVA_NEGATIVA = "filme entrada template invalido classificacao indicativa negativa";
    public static final String FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NEGATIVO = "filme entrada template invalido valor filme negativo";

    @Override
    public void load() {
        Fixture.of(FilmeEntrada.class).addTemplate(FILME_ENTRADA_TEMPLATE_VALIDO, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_VALIDO_NOVO_VALOR_FILME, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 15D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_NOME_NULL, new Rule(){{
            add("nome", null);
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_GENERO_NULL, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", null);
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_DATA_LANCAMENTO_NULL, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", null);
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_CLASSIFICACAO_INDICATIVA_NULL, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", null);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NULL, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", null);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_NOME_EMPTY, new Rule(){{
            add("nome", "");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_GENERO_EMPITY, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_CLASSIFICACAO_INDICATIVA_NEGATIVA, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", -10);
            add("valorFilme", 20D);
        }}).addTemplate(FILME_ENTRADA_TEMPLATE_INVALIDO_VALOR_FILME_NEGATIVO, new Rule(){{
            add("nome", "Jurassic Park");
            add("genero", "ficcao, acao, aventura");
            add("dataLancamento", new Date(2018, 02, 20));
            add("classificacaoIndicativa", 12);
            add("valorFilme", -10D);
        }});
    }
}
