/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "PessoasJuridicas")
@NamedQueries({
    @NamedQuery(name = "PessoasJuridicas.findAll", query = "SELECT p FROM PessoasJuridicas p"),
    @NamedQuery(name = "PessoasJuridicas.findByPessoasIDPessoa", query = "SELECT p FROM PessoasJuridicas p WHERE p.pessoasIDPessoa = :pessoasIDPessoa"),
    @NamedQuery(name = "PessoasJuridicas.findByCnpj", query = "SELECT p FROM PessoasJuridicas p WHERE p.cnpj = :cnpj")})
public class PessoasJuridicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Pessoas_IDPessoa")
    private Integer pessoasIDPessoa;
    @Basic(optional = false)
    @Column(name = "CNPJ")
    private String cnpj;
    @JoinColumn(name = "Pessoas_IDPessoa", referencedColumnName = "IDPessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoas pessoas;

    public PessoasJuridicas() {
    }

    public PessoasJuridicas(Integer pessoasIDPessoa) {
        this.pessoasIDPessoa = pessoasIDPessoa;
    }

    public PessoasJuridicas(Integer pessoasIDPessoa, String cnpj) {
        this.pessoasIDPessoa = pessoasIDPessoa;
        this.cnpj = cnpj;
    }

    public Integer getPessoasIDPessoa() {
        return pessoasIDPessoa;
    }

    public void setPessoasIDPessoa(Integer pessoasIDPessoa) {
        this.pessoasIDPessoa = pessoasIDPessoa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Pessoas getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoas pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoasIDPessoa != null ? pessoasIDPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PessoasJuridicas)) {
            return false;
        }
        PessoasJuridicas other = (PessoasJuridicas) object;
        if ((this.pessoasIDPessoa == null && other.pessoasIDPessoa != null) || (this.pessoasIDPessoa != null && !this.pessoasIDPessoa.equals(other.pessoasIDPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PessoasJuridicas[ pessoasIDPessoa=" + pessoasIDPessoa + " ]";
    }
    
}
