/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "Movimentos")
@NamedQueries({
    @NamedQuery(name = "Movimentos.findAll", query = "SELECT m FROM Movimentos m"),
    @NamedQuery(name = "Movimentos.findByIDMovimento", query = "SELECT m FROM Movimentos m WHERE m.iDMovimento = :iDMovimento"),
    @NamedQuery(name = "Movimentos.findByTipo", query = "SELECT m FROM Movimentos m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Movimentos.findByQuantidadeMovimentado", query = "SELECT m FROM Movimentos m WHERE m.quantidadeMovimentado = :quantidadeMovimentado"),
    @NamedQuery(name = "Movimentos.findByPrecoUnitario", query = "SELECT m FROM Movimentos m WHERE m.precoUnitario = :precoUnitario")})
public class Movimentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMovimento")
    private Integer iDMovimento;
    @Basic(optional = false)
    @Column(name = "Tipo")
    private Character tipo;
    @Basic(optional = false)
    @Column(name = "QuantidadeMovimentado")
    private int quantidadeMovimentado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PrecoUnitario")
    private BigDecimal precoUnitario;
    @JoinColumn(name = "Pessoas_IDPessoa", referencedColumnName = "IDPessoa")
    @ManyToOne(optional = false)
    private Pessoas pessoasIDPessoa;
    @JoinColumn(name = "Produtos_IDProduto", referencedColumnName = "IDProduto")
    @ManyToOne(optional = false)
    private Produtos produtosIDProduto;
    @JoinColumn(name = "Usuarios_IDUsuario", referencedColumnName = "IDUsuario")
    @ManyToOne(optional = false)
    private Usuarios usuariosIDUsuario;

    public Movimentos() {
    }

    public Movimentos(Integer iDMovimento) {
        this.iDMovimento = iDMovimento;
    }

    public Movimentos(Integer iDMovimento, Character tipo, int quantidadeMovimentado, BigDecimal precoUnitario) {
        this.iDMovimento = iDMovimento;
        this.tipo = tipo;
        this.quantidadeMovimentado = quantidadeMovimentado;
        this.precoUnitario = precoUnitario;
    }

    public Integer getIDMovimento() {
        return iDMovimento;
    }

    public void setIDMovimento(Integer iDMovimento) {
        this.iDMovimento = iDMovimento;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public int getQuantidadeMovimentado() {
        return quantidadeMovimentado;
    }

    public void setQuantidadeMovimentado(int quantidadeMovimentado) {
        this.quantidadeMovimentado = quantidadeMovimentado;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Pessoas getPessoasIDPessoa() {
        return pessoasIDPessoa;
    }

    public void setPessoasIDPessoa(Pessoas pessoasIDPessoa) {
        this.pessoasIDPessoa = pessoasIDPessoa;
    }

    public Produtos getProdutosIDProduto() {
        return produtosIDProduto;
    }

    public void setProdutosIDProduto(Produtos produtosIDProduto) {
        this.produtosIDProduto = produtosIDProduto;
    }

    public Usuarios getUsuariosIDUsuario() {
        return usuariosIDUsuario;
    }

    public void setUsuariosIDUsuario(Usuarios usuariosIDUsuario) {
        this.usuariosIDUsuario = usuariosIDUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDMovimento != null ? iDMovimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimentos)) {
            return false;
        }
        Movimentos other = (Movimentos) object;
        if ((this.iDMovimento == null && other.iDMovimento != null) || (this.iDMovimento != null && !this.iDMovimento.equals(other.iDMovimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Movimentos[ iDMovimento=" + iDMovimento + " ]";
    }
    
}
