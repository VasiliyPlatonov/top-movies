package com.vasiliyplatonov.topmovies.repository;

import com.vasiliyplatonov.topmovies.domain.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class MovieRepositoryJpa implements MovieRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Movie m) {
        em.persist(m);
    }

    @Override
    public void insertAll(List<Movie> movies) {
        for (Movie m : movies) {
            em.persist(m);
        }
    }

    @Override
    public Movie getById(long id) {
        return em.find(Movie.class, id);
    }

    @Override
    public List<Movie> getAllByDate(LocalDate date) {
        TypedQuery<Movie> query = em.createQuery(
                "select m from Movie m where m.receiptDate = :receiptDate",
                Movie.class).setParameter("receiptDate", date);
        return query.getResultList();
    }

    @Override
    public List<Movie> getAll() {
        TypedQuery<Movie> query = em.createQuery(
                "select m from Movie m",
                Movie.class);
        return query.getResultList();
    }
}
