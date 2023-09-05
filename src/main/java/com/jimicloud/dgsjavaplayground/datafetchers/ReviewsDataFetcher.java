package com.jimicloud.dgsjavaplayground.datafetchers;

import com.jimicloud.dgsjavaplayground.dataloaders.ReviewsDataLoader;
import com.jimicloud.dgsjavaplayground.models.Review;
import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class ReviewsDataFetcher {
    @Autowired
    ReviewsDataLoader reviewsDataLoader;

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
