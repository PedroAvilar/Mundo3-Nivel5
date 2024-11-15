package model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Movimentos;
import model.PessoasFisicas;
import model.PessoasJuridicas;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-11-15T00:13:05", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pessoas.class)
public class Pessoas_ { 

    public static volatile SingularAttribute<Pessoas, PessoasJuridicas> pessoasJuridicas;
    public static volatile CollectionAttribute<Pessoas, Movimentos> movimentosCollection;
    public static volatile SingularAttribute<Pessoas, String> telefone;
    public static volatile SingularAttribute<Pessoas, String> cidade;
    public static volatile SingularAttribute<Pessoas, String> estado;
    public static volatile SingularAttribute<Pessoas, String> logradouro;
    public static volatile SingularAttribute<Pessoas, PessoasFisicas> pessoasFisicas;
    public static volatile SingularAttribute<Pessoas, String> nomePessoa;
    public static volatile SingularAttribute<Pessoas, Integer> iDPessoa;
    public static volatile SingularAttribute<Pessoas, String> email;

}