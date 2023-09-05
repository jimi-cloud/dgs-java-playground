package com.jimicloud.dgsjavaplayground.dataloaders;

import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class ShowsDataLoader {
    private static final List<Show> shows = List.of(
            new Show("1", "Stranger Things", 2016),
            new Show("2", "Ozark", 2017),
            new Show("3", "The Crown", 2016),
            new Show("4", "Below Deck", 2012)
    );

    @DgsDataLoader(name = "shows")
    public BatchLoader<String, Show> showsBatchLoader = keys -> CompletableFuture.supplyAsync(() -> shows.stream().filter(show -> keys.contains(show.id())).toList());
}
