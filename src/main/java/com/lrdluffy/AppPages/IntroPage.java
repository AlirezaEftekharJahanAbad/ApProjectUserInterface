package com.lrdluffy.AppPages;

import com.lrdluffy.Main;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class IntroPage {

    public static void showPage() {

        int option;
        do {

            Divider();

            System.out.println("1 - Movie MetaData");
            System.out.println("2 - Ratings");
            System.out.println("3 - Crew");
            System.out.println("4 - Get Recommendation");
            System.out.println("5 - exit (quit)");

            System.out.print("Select Your preference : ");

            option = Main.scanner.nextInt();

            switch (option) {
                case 1:
                    MovieMetadataPage.showPage();
                    break;
                case 2:
                    MovieRatingsPage.showPage();
                    break;
                case 3:
                    MovieCrewsPage.showPage();
                    break;
                case 4:
                    Main.needsRecommendation=true;
                    break;
                case 5:
                    updateUser();
                    break;
                default:
                    System.out.println("Invalid choice. please try again ;)");
            }
        } while (option < 4);

    }

    private static void Divider() {
        System.out.println(Dividers.MAIN_PAGE.getDividerString());
    }

    public static void updateUser(){
        try {
            // Base URL
            String baseUrl = "http://localhost:8080/updateUser";


            // Query parameters
            String userName=Main.userName;
            String password=Main.password;
            Integer releaseYear = (Main.releaseYear == null) ? 0 : Main.releaseYear;
            Integer duration = (Main.duration == null) ? 0 : Main.duration;
            String genre = (Main.genere == null) ? "" : Main.genere;
            Double rating = (Main.imdbRating == null) ? 0 : Main.imdbRating;
            Integer metaScore = (Main.metascore == null) ? 0 : Main.metascore;
            Integer votes = (Main.votesNumber == null) ? 0 : Math.toIntExact(Main.votesNumber);
            String directorName = (Main.directorName == null) ? "" : Main.directorName;
            String cast = (Main.cast == null) ? "" : Main.cast;


            // Construct the full URL with query parameters
            // URL-encode the parameters
            String encodedGenre = URLEncoder.encode(genre, StandardCharsets.UTF_8.toString());
            String encodedDirectorName = URLEncoder.encode(directorName, StandardCharsets.UTF_8.toString());
            String encodedCast = URLEncoder.encode(cast, StandardCharsets.UTF_8.toString());


            // Create a URL object
            URL url = new URL(baseUrl);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to Post
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);


            // Construct the query parameters
            String queryParams=String.format("userName=%s&password=%s&releaseYear=%d&duration=%d&genre=%s&rating=%.1f&metaScore=%d&votes=%d&directorName=%s&cast=%s",
                    userName,password, releaseYear, duration, encodedGenre, rating, metaScore, votes, encodedDirectorName, encodedCast);

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = queryParams.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }


            // Get the response code
            int responseCode = connection.getResponseCode();

            System.out.println("It was an honor to serve you ;). Please come back later");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
