package com.jimicloud.dgsjavaplayground.dataloaders;

import com.jimicloud.dgsjavaplayground.models.Review;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsDataLoader(name = "reviews")
public class ReviewsDataLoader implements BatchLoader<String, Review> {
    private static final List<Review> reviews = List.of(
            new Review("1", "1", "Great show", 5),
            new Review("2", "1", "Nice!", 5),
            new Review("3", "2", "Meh...", 3),
            new Review("4", "3", "Uggh!", 2),
            new Review("5", "4", "Wow, my favorite show!", 5)
    );

    @Override
    public CompletableFuture<List<Review>> load(List<String> keys) {
        if (keys != null && !keys.isEmpty()) {
            return CompletableFuture.supplyAsync(() -> reviews.stream()
                    .filter(review -> keys.contains(review.showId()))
                    .toList());
        }
        return CompletableFuture.completedFuture(reviews);
    }
}
