package com.vasiliyplatonov.topmoviesweb.repository;

import com.vasiliyplatonov.topmoviesweb.entity.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class MovieRepositoryJpa implements MovieRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Movie> getAllByDate(LocalDate topDate) {
        TypedQuery<Movie> query = em.createQuery(
                "select m from Movie m where m.topDate = :topDate",
                Movie.class).setParameter("topDate", topDate);
        return query.getResultList();
    }

    @Override
    public List<Movie> getAll() {
        TypedQuery<Movie> query = em.createQuery(
                "select m from Movie m",
                Movie.class);
        return query.getResultList();
    }

    @Override
    public List<Movie> getFirstNByDate(LocalDate topDate, int n) {
        TypedQuery<Movie> query = em.createQuery(
                "select m from Movie m where m.topDate = :toptDate order by m.position",
                Movie.class)
                .setParameter("toptDate", topDate)
                .setMaxResults(n);
        return query.getResultList();
    }
}
