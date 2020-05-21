package br.com.locadora.filmes.aluguel.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAluguelEntity is a Querydsl query type for AluguelEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAluguelEntity extends EntityPathBase<AluguelEntity> {

    private static final long serialVersionUID = -667339622L;

    public static final QAluguelEntity aluguelEntity = new QAluguelEntity("aluguelEntity");

    public final DateTimePath<java.util.Date> dataAluguel = createDateTime("dataAluguel", java.util.Date.class);

    public final DateTimePath<java.util.Date> dataDevolucao = createDateTime("dataDevolucao", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idCliente = createNumber("idCliente", Long.class);

    public final ListPath<br.com.locadora.filmes.filme.model.FilmeEntity, br.com.locadora.filmes.filme.model.QFilmeEntity> listaFilmes = this.<br.com.locadora.filmes.filme.model.FilmeEntity, br.com.locadora.filmes.filme.model.QFilmeEntity>createList("listaFilmes", br.com.locadora.filmes.filme.model.FilmeEntity.class, br.com.locadora.filmes.filme.model.QFilmeEntity.class, PathInits.DIRECT2);

    public final StringPath statusDevolucao = createString("statusDevolucao");

    public final NumberPath<Double> valorTotal = createNumber("valorTotal", Double.class);

    public QAluguelEntity(String variable) {
        super(AluguelEntity.class, forVariable(variable));
    }

    public QAluguelEntity(Path<? extends AluguelEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAluguelEntity(PathMetadata metadata) {
        super(AluguelEntity.class, metadata);
    }

}

