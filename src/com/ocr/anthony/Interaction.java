package com.ocr.anthony;

import java.util.Scanner;


public class Interaction {

    /**
     * Ask question to user and send a reponse
     * @param category is the question
     * @param responses is the possible reponse
     */

    public static void askSomething(String category, String[] responses) {
        Scanner sc = new Scanner(System.in);
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
                System.out.println("Vous avez choisi comme " +  category + " : " + responses[nbResponse - 1]);
            }else{
                boolean isVowel = "aeiouy".contains(Character.toString(category.charAt(0)));
                if (isVowel) {
                    System.out.println("Vous n'avez pas choisi d'" + category + " parmi les choix proposés");
                }else{
                    System.out.println("Vous n'avez pas choisi de " + category + " parmi les choix proposés");
                }
            }

        }while(!responseIsGood);
    }
}
