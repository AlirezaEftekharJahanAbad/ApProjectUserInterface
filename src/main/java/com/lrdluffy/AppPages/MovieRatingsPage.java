package com.lrdluffy.AppPages;


import com.lrdluffy.Main;

public class MovieRatingsPage {


    public static void showPage() {
        int ratingOption;
        do {

            Divider();

            System.out.println("1 - Imdb Rating");
            System.out.println("2 - Metascore");
            System.out.println("3 - Votes Number");
            System.out.println("4 - Exit to Main Menu");

            System.out.print("Select your preference : ");

            ratingOption = Main.scanner.nextInt();


            switch (ratingOption) {
                case 1:
                    ratingsImdbRating();
                    break;
                case 2:
                    ratingsMetascore();
                    break;
                case 3:
                    ratingsVotesNumber();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. please try again ;)");
            }
        } while (ratingOption != 4);
    }



    public static void Divider() {
        System.out.println(Dividers.RATINGS_PAGE.getDividerString());
    }

    private static void ratingsImdbRating() {
        System.out.println(Dividers.RATINGS_IMDB_PAGE.getDividerString());
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter the minimum imdb rating : ");
            if (Main.scanner.hasNextDouble()) {
                Main.imdbRating = Main.scanner.nextDouble();
                Main.scanner.nextLine();
                if (Main.imdbRating >= 0 && Main.imdbRating <= 10) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input VALUE. Please enter a valid IMDB Rating!!!");
                }
            } else {
                System.out.println("Invalid input TYPE. Please enter a valid IMDB Rating!!!");
                Main.scanner.nextLine();
            }
        }
    }

    private static void ratingsMetascore() {
        System.out.println(Dividers.RATINGS_METASCORE_PAGE.getDividerString());
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter the minimum metascore : ");
            if (Main.scanner.hasNextInt()) {
                Main.metascore = Main.scanner.nextInt();
                Main.scanner.nextLine();
                if (Main.metascore >= 0 && Main.metascore <= 100) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input VALUE. Please enter a valid Metascore!!!");
                }
            } else {
                System.out.println("Invalid input TYPE. Please enter a valid Metascore!!!");
                Main.scanner.nextLine();
            }
        }
    }

    private static void ratingsVotesNumber() {
        System.out.println(Dividers.RATINGS_VOTESNUMBER_PAGE.getDividerString());;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter the minimum Votes Number : ");
            if (Main.scanner.hasNextInt()) {
                Main.votesNumber = Main.scanner.nextLong();
                Main.scanner.nextLine();
                if (Main.votesNumber >= 0) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input VALUE. Please enter a valid Votes Number!!!");
                }
            } else {
                System.out.println("Invalid input TYPE. Please enter a valid Votes Number!!!");
                Main.scanner.nextLine();
            }
        }
    }

}
