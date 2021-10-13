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
     */
    public void askMenu() {
        String[] menus = {"poulet", "boeuf", "végétarien"};
        Interaction.askSomething("menu", menus);
    }

    /**
     * Display a a question about sides, get response and display ii
     * @param allSideEnable enable display for all sides or not
     */
    public void askSide(boolean allSideEnable) {
        if (allSideEnable){
            String[] sides = {"légumes frais", "frites", "riz"};
            Interaction.askSomething("accompagnement", sides);
        }else{
            String[] sides = {"riz", "pas de riz"};
            Interaction.askSomething("accompagnement", sides);
        }

    }

    /**
     * Display a a question about Drinks, get response and display it
     */
    public void askDrink() {
        String[] drinks = {"eau plate", "eau gazeuse", "soda"};
        Interaction.askSomething("boisson", drinks);
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
}