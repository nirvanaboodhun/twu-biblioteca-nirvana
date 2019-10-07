package com.twu.biblioteca;

public class Movie extends Item {
    String director;
    String rating;

    public Movie(String name, String year, String director, String rating) {
        super(name, year);
        this.director = director;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return getName() + " || "
                + getDirector() +  " || "
                + getRating() +  " || "
                + getYear();
    }

}
