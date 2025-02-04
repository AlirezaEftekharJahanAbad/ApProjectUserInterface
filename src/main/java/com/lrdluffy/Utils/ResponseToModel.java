package com.lrdluffy.Utils;

import com.lrdluffy.Models.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseToModel {

    public static List<Movie> jsonToModelConverter(String json){

        List<Movie> responsesList=new ArrayList<>();

        Pattern pattern=Pattern.compile("\"movieId\":\"(\\d+)\",\"title\":\"([a-zA-Z\\s]+)\",\"releaseYear\":(\\d+),\"duration\":(\\d+),\"genres\":\\[\"(.*?)\"\\],\"rating\":(\\d+.?\\d?),\"metaScore\":(\\d+),\"votes\":(\\d+),\"directorName\":\"([^\"]+)\",\"casts\":\\[\"(.*?)\"\\]");
        Matcher matcher=pattern.matcher(json);


        while (matcher.find()){

            Movie responseMovie =new Movie();
            responseMovie.setMovieId(matcher.group(1));
            responseMovie.setTitle(matcher.group(2));
            responseMovie.setReleaseYear(Integer.valueOf(matcher.group(3)));
            responseMovie.setDuration(Integer.valueOf(matcher.group(4)));
            String[] genreArray = matcher.group(5).split("\",\"");
            for (int i = 0; i < genreArray.length; i++) {
                genreArray[i]=genreArray[i].replace("\"","");
            }
            responseMovie.setGenres(genreArray);
            responseMovie.setRating(Double.valueOf(matcher.group(6)));
            responseMovie.setMetaScore(Integer.valueOf(matcher.group(7)));
            responseMovie.setVotes(Long.valueOf(matcher.group(8)));
            responseMovie.setDirectorName(matcher.group(9));
            String[] castArray = matcher.group(10).split("\",\"");
            for (int i = 0; i < castArray.length; i++) {
                castArray[i]=castArray[i].replace("\"","");
            }
            responseMovie.setCasts(castArray);

            responsesList.add(responseMovie);
        }

        return responsesList;
    }

}
