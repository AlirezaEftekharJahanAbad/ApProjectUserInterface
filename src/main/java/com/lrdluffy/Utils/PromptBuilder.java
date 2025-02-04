package com.lrdluffy.Utils;

import com.lrdluffy.Main;
import com.lrdluffy.Models.Movie;

import java.util.List;

public class PromptBuilder {

    public static String promptBuilder(List<Movie> movieList) {

        try {
            // User preferences
            Integer minReleaseYear = (Main.releaseYear == null) ? 0 : Main.releaseYear;
            Integer minDuration = (Main.duration == null) ? 0 : Main.duration;
            String preferredGenre = (Main.genere == null) ? "" : Main.genere;
            Double minIMDBRating = (Main.imdbRating == null) ? 0 : Main.imdbRating;
            Integer minMetaScore = (Main.metascore == null) ? 0 : Main.metascore;
            Integer minVotesNumber = (Main.votesNumber == null) ? 0 : Math.toIntExact(Main.votesNumber);
            String preferredDirector = (Main.directorName == null) ? "" : Main.directorName;
            String preferredCast = (Main.cast == null) ? "" : Main.cast;

            // Recommended movies
            StringBuilder recommendedMoviesBuilder = new StringBuilder();
            int index = 1;
            for (Movie movie : movieList) {
                recommendedMoviesBuilder.append(index++);
                recommendedMoviesBuilder.append(" -> ");
                recommendedMoviesBuilder.append(movie);
            }

            String recommendedMovies = recommendedMoviesBuilder.toString();

            // Construct the prompt
            String prompt;
            prompt = String.format("You are a movie recommendation filter. Your task is to analyze the following list of recommended movies and return a smaller, refined list based on the user's preferences. Your response will be shown directly to the user, so ensure it is clear, concise, and user-friendly. **User Preferences:** - Minimum Release Year: %d - Minimum Duration: %d minutes - Preferred Genre: %s - Minimum IMDb Rating: %.1f - Minimum MetaScore: %d - Minimum Votes Number: %d - Preferred Director: %s - Preferred Cast : %s **Recommended Movies:** %s **Output Format:** - Title: [Movie Title] - Reason: [Why this movie was selected]", minReleaseYear, minDuration, preferredGenre, minIMDBRating, minMetaScore, minVotesNumber, preferredDirector, preferredCast, recommendedMovies);

            return prompt;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
