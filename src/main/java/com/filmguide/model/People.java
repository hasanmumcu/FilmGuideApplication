package com.filmguide.model;

import java.util.Date;

import com.filmguide.model.credit.MovieCredit;
import com.filmguide.model.credit.TVCredit;

public class People {

    private String name;
    private int id;
    private Date birthday;
    private Date deathday;
    private int gender;
    private String biography;
    private String placeOfBirth;
    private String profileImagePath;
    private MovieCredit tvCredits;
    private TVCredit movieCredits;


    public People() {
    }

    public People(String name, int id, Date birthday, Date deathday, int gender, String biography, String placeOfBirth, String profileImagePath, MovieCredit tvCredits, TVCredit movieCredits) {
        this.name = name;
        this.id = id;
        this.birthday = birthday;
        this.deathday = deathday;
        this.gender = gender;
        this.biography = biography;
        this.placeOfBirth = placeOfBirth;
        this.profileImagePath = profileImagePath;
        this.tvCredits = tvCredits;
        this.movieCredits = movieCredits;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDeathday() {
        return this.deathday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getProfileImagePath() {
        return this.profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public MovieCredit getTvCredits() {
        return this.tvCredits;
    }

    public void setTvCredits(MovieCredit tvCredits) {
        this.tvCredits = tvCredits;
    }

    public TVCredit getMovieCredits() {
        return this.movieCredits;
    }

    public void setMovieCredits(TVCredit movieCredits) {
        this.movieCredits = movieCredits;
    }

    public People name(String name) {
        this.name = name;
        return this;
    }

    public People id(int id) {
        this.id = id;
        return this;
    }

    public People birthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public People deathday(Date deathday) {
        this.deathday = deathday;
        return this;
    }

    public People gender(int gender) {
        this.gender = gender;
        return this;
    }

    public People biography(String biography) {
        this.biography = biography;
        return this;
    }

    public People placeOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    public People profileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
        return this;
    }

    public People tvCredits(MovieCredit tvCredits) {
        this.tvCredits = tvCredits;
        return this;
    }

    public People movieCredits(TVCredit movieCredits) {
        this.movieCredits = movieCredits;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", id='" + getId() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", deathday='" + getDeathday() + "'" +
            ", gender='" + getGender() + "'" +
            ", biography='" + getBiography() + "'" +
            ", placeOfBirth='" + getPlaceOfBirth() + "'" +
            ", profileImagePath='" + getProfileImagePath() + "'" +
            ", tvCredits='" + getTvCredits() + "'" +
            ", movieCredits='" + getMovieCredits() + "'" +
            "}";
    }
}