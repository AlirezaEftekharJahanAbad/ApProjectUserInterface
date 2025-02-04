package com.lrdluffy.Services;

import com.lrdluffy.Main;
import com.lrdluffy.Models.Movie;
import com.lrdluffy.Utils.PromptBuilder;
import com.lrdluffy.Utils.ResponseToModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Services {

    static final int NUMBER_OF_MOVIES_TO_RECOMMEND = 1;

    public static void requestKnowledgeBase() {
        try {
            // Base URL
            String baseUrl = "http://localhost:8081/getMovies";

            // Query parameters
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

            // Construct the full URL with query parameters
            String fullUrl = String.format("%s?releaseYear=%d&duration=%d&genre=%s&rating=%.1f&metaScore=%d&votes=%d&directorName=%s&cast=%s",
                    baseUrl, releaseYear, duration, encodedGenre, rating, metaScore, votes, encodedDirectorName, encodedCast);


            // Create a URL object
            URL url = new URL(fullUrl);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

//            // Get the response code
//            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            List<Movie> responseList = ResponseToModel.jsonToModelConverter(response.toString());

            processKnowledgeBaseResponse(responseList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processKnowledgeBaseResponse(List<Movie> responseList) {

        if (responseList.size() == 0) {
            System.out.println("Sorry ;( . No Movies Match your preferences!!!");
        }
        else {
            ollamaRequest(responseList);
        }

    }

    private static String ollamaRequest(List<Movie> responseList) {
        String prompt = PromptBuilder.promptBuilder(responseList);
        String model = "llama3.2";

        // Ollama's endpoint (running locally)
        String apiUrl = "http://localhost:11434/api/generate";

        try {
            // Create URL and connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the request properties
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Create the JSON body for the request
            String requestBody = String.format(
                    "{ \"model\": \"%s\", \"prompt\": \"%s\" }",
                    model, prompt
            );

            // Send the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner scanner = new Scanner(connection.getInputStream(), "utf-8")) {
                    StringBuilder response = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        Pattern pattern = Pattern.compile("\"response\":\\s*\"([^\"]+)\"");
                        Matcher matcher = pattern.matcher(scanner.nextLine());

                        if (matcher.find()) {
                            String tempString=matcher.group(1);
                            if (tempString.contains("\\n")){
                                for (int i = 0; i < tempString.length();) {
                                    if (tempString.charAt(i)=='\\' && tempString.charAt(i+1)=='n'){
                                        response.append('\n');
                                        i+=2;
                                    }
                                    else {
                                        i+=1;
                                    }
                                }
                            }else {
                                response.append(tempString);
                            }
                        }
                    }
                    System.out.println(response.toString());
                }
            } else {
                System.err.println("Error: " + connection.getResponseMessage());
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
