package controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Movimentos;
import model.Pessoas;
import model.Produtos;
import model.Usuarios;
import java.util.List;

/**
 *
 * @author pedro
 */
public class MovimentosJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public MovimentosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimentos movimentos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Pessoas pessoas = movimentos.getPessoasIDPessoa();
            if (pessoas != null) {
                pessoas = em.getReference(pessoas.getClass(), pessoas.getIDPessoa());
                movimentos.setPessoasIDPessoa(pessoas);
            }
            
            Produtos produtos = movimentos.getProdutosIDProduto();
            if (produtos != null) {
                produtos = em.getReference(produtos.getClass(), produtos.getIDProduto());
                movimentos.setProdutosIDProduto(produtos);
            }
            
            Usuarios usuarios = movimentos.getUsuariosIDUsuario();
            if (usuarios != null) {
                usuarios = em.getReference(usuarios.getClass(), usuarios.getIDUsuario());
                movimentos.setUsuariosIDUsuario(usuarios);
            }
            
            em.persist(movimentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimentos movimentos) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Movimentos persistentMovimentos = em.find(Movimentos.class, movimentos.getIDMovimento());
            
            Pessoas pessoasOld = persistentMovimentos.getPessoasIDPessoa();
            Pessoas pessoasNew = movimentos.getPessoasIDPessoa();
            if (pessoasNew != null && !pessoasNew.equals(pessoasOld)) {
                pessoasNew = em.getReference(pessoasNew.getClass(), pessoasNew.getIDPessoa());
                movimentos.setPessoasIDPessoa(pessoasNew);
            }
            
            Produtos produtosOld = persistentMovimentos.getProdutosIDProduto();
            Produtos produtosNew = movimentos.getProdutosIDProduto();
            if (produtosNew != null && !produtosNew.equals(produtosOld)) {
                produtosNew = em.getReference(produtosNew.getClass(), produtosNew.getIDProduto());
                movimentos.setProdutosIDProduto(produtosNew);
            }
            
            Usuarios usuariosOld = persistentMovimentos.getUsuariosIDUsuario();
            Usuarios usuariosNew = movimentos.getUsuariosIDUsuario();
            if (usuariosNew != null && !usuariosNew.equals(usuariosOld)) {
                usuariosNew = em.getReference(usuariosNew.getClass(), usuariosNew.getIDUsuario());
                movimentos.setUsuariosIDUsuario(usuariosNew);
            }
            
            movimentos = em.merge(movimentos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (movimentos.getIDMovimento() == null || findMovimentos(movimentos.getIDMovimento()) == null) {
                throw new EntityNotFoundException("O movimento com o ID " + movimentos.getIDMovimento() + " nao existe.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws EntityNotFoundException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimentos movimentos;
            try {
                movimentos = em.getReference(Movimentos.class, id);
                movimentos.getIDMovimento();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("O movimento com o ID " + id + " nao existe.");
            }
            em.remove(movimentos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Movimentos findMovimentos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimentos.class, id);
        } finally {
            em.close();
        }
    }

    public List<Movimentos> findMovimentosEntities() {
        return findMovimentosEntities(true, -1, -1);
    }

    public List<Movimentos> findMovimentosEntities(int maxResults, int firstResult) {
        return findMovimentosEntities(false, maxResults, firstResult);
    }

    private List<Movimentos> findMovimentosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimentos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getMovimentosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimentos> rt = cq.from(Movimentos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
