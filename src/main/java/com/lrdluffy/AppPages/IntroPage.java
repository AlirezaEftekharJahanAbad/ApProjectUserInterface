package com.lrdluffy.AppPages;

import com.lrdluffy.Main;

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
                    break;
                default:
                    System.out.println("Invalid choice. please try again ;)");
            }
        } while (option < 4);

    }

    private static void Divider() {
        System.out.println(Dividers.MAIN_PAGE.getDividerString());
    }
}
