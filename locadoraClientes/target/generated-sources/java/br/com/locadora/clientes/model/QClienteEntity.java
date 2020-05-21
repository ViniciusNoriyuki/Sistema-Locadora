package br.com.locadora.clientes.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClienteEntity is a Querydsl query type for ClienteEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClienteEntity extends EntityPathBase<ClienteEntity> {

    private static final long serialVersionUID = -1643348405L;

    public static final QClienteEntity clienteEntity = new QClienteEntity("clienteEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idade = createNumber("idade", Integer.class);

    public final StringPath nome = createString("nome");

    public QClienteEntity(String variable) {
        super(ClienteEntity.class, forVariable(variable));
    }

    public QClienteEntity(Path<? extends ClienteEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClienteEntity(PathMetadata metadata) {
        super(ClienteEntity.class, metadata);
    }

}

