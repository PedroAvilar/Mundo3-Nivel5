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
import java.util.List;

/**
 *
 * @author pedro
 */
public class PessoasFisicasJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public PessoasFisicasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PessoasFisicas pessoasFisicas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Pessoas pessoas = pessoasFisicas.getPessoas();
            if (pessoas != null) {
                pessoas = em.getReference(pessoas.getClass(), pessoas.getIDPessoa());
                pessoasFisicas.setPessoas(pessoas);
            }

            em.persist(pessoasFisicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PessoasFisicas pessoasFisicas) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            PessoasFisicas persistentPessoasFisicas = em.find(PessoasFisicas.class, pessoasFisicas.getPessoasIDPessoa());
            Pessoas pessoasOld = persistentPessoasFisicas.getPessoas();
            Pessoas pessoasNew = pessoasFisicas.getPessoas();

            if (pessoasNew != null && !pessoasNew.equals(pessoasOld)) {
                pessoasNew = em.getReference(pessoasNew.getClass(), pessoasNew.getIDPessoa());
                pessoasFisicas.setPessoas(pessoasNew);
            }

            pessoasFisicas = em.merge(pessoasFisicas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (pessoasFisicas.getPessoasIDPessoa() == null || findPessoasFisicas(pessoasFisicas.getPessoasIDPessoa()) == null) {
                throw new EntityNotFoundException("A entidade com o ID " + pessoasFisicas.getPessoasIDPessoa() + " nao existe.");
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
            PessoasFisicas pessoasFisicas;
            try {
                pessoasFisicas = em.getReference(PessoasFisicas.class, id);
                pessoasFisicas.getPessoasIDPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("A entidade com o ID " + id + " nao existe.");
            }
            em.remove(pessoasFisicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public PessoasFisicas findPessoasFisicas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PessoasFisicas.class, id);
        } finally {
            em.close();
        }
    }

    public List<PessoasFisicas> findPessoasFisicasEntities() {
        return findPessoasFisicasEntities(true, -1, -1);
    }

    public List<PessoasFisicas> findPessoasFisicasEntities(int maxResults, int firstResult) {
        return findPessoasFisicasEntities(false, maxResults, firstResult);
    }

    private List<PessoasFisicas> findPessoasFisicasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PessoasFisicas.class));
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

    public int getPessoasFisicasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PessoasFisicas> rt = cq.from(PessoasFisicas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
