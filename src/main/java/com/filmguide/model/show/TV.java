package com.filmguide.model.show;

import java.util.Date;

public class TV extends Show{

    private String originalName;
    private Date firstAirDate;
    private Date lastAirDate;
    private int numberofEpisodes;
    private int numberofSeasons;
    private String status;

    public TV() {
    }

    public TV(String originalName, Date firstAirDate, Date lastAirDate, int numberofEpisodes, int numberofSeasons, String status) {
        this.originalName = originalName;
        this.firstAirDate = firstAirDate;
        this.lastAirDate = lastAirDate;
        this.numberofEpisodes = numberofEpisodes;
        this.numberofSeasons = numberofSeasons;
        this.status = status;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Date getFirstAirDate() {
        return this.firstAirDate;
    }

    public void setFirstAirDate(Date firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public Date getLastAirDate() {
        return this.lastAirDate;
    }

    public void setLastAirDate(Date lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public int getNumberofEpisodes() {
        return this.numberofEpisodes;
    }

    public void setNumberofEpisodes(int numberofEpisodes) {
        this.numberofEpisodes = numberofEpisodes;
    }

    public int getNumberofSeasons() {
        return this.numberofSeasons;
    }

    public void setNumberofSeasons(int numberofSeasons) {
        this.numberofSeasons = numberofSeasons;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TV originalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    public TV firstAirDate(Date firstAirDate) {
        this.firstAirDate = firstAirDate;
        return this;
    }

    public TV lastAirDate(Date lastAirDate) {
        this.lastAirDate = lastAirDate;
        return this;
    }

    public TV numberofEpisodes(int numberofEpisodes) {
        this.numberofEpisodes = numberofEpisodes;
        return this;
    }

    public TV numberofSeasons(int numberofSeasons) {
        this.numberofSeasons = numberofSeasons;
        return this;
    }

    public TV status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " originalName='" + getOriginalName() + "'" +
            ", firstAirDate='" + getFirstAirDate() + "'" +
            ", lastAirDate='" + getLastAirDate() + "'" +
            ", numberofEpisodes='" + getNumberofEpisodes() + "'" +
            ", numberofSeasons='" + getNumberofSeasons() + "'" +
            ", status='" + getStatus() + "'" +
            ", show=" + super.toString() + 
            "}";
    }


}