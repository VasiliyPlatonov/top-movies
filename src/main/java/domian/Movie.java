package domian;

import java.util.Objects;

public class Movie {
    private String title;
    private String year;
    private float rating;
    private int votes;
    private int position;

    public Movie(String title, String year, float rating, int votes, int position) {
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.votes = votes;
        this.position = position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return position == movie.position &&
                title.equals(movie.title) &&
                year.equals(movie.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                ", votes=" + votes +
                ", position=" + position +
                '}';
    }
}
