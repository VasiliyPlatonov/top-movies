package com.vasiliyplatonov.topmoviesfatcher.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Column(name = "rating")
    private float rating;

    @Column(name = "votes")
    private int votes;

    @Column(name = "position")
    private int position;

    @Column(name = "receipt_date")
    private LocalDate receiptDate;

    public Movie() {
    }

    public Movie(String title, String year, float rating, int votes, int position) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
        this.position = position;
        this.receiptDate = LocalDate.now();
    }

    public Movie(String title, String year, float rating, int votes, int position, LocalDate receiptDate) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
        this.position = position;
        this.receiptDate = receiptDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                ", votes=" + votes +
                ", position=" + position +
                ", receiptDate=" + receiptDate +
                '}';
    }
}
