package controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Pessoas;
import model.PessoasFisicas;
import model.PessoasJuridicas;
import java.util.List;

/**
 *
 * @author pedro
 */
public class PessoasJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public PessoasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pessoas pessoas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            PessoasJuridicas pessoasJuridicas = pessoas.getPessoasJuridicas();
            if (pessoasJuridicas != null) {
                pessoasJuridicas.setPessoas(pessoas);
                em.persist(pessoasJuridicas);
            }
            
            PessoasFisicas pessoasFisicas = pessoas.getPessoasFisicas();
            if (pessoasFisicas != null) {
                pessoasFisicas.setPessoas(pessoas);
                em.persist(pessoasFisicas);
            }

            em.persist(pessoas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pessoas pessoas) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            pessoas = em.merge(pessoas);

            PessoasJuridicas pessoasJuridicas = pessoas.getPessoasJuridicas();
            if (pessoasJuridicas != null) {
                pessoasJuridicas.setPessoas(pessoas);
                em.merge(pessoasJuridicas);
            }

            PessoasFisicas pessoasFisicas = pessoas.getPessoasFisicas();
            if (pessoasFisicas != null) {
                pessoasFisicas.setPessoas(pessoas);
                em.merge(pessoasFisicas);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (pessoas.getIDPessoa() == null || findPessoas(pessoas.getIDPessoa()) == null) {
                throw new EntityNotFoundException("A entidade com o ID " + pessoas.getIDPessoa() + " nao existe.");
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
            Pessoas pessoas;
            try {
                pessoas = em.getReference(Pessoas.class, id);
                pessoas.getIDPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("A entidade com o ID " + id + " nao existe.");
            }

            PessoasJuridicas pessoasJuridicas = pessoas.getPessoasJuridicas();
            if (pessoasJuridicas != null) {
                em.remove(pessoasJuridicas);
            }

            PessoasFisicas pessoasFisicas = pessoas.getPessoasFisicas();
            if (pessoasFisicas != null) {
                em.remove(pessoasFisicas);
            }

            em.remove(pessoas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Pessoas findPessoas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pessoas.class, id);
        } finally {
            em.close();
        }
    }

    public List<Pessoas> findPessoasEntities() {
        return findPessoasEntities(true, -1, -1);
    }

    public List<Pessoas> findPessoasEntities(int maxResults, int firstResult) {
        return findPessoasEntities(false, maxResults, firstResult);
    }

    private List<Pessoas> findPessoasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pessoas.class));
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

    public int getPessoasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pessoas> rt = cq.from(Pessoas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
