/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.rest.devmedia.dao;

import io.github.jass2125.rest.devmedia.entidades.Nota;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author Anderson Souza
 */
public class NotaDao {

    private EntityManager em;

    public NotaDao() {
        this.em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    }

    public List<Nota> listar() {
        try {
            return em.createQuery("SELECT N FROM Nota N").getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public Nota buscaPorId(Long id) {
        try {
            return (Nota) em.createQuery("SELECT N FROM Nota N WHERE N.id = :id").setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addNota(Nota nota) {
        em.getTransaction().begin();
        em.persist(nota);
        em.getTransaction().commit();
    }

    public void editNota(Nota nota) {
        em.getTransaction().begin();
        em.merge(nota);
        em.getTransaction().commit();
    }

    public void deleteNota(Long id) {
        em.getTransaction().begin();
        em.remove(em.find(Nota.class, id));
        em.getTransaction().commit();
    }

    public Nota buscaPorId(int id) {
        Nota nota = null;
        try {
            nota = (Nota) em.createQuery("SELECT N FROM Nota N WHERE N.id = :id").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nota;
    }

    public void editNota(Nota nota, Long id) {
        em.getTransaction().begin();
        em.createQuery("UPDATE Nota N SET N.descricao = :descricao, N.titulo = :titulo WHERE N.id = :id")
                .setParameter("descricao", nota.getDescricao())
                .setParameter("titulo", nota.getTitulo())
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }
}
