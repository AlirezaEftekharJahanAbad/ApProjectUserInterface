package com.lrdluffy.AppPages;

import com.lrdluffy.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage {

    private static boolean isLogged = false;

    public static void showPage() {

        int option;

        do {

            divider();

            System.out.println("1 - Register");
            System.out.println("2 - Login");
            System.out.println("3 - exit (quit)");
            System.out.print("Choose one of the options above to start : ");

            String input = Main.scanner.nextLine().trim();
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (option) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    isLogged=true;
                    break;
                default:
                    System.out.println("Invalid choice. please try agian ;)");
            }

        } while (!isLogged);

        return;
    }

    private static void divider() {
        System.out.println(Dividers.WELCOME_PAGE.getDividerString());
        return;
    }

    private static void register() {

        System.out.println(Dividers.REGISTER_PAGE.getDividerString());

        String userName = promptForInput("Enter your username: ", true);
        String password = promptForInput("Enter your password: ", true);
        try {
            // Define the URL
            URL requestUrl = new URL("http://localhost:8080/saveUser");
            String queryParams = String.format("userName=%s&password=%s", userName, password);


            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable output (sending data)
            connection.setDoOutput(true);

            // Set request headers (if needed)
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Send the request
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = queryParams.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }


            // Get the response code
            int responseCode = connection.getResponseCode();


            // Read the response body
            StringBuilder responseBody = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            responseCode == HttpURLConnection.HTTP_OK ? connection.getInputStream() : connection.getErrorStream(),
                            StandardCharsets.UTF_8))) {
                String responseLine;
                Pattern pattern = Pattern.compile("\"message\":\"([^\"]+)\"");
                while ((responseLine = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(responseLine);
                    if (matcher.find()) {
                        responseBody.append(matcher.group(1));
                    }
                }
            }


            // Handle the response (optional)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Registration successful! Logging in...");
                Thread.sleep(1000);
                isLogged = true;
                Main.userName = userName;
                Main.password = password;
                IntroPage.showPage();
            } else if (responseCode == HttpURLConnection.HTTP_SERVER_ERROR) {
                System.out.println("Username already exists. Try another username.");
                handleRetry("Do you want to log in instead? (y/n): ", LoginPage::login, LoginPage::register);
            } else {
                System.out.println("Request failed! Please try again later.");
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            System.out.println("An error occurred while registering. Please try again.");

            e.printStackTrace();
        }

    }

    public static void login() {

        System.out.println(Dividers.LOGIN_PAGE.getDividerString());

        String userName = promptForInput("Enter your username: ", true);
        String password = promptForInput("Enter your password: ", true);
        try {
            // Define the URL
            String baseUrl = "http://localhost:8080/getUser";
            String queryParams = String.format("userName=%s&password=%s", userName, password);
            URL fullUrl = new URL(baseUrl + '?' + queryParams);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) fullUrl.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("GET");


            // Get the response code
            int responseCode = connection.getResponseCode();


            // Read the response body
            StringBuilder responseBody = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            responseCode == HttpURLConnection.HTTP_OK ? connection.getInputStream() : connection.getErrorStream(),
                            StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {

                    responseBody.append(responseLine);

                }
            }


            // Handle the response (optional)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Login was successful ;)");
                jsonToUser(responseBody.toString());
                isLogged = true;
                IntroPage.showPage();
            } else if (responseCode == HttpURLConnection.HTTP_SERVER_ERROR) {
                System.out.println("Invalid credentials. Please try again.");
                login();
            } else {
                System.out.println("Request failed! please try again later!!!");
                return;
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            System.out.println("An error occurred while logging in. Please try again.");
            e.printStackTrace();
        }

    }

    private static void jsonToUser(String jsonResponse) {
        Pattern pattern = Pattern.compile("\"userName\":\"?([^\"]+)\"?,\"password\":\"?([^\"]+)\"?,\"minReleaseYear\":(\\d+),\"minDuration\":(\\d+),\"preferredGenre\":\"?([^\"]+)\"?,\"minImdbRating\":(\\d+.?\\d+),\"minMetaScore\":(\\d+),\"minVotesNumber\":(\\d+),\"preferredDirector\":\"?([^\"]+)\"?,\"preferredCast\":\"?([^\"]+)\"?");
        Matcher matcher = pattern.matcher(jsonResponse);

        if (matcher.find()) {
            Main.userName = matcher.group(1).equals("null") ? null : matcher.group(1);
            Main.password = matcher.group(2).equals("null") ? null : matcher.group(2);
            Main.releaseYear = Integer.valueOf(matcher.group(3));
            Main.duration = Integer.valueOf(matcher.group(4));
            Main.genere = matcher.group(5).equals("null") ? null : matcher.group(5);
            Main.imdbRating = Double.valueOf(matcher.group(6));
            Main.metascore = Integer.valueOf(matcher.group(7));
            Main.votesNumber = Long.valueOf(matcher.group(8));
            Main.directorName = matcher.group(9).equals("null") ? null : matcher.group(9);
            Main.cast = matcher.group(10).equals("null") ? null : matcher.group(10);
        }

        return;

    }

    private static String promptForInput(String prompt, boolean noSpaces) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = Main.scanner.nextLine().trim();
            if (noSpaces && input.contains(" ")) {
                System.out.println("Input cannot contain spaces. Try again.");
                continue;
            }
            return input;
        }
    }

    private static void handleRetry(String message, Runnable onYes, Runnable onNo) {
        System.out.print(message);
        String input = Main.scanner.nextLine().trim().toLowerCase();
        if (input.equals("y")) {
            onYes.run();
        } else {
            onNo.run();
        }
    }

}
