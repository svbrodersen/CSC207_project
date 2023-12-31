package interface_adapters.get_ratings;

import entity.Movie;
import entity.UserRating;
import entity.Watchlist;
import use_case.get_ratings.GetRatingsOutputBoundary;
import use_case.get_ratings.GetRatingsOutputData;

import java.util.List;

public class GetRatingsPresenter implements GetRatingsOutputBoundary {

    private final GetRatingsViewModel getRatingsViewModel;
    public GetRatingsPresenter(GetRatingsViewModel getRatingsViewModel) {
        this.getRatingsViewModel = getRatingsViewModel;
    }

    @Override
    public void prepareGetRatingsView(GetRatingsOutputData getRatingsOutputData) {
        GetRatingsState state = getRatingsViewModel.getState();

        List<UserRating> ratings = getRatingsOutputData.getRatings();
        List<Movie> movieList = getRatingsOutputData.getMovieList();
        Watchlist watchlist = getRatingsOutputData.getWatchlist();

        state.setRatings(ratings);
        state.setMovieList(movieList);
        state.setWatchlist(watchlist);

        getRatingsViewModel.setState(state);
        getRatingsViewModel.firePropertyChanged();
    }
}
