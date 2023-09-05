package com.jimicloud.dgsjavaplayground.datafetchers;

import com.jimicloud.dgsjavaplayground.models.Review;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import graphql.schema.DataFetchingEnvironment;
import org.dataloader.DataLoader;

import java.util.concurrent.CompletableFuture;

@DgsComponent
public class ReviewsDataFetcher {
    @DgsData(parentType = "Show", field = "reviews")
    public CompletableFuture<Review> review(DataFetchingEnvironment dfe) {
        DataLoader<String, Review> dataLoader = dfe.getDataLoader("reviews");
        return dataLoader.load(dfe.getArgument("id"));
    }
}
