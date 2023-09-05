package com.jimicloud.dgsjavaplayground.dataloaders;

import com.jimicloud.dgsjavaplayground.models.Review;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class ReviewsDataLoader {
    private static final List<Review> reviews = List.of(
            new Review("1", "1", "Great show", 5),
            new Review("2", "1", "Nice!",5),
            new Review("3", "2", "Meh...", 3),
            new Review("4", "3", "Uggh!", 2)
    );

    @DgsDataLoader(name = "reviews")
    public BatchLoader<String, Review> showsBatchLoader = keys -> CompletableFuture.supplyAsync(() -> reviews.stream().filter(review -> keys.contains(review.id())).toList());
}
