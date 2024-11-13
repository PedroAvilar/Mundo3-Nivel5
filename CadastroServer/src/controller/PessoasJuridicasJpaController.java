package controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.PessoasJuridicas;
import java.util.List;

/**
 *
 * @author pedro
 */
public class PessoasJuridicasJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public PessoasJuridicasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PessoasJuridicas pessoasJuridicas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pessoasJuridicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PessoasJuridicas pessoasJuridicas) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pessoasJuridicas = em.merge(pessoasJuridicas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (pessoasJuridicas.getPessoasIDPessoa() == null || findPessoasJuridicas(pessoasJuridicas.getPessoasIDPessoa()) == null) {
                throw new EntityNotFoundException("A entidade com o ID " + pessoasJuridicas.getPessoasIDPessoa() + " nao existe.");
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
            PessoasJuridicas pessoasJuridicas;
            try {
                pessoasJuridicas = em.getReference(PessoasJuridicas.class, id);
                pessoasJuridicas.getPessoasIDPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("A entidade com o ID " + id + " nao existe.");
            }
            em.remove(pessoasJuridicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public PessoasJuridicas findPessoasJuridicas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PessoasJuridicas.class, id);
        } finally {
            em.close();
        }
    }

    public List<PessoasJuridicas> findPessoasJuridicasEntities() {
        return findPessoasJuridicasEntities(true, -1, -1);
    }

    public List<PessoasJuridicas> findPessoasJuridicasEntities(int maxResults, int firstResult) {
        return findPessoasJuridicasEntities(false, maxResults, firstResult);
    }

    private List<PessoasJuridicas> findPessoasJuridicasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PessoasJuridicas.class));
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

    public int getPessoasJuridicasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PessoasJuridicas> rt = cq.from(PessoasJuridicas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
