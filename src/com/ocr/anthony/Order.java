package com.ocr.anthony;

import java.util.Scanner;

public class Order {
    public Scanner sc = new Scanner(System.in);
    public String orderSummary = "";

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
     * Display a a question about menu, get response and display it
     * @return menu number
     */
    public int askMenu() {
        String[] menus = {"poulet", "boeuf", "végétarien"};
        return this.askSomething("menu", menus);

    }

    /**
     * Display a a question about sides, get response and display ii
     * @param allSideEnable enable display for all sides or not
     */
    public void askSide(boolean allSideEnable) {
        if (allSideEnable){
            String[] sides = {"légumes frais", "frites", "riz"};
            this.askSomething("accompagnement", sides);
        }else{
            String[] sides = {"riz", "pas de riz"};
            this.askSomething("accompagnement", sides);
        }

    }

    /**
     * Display a a question about Drinks, get response and display it
     */
    public void askDrink() {
        String[] drinks = {"eau plate", "eau gazeuse", "soda"};
        this.askSomething("boisson", drinks);
    }

    /**
     * Run asking process for a menu
     */
    public void runMenu() {

       int nbMenu = this.askMenu();
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
    }
    public void runMenus() {
        System.out.println("Combien souhaitez vous commander de menu ?");
        this.orderSummary = "Résumé de votre commande :%n";
        int menuQuantity = sc.nextInt();
        for (int i = 0; i<menuQuantity; i++){
            this.orderSummary += "Menu " + (i + 1 + ":%n");
            this.runMenu();
        }
        System.out.println("");
        System.out.println(String.format(this.orderSummary));

    }
    /**
     * Ask question to user and send a reponse
     * @param category is the question
     * @param responses is the possible reponse
     * @return response number
     */

    public int askSomething(String category, String[] responses) {
        int nbResponse;
        boolean responseIsGood;
        do {
            System.out.println("choix " + category);
            for (int i = 1; i <= responses.length; i++){
                System.out.println(i + " - " + responses[i-1]);
            }
            System.out.println("Que souhaitez vous comme " + category + "?");

            nbResponse = sc.nextInt();
            responseIsGood = (nbResponse > 0 && nbResponse <= responses.length);

            if (responseIsGood){
                String choice = "Vous avez choisi comme " +  category + " : " + responses[nbResponse - 1];
                this.orderSummary += choice + "%n";
                System.out.println(choice);
            }else{
                boolean isVowel = "aeiouy".contains(Character.toString(category.charAt(0)));
                if (isVowel) {
                    System.out.println("Vous n'avez pas choisi d'" + category + " parmi les choix proposés");
                }else{
                    System.out.println("Vous n'avez pas choisi de " + category + " parmi les choix proposés");
                }
            }

        }while(!responseIsGood);
        return nbResponse;
    }
}