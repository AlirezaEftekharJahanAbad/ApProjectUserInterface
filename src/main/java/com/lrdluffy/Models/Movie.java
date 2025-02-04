package com.lrdluffy.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    private String movieId;
    private String title;
    private Integer releaseYear;
    private Integer duration;
    private String[] genres;

    private Double rating;
    private Integer metaScore;
    private Long votes;

    private String directorName;

    private String[] casts;

    @Override
    public String toString(){
        return String.format(
                "Title: %s, Release Year: %d, Duration: %d mins, Genres: %s, IMDB Rating: %.1f, " +
                        "MetaScore: %d, Votes: %d, Director: %s, Casts: %s",
                title != null ? title : "N/A",
                releaseYear != null ? releaseYear : 0,
                duration != null ? duration : 0,
                genres != null ? String.join(", ", genres) : "N/A",
                rating != null ? rating : 0.0,
                metaScore != null ? metaScore : 0,
                votes != null ? votes : 0L,
                directorName != null ? directorName : "N/A",
                casts != null ? String.join(", ", casts) : "N/A"
        );
    }

}
