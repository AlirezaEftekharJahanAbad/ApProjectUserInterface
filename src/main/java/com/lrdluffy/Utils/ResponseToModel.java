package com.lrdluffy.Utils;

import com.lrdluffy.Models.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseToModel {

    public static List<Model> jsonToModelConverter(String json){

        List<Model> responsesList=new ArrayList<>();

        Pattern pattern=Pattern.compile("\"movieId\":\"(\\d+)\",\"title\":\"([a-zA-Z\\s]+)\",\"releaseYear\":(\\d+),\"duration\":(\\d+),\"genres\":\\[\"(.*?)\"\\],\"rating\":(\\d+.?\\d?),\"metaScore\":(\\d+),\"votes\":(\\d+),\"directorName\":\"([^\"]+)\",\"casts\":\\[\"(.*?)\"\\]");
        Matcher matcher=pattern.matcher(json);


        while (matcher.find()){

            Model responseModel=new Model();
            responseModel.setMovieId(matcher.group(1));
            responseModel.setTitle(matcher.group(2));
            responseModel.setReleaseYear(Integer.valueOf(matcher.group(3)));
            responseModel.setDuration(Integer.valueOf(matcher.group(4)));
            String[] genreArray = matcher.group(5).split("\",\"");
            for (int i = 0; i < genreArray.length; i++) {
                genreArray[i]=genreArray[i].replace("\"","");
            }
            responseModel.setGenres(genreArray);
            responseModel.setRating(Double.valueOf(matcher.group(6)));
            responseModel.setMetaScore(Integer.valueOf(matcher.group(7)));
            responseModel.setVotes(Long.valueOf(matcher.group(8)));
            responseModel.setDirectorName(matcher.group(9));
            String[] castArray = matcher.group(10).split("\",\"");
            for (int i = 0; i < castArray.length; i++) {
                castArray[i]=castArray[i].replace("\"","");
            }
            responseModel.setCasts(castArray);

            responsesList.add(responseModel);
        }

        return responsesList;
    }

}
