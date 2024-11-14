/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "Pessoas")
@NamedQueries({
    @NamedQuery(name = "Pessoas.findAll", query = "SELECT p FROM Pessoas p"),
    @NamedQuery(name = "Pessoas.findByIDPessoa", query = "SELECT p FROM Pessoas p WHERE p.iDPessoa = :iDPessoa"),
    @NamedQuery(name = "Pessoas.findByNomePessoa", query = "SELECT p FROM Pessoas p WHERE p.nomePessoa = :nomePessoa"),
    @NamedQuery(name = "Pessoas.findByEmail", query = "SELECT p FROM Pessoas p WHERE p.email = :email"),
    @NamedQuery(name = "Pessoas.findByTelefone", query = "SELECT p FROM Pessoas p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Pessoas.findByLogradouro", query = "SELECT p FROM Pessoas p WHERE p.logradouro = :logradouro"),
    @NamedQuery(name = "Pessoas.findByCidade", query = "SELECT p FROM Pessoas p WHERE p.cidade = :cidade"),
    @NamedQuery(name = "Pessoas.findByEstado", query = "SELECT p FROM Pessoas p WHERE p.estado = :estado")})
public class Pessoas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPessoa")
    private Integer iDPessoa;
    @Basic(optional = false)
    @Column(name = "NomePessoa")
    private String nomePessoa;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "Telefone")
    private String telefone;
    @Basic(optional = false)
    @Column(name = "Logradouro")
    private String logradouro;
    @Basic(optional = false)
    @Column(name = "Cidade")
    private String cidade;
    @Basic(optional = false)
    @Column(name = "Estado")
    private String estado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoas")
    private PessoasJuridicas pessoasJuridicas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoasIDPessoa")
    private Collection<Movimentos> movimentosCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoas")
    private PessoasFisicas pessoasFisicas;

    public Pessoas() {
    }

    public Pessoas(Integer iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    public Pessoas(Integer iDPessoa, String nomePessoa, String email, String telefone, String logradouro, String cidade, String estado) {
        this.iDPessoa = iDPessoa;
        this.nomePessoa = nomePessoa;
        this.email = email;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Integer getIDPessoa() {
        return iDPessoa;
    }

    public void setIDPessoa(Integer iDPessoa) {
        this.iDPessoa = iDPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PessoasJuridicas getPessoasJuridicas() {
        return pessoasJuridicas;
    }

    public void setPessoasJuridicas(PessoasJuridicas pessoasJuridicas) {
        this.pessoasJuridicas = pessoasJuridicas;
    }

    public Collection<Movimentos> getMovimentosCollection() {
        return movimentosCollection;
    }

    public void setMovimentosCollection(Collection<Movimentos> movimentosCollection) {
        this.movimentosCollection = movimentosCollection;
    }

    public PessoasFisicas getPessoasFisicas() {
        return pessoasFisicas;
    }

    public void setPessoasFisicas(PessoasFisicas pessoasFisicas) {
        this.pessoasFisicas = pessoasFisicas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPessoa != null ? iDPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoas)) {
            return false;
        }
        Pessoas other = (Pessoas) object;
        if ((this.iDPessoa == null && other.iDPessoa != null) || (this.iDPessoa != null && !this.iDPessoa.equals(other.iDPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Pessoas[ iDPessoa=" + iDPessoa + " ]";
    }
    
}
