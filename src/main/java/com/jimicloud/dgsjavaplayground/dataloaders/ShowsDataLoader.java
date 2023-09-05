package com.jimicloud.dgsjavaplayground.dataloaders;

import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsDataLoader(name = "shows")
public class ShowsDataLoader implements BatchLoader<String, Show> {
    private static final List<Show> shows = List.of(
            new Show("1", "Stranger Things", 2016, null),
            new Show("2", "Ozark", 2017, null),
            new Show("3", "The Crown", 2016, null),
            new Show("4", "Below Deck", 2012, null)
    );

    @Override
    public CompletableFuture<List<Show>> load(List<String> keys) {
        if (keys != null && !keys.isEmpty()) {
            return CompletableFuture.supplyAsync(() -> shows.stream()
                    .filter(review -> keys.contains(review.id()))
                    .toList());
        }
        return CompletableFuture.completedFuture(shows);
    }
}
