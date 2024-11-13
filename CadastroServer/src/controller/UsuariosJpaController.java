package controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.EntityNotFoundException;
import model.Usuarios;

/**
 *
 * @author pedro
 */
public class UsuariosJpaController implements Serializable {
    
    private EntityManagerFactory emf = null;

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Usuarios usuario) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void edit(Usuarios usuario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            usuario = em.merge(usuario);
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
            Usuarios usuario;
            try {
                usuario = em.getReference(Usuarios.class, id);
                usuario.getIDUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new Exception("O usuário com o ID " + id + " não existe.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public Usuarios findUsuarioById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Usuarios> findAllUsuarios() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u", Usuarios.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Usuarios findUsuario(String nomeUsuario, String senhaUsuario) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuarios> query = em.createQuery(
                "SELECT u FROM Usuarios u WHERE u.nomeUsuario = :nomeUsuario AND u.senhaUsuario = :senhaUsuario",
                Usuarios.class);
            query.setParameter("nomeUsuario", nomeUsuario);
            query.setParameter("senhaUsuario", senhaUsuario);

            List<Usuarios> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }
}
