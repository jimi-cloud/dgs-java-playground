package com.jimicloud.dgsjavaplayground.datafetchers;

import com.jimicloud.dgsjavaplayground.models.Show;
import com.netflix.graphql.dgs.DgsComponent;

import java.util.List;

@DgsComponent
public class ShowsDataFetcher {

    List<Show> shows = List.of(
            new Show("1", "Stranger Things", 2016),
            new Show("2", "Ozark", 2017),
            new Show("3", "The Crown", 2016),
            new Show("4", "Belwo Deck", 2012)
    );

    public Show show(Integer id) {
        return shows.stream().filter(show -> show.id().equals(id.toString())).findFirst().orElse(null);
    }

    public List<Show> shows() {
        return shows;
    }
}
