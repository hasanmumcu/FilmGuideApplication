package com.filmguide.model.show;

import java.util.List;

import com.filmguide.model.Genre;

public abstract class Show {

    private String backdropPath;
    private int id; 
    private List<Genre> genres;
    private String posterPath;
    private float voteAverage;
    private String overview;

    public Show() {
    }

    public Show(String backdropPath, int id, List<Genre> genres, String posterPath, float voteAverage, String overview) {
        this.backdropPath = backdropPath;
        this.id = id;
        this.genres = genres;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public String getBackdropPath() {
        return this.backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public float getVoteAverage() {
        return this.voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Show backdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public Show id(int id) {
        this.id = id;
        return this;
    }

    public Show genres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public Show posterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public Show voteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public Show overview(String overview) {
        this.overview = overview;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " backdropPath='" + getBackdropPath() + "'" +
            ", id='" + getId() + "'" +
            ", genres='" + getGenres() + "'" +
            ", posterPath='" + getPosterPath() + "'" +
            ", voteAverage='" + getVoteAverage() + "'" +
            ", overview='" + getOverview() + "'" +
            "}";
    }
}
