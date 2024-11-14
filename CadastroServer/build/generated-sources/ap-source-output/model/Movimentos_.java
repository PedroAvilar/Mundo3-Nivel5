package model;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Pessoas;
import model.Produtos;
import model.Usuarios;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-11-14T11:45:25", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Movimentos.class)
public class Movimentos_ { 

    public static volatile SingularAttribute<Movimentos, BigDecimal> precoUnitario;
    public static volatile SingularAttribute<Movimentos, Character> tipo;
    public static volatile SingularAttribute<Movimentos, Integer> quantidadeMovimentado;
    public static volatile SingularAttribute<Movimentos, Integer> iDMovimento;
    public static volatile SingularAttribute<Movimentos, Usuarios> usuariosIDUsuario;
    public static volatile SingularAttribute<Movimentos, Pessoas> pessoasIDPessoa;
    public static volatile SingularAttribute<Movimentos, Produtos> produtosIDProduto;

}