package ciolty.VideoDBImplementation.actions;

import ciolty.VideoDBImplementation.entities.MovieData;
import ciolty.VideoDBImplementation.entities.UserData;
import ciolty.database.Filter;

import java.util.List;

public interface MovieAction extends VideoDBActionInterface {
    /**
     * @param filter
     * @return List of movies with filter
     */
    default List<MovieData> getAllMoviesWithFilter(Filter filter) {
        List<MovieData> movies = getUnitOfWork().getMovieRepository().find(filter);
        return movies;
    }

    /**
     * @param userData
     * @return List of all the movies that haven't been watched by user
     */
    default List<MovieData> getUnwatchedMovies(final UserData userData) {
        /*
        List<MovieData> unwatchedMovies = getUnitOfWork().getMovieRepository().find(new Filter() {
            @Override
            public boolean isValid(final Object object) {
                MovieData movieData = (MovieData) object;
                if (userData.getHistory().containsKey(movieData.getTitle())) {
                    LOGGER.log(Level.WARNING, "User " + userData.getUsername()
                            + " watched movie " + movieData.getTitle());
                    return false;
                }
                LOGGER.log(Level.WARNING, "User " + userData.getUsername()
                        + " didn't watch movie " + movieData.getTitle());
                return true;
            }
        });*/
        List<MovieData> unwatchedMovies = getUnitOfWork().getMovieRepository().find(new Filter() {
            @Override
            public boolean isValid(final Object object) {
                return !userData.getHistory().containsKey(((MovieData) object).getTitle());
            }
        });
        return unwatchedMovies;
    }

    /**
     * @param userData
     * @param genre
     * @return List of all movies not seen by user from genre
     */
    default List<MovieData> getUnwatchedMoviesOfGenre(final UserData userData,
                                                      final String genre) {
        List<MovieData> unwatchedMovies = getUnitOfWork().getMovieRepository().find(new Filter() {
            @Override
            public boolean isValid(final Object object) {
                return (!userData.getHistory().containsKey(((MovieData) object).getTitle())
                        && ((MovieData) object).getGenres().contains(genre));
            }
        });
        return unwatchedMovies;
    }
}
