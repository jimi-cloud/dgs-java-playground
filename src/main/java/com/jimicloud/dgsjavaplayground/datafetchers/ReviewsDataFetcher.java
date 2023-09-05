package com.jimicloud.dgsjavaplayground.datafetchers;

import com.jimicloud.dgsjavaplayground.dataloaders.ReviewsDataLoader;
import com.jimicloud.dgsjavaplayground.models.Review;
import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;

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
    public List<Review> reviews() {
        return reviewsDataLoader.load(null).join();
    }
}
