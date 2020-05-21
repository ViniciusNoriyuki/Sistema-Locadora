package br.com.locadora.filmes.filme.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFilmeEntity is a Querydsl query type for FilmeEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFilmeEntity extends EntityPathBase<FilmeEntity> {

    private static final long serialVersionUID = 654319838L;

    public static final QFilmeEntity filmeEntity = new QFilmeEntity("filmeEntity");

    public final NumberPath<Integer> classificacaoIndicativa = createNumber("classificacaoIndicativa", Integer.class);

    public final DateTimePath<java.util.Date> dataLancamento = createDateTime("dataLancamento", java.util.Date.class);

    public final StringPath genero = createString("genero");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final StringPath statusAluguel = createString("statusAluguel");

    public final NumberPath<Double> valorFilme = createNumber("valorFilme", Double.class);

    public QFilmeEntity(String variable) {
        super(FilmeEntity.class, forVariable(variable));
    }

    public QFilmeEntity(Path<? extends FilmeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFilmeEntity(PathMetadata metadata) {
        super(FilmeEntity.class, metadata);
    }

}

