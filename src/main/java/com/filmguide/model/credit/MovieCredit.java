package com.filmguide.model.credit;

import com.filmguide.model.show.Movie;

public class MovieCredit extends Credit{

    private Movie movie;

    public MovieCredit() {
        super();
    }

    public MovieCredit(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieCredit movie(Movie movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " movie='" + getMovie() + "'" +
            " credit=" + super.toString() + 
            "}";
    }
    
}