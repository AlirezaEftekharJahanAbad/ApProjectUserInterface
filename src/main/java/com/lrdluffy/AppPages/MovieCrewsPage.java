package com.lrdluffy.AppPages;


import com.lrdluffy.Main;

public class MovieCrewsPage{

    public static void showPage() {

        int crewOption;

        do {

            Divider();

            System.out.println("1 - Director");
            System.out.println("2 - Casts");
            System.out.println("3 - Exit to Main Menu");

            System.out.print("Select your preference : ");

            crewOption= Main.scanner.nextInt();
            Main.scanner.nextLine();

            switch (crewOption){
                case 1:
                    crewDirector();
                    break;
                case 2:
                    crewCasts();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice. please try again ;)");
            }
        }while (crewOption!=3);
    }

    public static void Divider() {
        System.out.println(Dividers.CREW_PAGE.getDividerString());
    }


    private static void crewDirector(){
        System.out.println(Dividers.CREW_DIRECTOR_PAGE.getDividerString());
        System.out.print("Enter the director name : ");
        Main.directorName=Main.scanner.nextLine();
    }

    private static void crewCasts(){
        System.out.println(Dividers.CREW_CASTS_PAGE.getDividerString());
        System.out.println("Enter your preferred cast (Avoid using extra whitespaces And write it in PascalCase format;)");
        System.out.println("( Valid format : ->Anne Hathaway<- ");
        Main.cast=Main.scanner.nextLine();
    }

}
