package model;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Movimentos;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-11-14T11:59:55", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Produtos.class)
public class Produtos_ { 

    public static volatile CollectionAttribute<Produtos, Movimentos> movimentosCollection;
    public static volatile SingularAttribute<Produtos, BigDecimal> precoVendaBase;
    public static volatile SingularAttribute<Produtos, Integer> quantidadeProduto;
    public static volatile SingularAttribute<Produtos, Integer> iDProduto;
    public static volatile SingularAttribute<Produtos, String> nomeProduto;

}