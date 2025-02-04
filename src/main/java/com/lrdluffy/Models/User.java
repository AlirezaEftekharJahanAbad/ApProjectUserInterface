package com.lrdluffy.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String userName;
    private String password;
    private Integer minReleaseYear;
    private Integer minDuration;
    private String preferredGenre;
    private Double minImdbRating;
    private Integer minMetaScore;
    private Long minVotesNumber;
    private String preferredDirector;
    private String preferredCast;

    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

}

