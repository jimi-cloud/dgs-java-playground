package com.jimicloud.dgsjavaplayground.models;

public class Review {
    private final String id;
    private final String showId;
    private final String comment;
    private final Integer rating;

    public Review(String id, String showId, String comment, Integer rating) {
        this.id = id;
        this.showId = showId;
        this.comment = comment;
        this.rating = rating;
    }

}
