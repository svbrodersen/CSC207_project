package use_case.add_to_watchlist;

import entity.Movie;

public class AddToWatchlistOutputData {
    private final Movie movie;

    public AddToWatchlistOutputData(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
