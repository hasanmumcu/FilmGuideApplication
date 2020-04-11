package com.filmguide.model.show;

import java.util.List;

import com.filmguide.model.Company;

public class Movie extends Show{

    private String originalTitle;
    private long revenue;
    private long budget;
    private int runtime;
    private List<Company> productionCompanies;


    public Movie() {
    }

    public Movie(String originalTitle, long revenue, long budget, int runtime, List<Company> productionCompanies) {
        this.originalTitle = originalTitle;
        this.revenue = revenue;
        this.budget = budget;
        this.runtime = runtime;
        this.productionCompanies = productionCompanies;
    }

    public String getOriginalTitle() {
        return this.originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public long getRevenue() {
        return this.revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public long getBudget() {
        return this.budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Company> getProductionCompanies() {
        return this.productionCompanies;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public Movie originalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public Movie revenue(long revenue) {
        this.revenue = revenue;
        return this;
    }

    public Movie budget(long budget) {
        this.budget = budget;
        return this;
    }

    public Movie runtime(int runtime) {
        this.runtime = runtime;
        return this;
    }

    public Movie productionCompanies(List<Company> productionCompanies) {
        this.productionCompanies = productionCompanies;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " originalTitle='" + getOriginalTitle() + "'" +
            ", revenue='" + getRevenue() + "'" +
            ", budget='" + getBudget() + "'" +
            ", runtime='" + getRuntime() + "'" +
            ", productionCompanies='" + getProductionCompanies() + "'" +
            "}";
    }

}