package model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Movimentos;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-11-12T12:17:28", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile CollectionAttribute<Usuarios, Movimentos> movimentosCollection;
    public static volatile SingularAttribute<Usuarios, String> nomeUsuario;
    public static volatile SingularAttribute<Usuarios, String> senhaUsuario;
    public static volatile SingularAttribute<Usuarios, Integer> iDUsuario;

}