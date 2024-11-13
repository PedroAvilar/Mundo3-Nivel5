package controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.EntityNotFoundException;
import model.Produtos;

/**
 *
 * @author pedro
 */
public class ProdutosJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public ProdutosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produtos produto) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void edit(Produtos produto) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            produto = em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void destroy(Integer id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Produtos produto;
            try {
                produto = em.getReference(Produtos.class, id);
                produto.getIDProduto();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("O produto com o ID " + id + " não existe.", enfe);
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Produtos findProdutoById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produtos.class, id);
        } finally {
            em.close();
        }
    }

    public List<Produtos> findAllProdutos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Produtos> query = em.createQuery("SELECT p FROM Produtos p", Produtos.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
