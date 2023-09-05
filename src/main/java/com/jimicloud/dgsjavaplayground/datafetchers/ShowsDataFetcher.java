package com.jimicloud.dgsjavaplayground.datafetchers;

import com.jimicloud.dgsjavaplayground.dataloaders.ShowsDataLoader;
import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class ShowsDataFetcher {

    private final ShowsDataLoader showsDataLoader;

    public ShowsDataFetcher(ShowsDataLoader showsDataLoader) {
        this.showsDataLoader = showsDataLoader;
    }

    @DgsQuery
    public Show show(@InputArgument String id) {
        if (id != null) {
            List<Show> shows = showsDataLoader.load(List.of(id))
                    .join();
            if (!shows.isEmpty()) {
                return shows.get(0);
            }
        }
        return null;
    }

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter) {
        if (titleFilter == null) {
            return showsDataLoader.load(null)
                    .join();
        }
        return showsDataLoader.load(null)
                .join()
                .stream()
                .filter(show -> show.title()
                        .contains(titleFilter))
                .toList();
    }
}
