package com.ocr.anthony;

import java.util.Scanner;

public class Order {
    public Scanner sc = new Scanner(System.in);
    /**
     * Display all available menus in the restaurant.
     */
    public void displayAvailableMenu() {
        System.out.println("Choix menu");
        System.out.println("1 - Poulet");
        System.out.println("2 - Boeuf");
        System.out.println("3 - Végétarien");
        System.out.println("Que souhaitez-vous comme menu?");
    }
    /**
     * Display all available sides depending on all sides enable or not.
     * All sides = vegetables, frites and rice
     * No all sides = rice or not
     * @param allSideEnable enable display for all side or not
     */
    public void displayAvailableSide(boolean allSideEnable) {
        System.out.println("Choix accompagnement");
        if (allSideEnable) {
            System.out.println("1 - légumes frais");
            System.out.println("2 - frites");
            System.out.println("3 - riz");
        } else {
            System.out.println("1 - riz");
            System.out.println("2 - pas de riz");
        }
        System.out.println("Que souhaitez-vous comme accompagnement ?");
    }
    /**
     * Display all available drinks in the restaurant
     */
    public void displayAvailableDrink() {
        System.out.println("Choix boisson");
        System.out.println("1 - eau plate");
        System.out.println("2 - eau gazeuse");
        System.out.println("3 - soda");
        System.out.println("Que souhaitez-vous comme boisson ?");
    }
    /**
     * Display a selected menu.
     * @param nbMenu The selected menu.
     */
    public void displaySelectedMenu(int nbMenu) {
        switch (nbMenu){
            case 1:
                System.out.println("Vous avez choisi comme menu : poulet");
                break;
            case 2:
                System.out.println("Vous avez choisi comme menu : boeuf");
                break;
            case 3:
                System.out.println("Vous avez choisi comme menu : végétarien");
                break;
            default:
                System.out.println("Vous n'avez pas choisi de menu parmi les choix proposés");
                break;
        }

    }


    /**
     * Display a selected side depending on all sides enable or not.
     * All sides = Veg, fries and rice
     * Not all sides = rice or not
     * @param nbSide the selected side
     * @param AllSidesEnable enable display for all side or not
     */

    public void displaySelectedSide(int nbSide , boolean AllSidesEnable) {
        if (AllSidesEnable) {
            switch (nbSide) {
                case 1:
                    System.out.println("Vous avez choisi comme accompagnement : légumes frais");
                    break;
                case 2:
                    System.out.println("Vous avez choisi comme accompagnement : frites");
                    break;
                case 3:
                    System.out.println("Vous avez choisi comme accompagnement : riz");
                    break;
                default:
                    System.out.println("Vous n'avez pas choisi d'accompagnement parmi les choix proposés");
                    break;
            }
        }else {
            switch (nbSide) {
                case 1:
                    System.out.println("Vous avez choisi comme accompagnement : riz");
                    break;
                case 2:
                    System.out.println("Vous avez choisi comme accompagnement : pas de riz");
                    break;
                default:
                    System.out.println("Vous n'avez pas choisi d'accompagnement parmi les choix proposés");
                    break;

            }
        }
    }

    /**
     * Display a selected drink
     * @param nbDrink the selected drink
     */

    public void displaySelectedDrink(int nbDrink) {
        switch (nbDrink) {
            case 1:
                System.out.println("Vous avez choisi comme boisson : eau plate");
                break;
            case 2:
                System.out.println("Vous avez choisi comme boisson : eau gazeuse");
                break;
            case 3:
                System.out.println("Vous avez choisi comme boisson : soda");
                break;
            default:
                System.out.println("Vous n'avez pas choisi de boisson parmi les choix proposés");
                break;
        }
    }
    /**
     * Run asking process for a menu
     */
    public void runMenu() {

       int nbMenu;
       do{
            this.displayAvailableMenu();
            nbMenu = sc.nextInt();
            this.displaySelectedMenu(nbMenu);
            switch (nbMenu) {
                case 1:
                    askSide(true);
                    askDrink();
                    break;
                case 2:
                    askSide(true);
                    break;
                case 3:
                    askSide(false);
                    askDrink();
                    break;
            }
        }  while (nbMenu <1  || nbMenu > 3);
    }
    public void runMenus() {
        System.out.println("Combien souhaitez vous commander de menu ?");
        int menuQuantity = sc.nextInt();
        for (int i = 0; i<menuQuantity; i++){
            runMenu();
        }
    }

    /**
     * Ask question to user and send a reponse
     * @param category is the question
     * @param responses is the possible reponse
     */
    public void askSomething(String category, String[] responses) {

        int nbResponse;
        boolean responseIsGood;
        do {
            System.out.println("choix " + category);
            for (int i = 1; i <= responses.length; i++){
                System.out.println(i + " - " + responses[i-1]);
            }
            System.out.println("Que souhaitez vous comme " + category + "?");
            nbResponse = sc.nextInt();
            if (nbResponse > 0 && nbResponse <= responses.length){
                responseIsGood = true;
                System.out.println("Vous avez choisi comme " +  category + " : " + responses[nbResponse - 1]);

            }else{
                responseIsGood = false;
                boolean isVowel = "aeiouy".contains(Character.toString(category.charAt(0)));
                if (isVowel) {
                    System.out.println("Vous n'avez pas choisi d'" + category + " parmi les choix proposés");
                }else{
                    System.out.println("Vous n'avez pas choisi de " + category + " parmi les choix proposés");
                }
            }

        }while(!responseIsGood);
    }

    /**
     * Display a a question about menu, get response and display it
     */
    public void askMenu() {
        String[] menus = {"poulet", "boeuf", "végétarien"};
        this.askSomething("menu", menus);
    }

    /**
     * Display a a question about sides, get response and display ii
     * @param allSideEnable enable display for all sides or not
     */
    public void askSide(boolean allSideEnable) {
        if (allSideEnable){
            String[] sides = {"légumes frais", "frites", "riz"};
            askSomething("accompagnement", sides);
        }else{
            String[] sides = {"riz", "pas de riz"};
            askSomething("accompagnement", sides);
        }

    }

    /**
     * Display a a question about Drinks, get response and display it
     */
    public void askDrink() {
        String[] drinks = {"eau plate", "eau gazeuse", "soda"};
        askSomething("boisson", drinks);
    }
}