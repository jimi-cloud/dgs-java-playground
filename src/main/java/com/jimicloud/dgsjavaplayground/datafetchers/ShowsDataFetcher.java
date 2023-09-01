package com.jimicloud.dgsjavaplayground.datafetchers;

import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class ShowsDataFetcher {

    private static final List<Show> shows = List.of(
            new Show("1", "Stranger Things", 2016),
            new Show("2", "Ozark", 2017),
            new Show("3", "The Crown", 2016),
            new Show("4", "Below Deck", 2012)
    );

    @DgsQuery
    public Show show(@InputArgument String id) {
        return shows.stream().filter(show -> show.id().equals(id)).findFirst().orElse(null);
    }

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter) {
        if (titleFilter == null) {
            return shows;
        }
        return shows.stream().filter(show -> show.title().contains(titleFilter)).toList();
    }
}
