package com.filmguide.model;

public class Company {

    private int id;
    private String logoPath;
    private String name;
    private String originalCountry;


    public Company() {
    }

    public Company(int id, String logoPath, String name, String originalCountry) {
        this.id = id;
        this.logoPath = logoPath;
        this.name = name;
        this.originalCountry = originalCountry;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalCountry() {
        return this.originalCountry;
    }

    public void setOriginalCountry(String originalCountry) {
        this.originalCountry = originalCountry;
    }

    public Company id(int id) {
        this.id = id;
        return this;
    }

    public Company logoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public Company originalCountry(String originalCountry) {
        this.originalCountry = originalCountry;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", logoPath='" + getLogoPath() + "'" +
            ", name='" + getName() + "'" +
            ", originalCountry='" + getOriginalCountry() + "'" +
            "}";
    }

}