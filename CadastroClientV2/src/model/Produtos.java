/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "Produtos")
@NamedQueries({
    @NamedQuery(name = "Produtos.findAll", query = "SELECT p FROM Produtos p"),
    @NamedQuery(name = "Produtos.findByIDProduto", query = "SELECT p FROM Produtos p WHERE p.iDProduto = :iDProduto"),
    @NamedQuery(name = "Produtos.findByNomeProduto", query = "SELECT p FROM Produtos p WHERE p.nomeProduto = :nomeProduto"),
    @NamedQuery(name = "Produtos.findByQuantidadeProduto", query = "SELECT p FROM Produtos p WHERE p.quantidadeProduto = :quantidadeProduto"),
    @NamedQuery(name = "Produtos.findByPrecoVendaBase", query = "SELECT p FROM Produtos p WHERE p.precoVendaBase = :precoVendaBase")})
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDProduto")
    private Integer iDProduto;
    @Basic(optional = false)
    @Column(name = "NomeProduto")
    private String nomeProduto;
    @Basic(optional = false)
    @Column(name = "QuantidadeProduto")
    private int quantidadeProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PrecoVendaBase")
    private BigDecimal precoVendaBase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtosIDProduto")
    private Collection<Movimentos> movimentosCollection;

    public Produtos() {
    }

    public Produtos(Integer iDProduto) {
        this.iDProduto = iDProduto;
    }

    public Produtos(Integer iDProduto, String nomeProduto, int quantidadeProduto, BigDecimal precoVendaBase) {
        this.iDProduto = iDProduto;
        this.nomeProduto = nomeProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.precoVendaBase = precoVendaBase;
    }

    public Integer getIDProduto() {
        return iDProduto;
    }

    public void setIDProduto(Integer iDProduto) {
        this.iDProduto = iDProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public BigDecimal getPrecoVendaBase() {
        return precoVendaBase;
    }

    public void setPrecoVendaBase(BigDecimal precoVendaBase) {
        this.precoVendaBase = precoVendaBase;
    }

    public Collection<Movimentos> getMovimentosCollection() {
        return movimentosCollection;
    }

    public void setMovimentosCollection(Collection<Movimentos> movimentosCollection) {
        this.movimentosCollection = movimentosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDProduto != null ? iDProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.iDProduto == null && other.iDProduto != null) || (this.iDProduto != null && !this.iDProduto.equals(other.iDProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Produtos[ iDProduto=" + iDProduto + " ]";
    }
    
}
