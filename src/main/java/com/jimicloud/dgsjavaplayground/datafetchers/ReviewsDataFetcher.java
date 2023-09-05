package com.jimicloud.dgsjavaplayground.datafetchers;

import com.jimicloud.dgsjavaplayground.dataloaders.ReviewsDataLoader;
import com.jimicloud.dgsjavaplayground.models.Review;
import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class ReviewsDataFetcher {
    final ReviewsDataLoader reviewsDataLoader;

    public ReviewsDataFetcher(ReviewsDataLoader reviewsDataLoader) {
        this.reviewsDataLoader = reviewsDataLoader;
    }

    @DgsData(parentType = "Show")
    public CompletableFuture<List<Review>> reviews(DgsDataFetchingEnvironment dfe) {
        Show source = dfe.getSource();
        return reviewsDataLoader.load(List.of(source.id()));
    }

    @DgsQuery
    public List<Review> reviews(@InputArgument String showId) {
        return reviewsDataLoader.load(List.of(showId))
                .join();
    }
}
