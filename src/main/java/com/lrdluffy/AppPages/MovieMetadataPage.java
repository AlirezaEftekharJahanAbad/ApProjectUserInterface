package com.lrdluffy.AppPages;


import com.lrdluffy.Main;

public class MovieMetadataPage {


    public static void showPage() {

        int movieMetadataOption = 0;


        do {

            Divider();

            System.out.println("1 - Release Year");
            System.out.println("2 - Duration");
            System.out.println("3 - Genre");
            System.out.println("4 - Exit to Main Menu");

            System.out.print("Select your preference : ");

            String input = Main.scanner.nextLine().trim();
            try {
                movieMetadataOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (movieMetadataOption) {
                case 1:
                    movieMetaDataReleaseYear();
                    break;
                case 2:
                    movieMetaDataDuration();
                    break;
                case 3:
                    movieMetaDataGenre();
                    break;
                case 4:
                    System.out.println("Exiting to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. please try again ;)");
            }
        } while (movieMetadataOption != 4);
    }

    public static void Divider() {
        System.out.println(Dividers.METADATA_PAGE.getDividerString());
    }

    private static void movieMetaDataReleaseYear() {
        System.out.println(Dividers.METADATA_RELEASEYEAR_PAGE.getDividerString());
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter the minimum release year : ");
            if (Main.scanner.hasNextInt()) {
                Main.releaseYear = Main.scanner.nextInt();
                Main.scanner.nextLine();
                if (Main.releaseYear >= 1900 && Main.releaseYear <= 2026) {
                    validInput = true;
                    System.out.println("Release year set to " + Main.releaseYear);
                } else {
                    System.out.println("Invalid input VALUE. Please enter a valid Relesae Year!!!");
                }
            } else {
                System.out.println("Invalid input TYPE. Please enter a valid Relesae Year!!!");
                Main.scanner.nextLine();
            }
        }
    }

    private static void movieMetaDataDuration() {
        System.out.println(Dividers.METADATA_DURATION_PAGE.getDividerString());
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Enter the maximum duration (in min) :");
            if (Main.scanner.hasNextInt()) {
                Main.duration = Main.scanner.nextInt();
                Main.scanner.nextLine();
                if (Main.duration >= 0) {
                    validInput = true;
                    System.out.println("Duration set to " + Main.duration + " minutes.");
                } else {
                    System.out.println("Invalid input VALUE. Please enter a valid Duration!!!");
                }
            } else {
                System.out.println("Invalid input TYPE. Please enter a valid Duration!!!");
                Main.scanner.nextLine();
            }
        }
    }

    private static void movieMetaDataGenre() {
        System.out.println(Dividers.METADATA_GENRES_PAGE.getDividerString());
        String genre;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Enter your preferred genres (Avoid using extra whitespaces and write it in PascalCase format;)");
            System.out.println("(Valid format: ->Action<- )");
            genre = Main.scanner.nextLine().trim();

            if (!genre.isEmpty() && genre.equals(genre.substring(0, 1).toUpperCase() + genre.substring(1))) {
                Main.genere = genre;
                validInput = true;
                System.out.println("Genre set to: " + Main.genere);
            } else {
                System.out.println("Invalid format. Please use PascalCase format for the genre.");
            }
        }
    }
}
