package com.lrdluffy;

import com.lrdluffy.AppPages.IntroPage;
import com.lrdluffy.Services.Services;

import java.util.Scanner;

public class Main {

    public static String userName;
    public static String title;
    public static Integer releaseYear;
    public static Integer duration;
    public static String genere;
    public static Double imdbRating;
    public static Integer metascore;
    public static Long votesNumber;
    public static String directorName;
    public static String cast;
    public static boolean needsRecommendation = false;
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        IntroPage.showPage();

        if (needsRecommendation){
            Services.requestKnowledgeBase();
        }


    }
}