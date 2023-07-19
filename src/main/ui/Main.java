package ui;

import model.Expense;
import model.ExpenseList;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        splitExpense();
    }

    public static void splitExpense() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the name of the expense:");
        String name = console.nextLine();
        System.out.println("What was the total cost?");
        int cost = console.nextInt();

        System.out.println("How many people do you want to split it between?");
        int numPeople = console.nextInt();

//        System.out.println("Please enter the names of who it was split between:");
//        boolean quit = false;
//        String statement = "The expense of " + cost + " split between: ";
//        int count = 0;
//
//        while (!quit) {
//            String name = console.nextLine();
//            statement = statement + name;
//            System.out.println("press c to continue or q to quit");
//            count++;
//            if (Objects.equals(console.nextLine(), "q")) {
//                quit = true;
//            }
//        }
        int splitCost = cost / numPeople;
        System.out.println("For the expense: " + name);
        System.out.println("Each person owes: " + splitCost);

        Expense expense = new Expense(cost, splitCost, name);
    }
}

