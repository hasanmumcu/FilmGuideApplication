package com.filmguide.model.credit;

public abstract class Credit implements ICredit{

    private String creditId;
    private String character;

    public Credit(){
    }

    public Credit(String creditId, String character) {
        this.creditId = creditId;
        this.character = character;
    }
   
    public String getCreditId() {
        return this.creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getCharacter() {
        return this.character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "{" +
            " creditId='" + getCreditId() + "'" +
            ", character='" + getCharacter() + "'" +
            "}";
    }
}