package com.jimicloud.dgsjavaplayground.repositories;

import com.jimicloud.dgsjavaplayground.models.Review;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;

public class ReviewsMongoRepository implements ReviewsRepository {
    private final MongoClient mongoClient;

    public ReviewsMongoRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public List<Review> findByShowId(String showId) {
        return mongoClient.getDatabase("dgs-java-playground")
                .getCollection("reviews")
                .find(Filters.eq("showId", showId))
                .map(doc -> new Review(
                        doc.getString("id"),
                        doc.getString("showId"),
                        doc.getString("comment"),
                        doc.getInteger("rating")
                ))
                .into(new ArrayList<>());
    }
}
