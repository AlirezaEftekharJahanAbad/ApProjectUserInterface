package com.lrdluffy.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Model {

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
        return  "Title : " + this.title +
                ", Release Year : " + this.releaseYear +
                ", Duration : " + this.duration +
                ", Genres : [" + String.join(", ",this.genres) +
                "], IMDB Rating : " + this.rating +
                ", MetaScore : " + this.metaScore +
                ", Votes Number: " + this.votes +
                ", Director Name: " + this.directorName +
                ", Casts: [" + String.join(", ",this.casts) + "]";
    }

}
