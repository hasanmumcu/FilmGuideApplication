package com.filmguide.model.show;

import java.util.List;

import com.filmguide.model.Genre;

public interface IShow {

    public String getBackdropPath();
    public void setBackdropPath(String backdropPath);
    public int getId();
    public void setId(int id);
    public List<Genre> getGenres();
    public void setGenres(List<Genre> genres);
    public String getPosterPath();
    public void setPosterPath(String posterPath);
    public float getVoteAverage();
    public void setVoteAverage(float voteAverage);
    public String getOverview();
    public void setOverview(String overview);
}