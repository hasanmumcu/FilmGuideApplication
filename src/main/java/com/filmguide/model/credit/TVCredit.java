package com.filmguide.model.credit;

import com.filmguide.model.show.TV;

public class TVCredit extends Credit{

    private TV tv;

    public TVCredit() {
    }

    public TVCredit(TV tv) {
        this.tv = tv;
    }

    public TV getTv() {
        return this.tv;
    }

    public void setTv(TV tv) {
        this.tv = tv;
    }

    public TVCredit tv(TV tv) {
        this.tv = tv;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " tv='" + getTv() + "'" +
            ", credit=" + super.toString() +
            "}";
    }

}