package com.ocr.anthony;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Order order = new Order();


    @Test
    public void Given_Chicken_When_DisplayMenuSelected_Then_DisplayChickenSentence() {
        order.displaySelectedMenu(1);
        assertEquals("Vous avez choisi comme menu : poulet\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_Beef_When_DisplayMenuSelected_Then_DisplayBeefSentence() {
        order.displaySelectedMenu(2);
        assertEquals("Vous avez choisi comme menu : boeuf\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_Vegetarian_When_DisplayMenuSelected_Then_DisplayVegetarianSentence() {
        order.displaySelectedMenu(3);
        assertEquals("Vous avez choisi comme menu : végétarien\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_TooBigValue_When_DisplayMenuSelected_Then_DisplayErrorSentence() {
        order.displaySelectedMenu(15);
        assertEquals("Vous n'avez pas choisi de menu parmi les choix proposés\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_NegativeValue_When_DisplayMenuSelected_Then_DisplayErrorSentence() {
        order.displaySelectedMenu(-6);
        assertEquals("Vous n'avez pas choisi de menu parmi les choix proposés\n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_BadResponseAndResponse1_When_AskAboutCarWithThreeResponses_Then_DisplayErrorAndGoodResponse() {
        System.setIn(new ByteArrayInputStream("5\n1\n".getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        order.askSomething("voiture", responses);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals(true, output[0].contains("voiture"));
        assertEquals("Vous n'avez pas choisi de voiture parmi les choix proposés", output[5]);
        assertEquals("Vous avez choisi comme voiture : BMW", output[11]);
    }
    @Test
    public void Given_Chiken_When_AskAboutMenus_Then_DisplayChikenChoice() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        order.askMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : poulet", output[5]);
    }
    @Test
    public void Given_FriesWithAllSidesEnabled_When_AskAboutSides_Then_DisplayFriesChoice() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        order = new Order();
        order.askSide(true);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme accompagnement : frites", output[5]);
    }
    @Test
    public void Given_Water_When_AskAboutDrinks_Then_DisplayWaterChoice() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        order.askDrink();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme boisson : eau plate", output[5]);
    }
    @Test
    public void Given_Response2_When_AskAboutCarWithThreeResponses_Then_ReturnNumber2() {
        System.setIn(new ByteArrayInputStream("5\n2\n".getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        int choice = order.askSomething("voiture", responses);
        assertEquals(2, choice);
    }
    @Test
    public void Given_Chiken_When_AskAboutMenus_Then_Return1() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        int choice = order.askMenu();
        assertEquals(1, choice);
    }
    @Test
    public void Given_Response_When_CallingAskQuestion_Then_FillOrderSummaryCorrectly() {
        System.setIn(new ByteArrayInputStream(String.format("1%n").getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        int choice = order.askSomething("voiture", responses);
        assertEquals("Vous avez choisi comme voiture : BMW%n", order.orderSummary);
    }
    @Test
    public void Given_Responses_When_CallingRunMenus_Then_FillOrderSummaryCorrectly() {
        System.setIn(new ByteArrayInputStream(String.format("2%n1%n1%n1%n2%n2%n").getBytes()));
        order = new Order();
        order.runMenus();
        assertEquals("Résumé de votre commande :%n" +
                "Menu 1:%n" +
                "Vous avez choisi comme menu : poulet%n" +
                "Vous avez choisi comme accompagnement : légumes frais%n" +
                "Vous avez choisi comme boisson : eau plate%n" +
                "Menu 2:%n" +
                "Vous avez choisi comme menu : boeuf%n" +
                "Vous avez choisi comme accompagnement : frites%n" , order.orderSummary);
    }
}