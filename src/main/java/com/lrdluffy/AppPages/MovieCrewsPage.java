package com.lrdluffy.AppPages;


import com.lrdluffy.Main;

public class MovieCrewsPage{

    public static void showPage() {

        int crewOption = 0;

        do {

            Divider();

            System.out.println("1 - Director");
            System.out.println("2 - Casts");
            System.out.println("3 - Exit to Main Menu");

            System.out.print("Select your preference : ");

            // Handle non-integer input gracefully
            String input = Main.scanner.nextLine().trim();
            try {
                crewOption = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (crewOption){
                case 1:
                    crewDirector();
                    break;
                case 2:
                    crewCasts();
                    break;
                case 3:
                    System.out.println("Exiting to Main Menu...");
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
        String directorName = Main.scanner.nextLine().trim();
        while (directorName.isEmpty()) {
            System.out.println("Director name cannot be empty. Please try again.");
            directorName = Main.scanner.nextLine().trim();
        }
        Main.directorName = directorName;
    }

    private static void crewCasts(){
        System.out.println(Dividers.CREW_CASTS_PAGE.getDividerString());
        System.out.println("Enter your preferred cast (Avoid using extra whitespaces And write it in PascalCase format;)");
        System.out.println("( Valid format : ->Anne Hathaway<- ");

        String cast = Main.scanner.nextLine().trim();
        while (cast.isEmpty() || cast.contains(" ")) {
            System.out.println("Invalid input. Cast name cannot contain extra spaces. Please use PascalCase format.");
            cast = Main.scanner.nextLine().trim();
        }
        Main.cast = cast;
    }

}
