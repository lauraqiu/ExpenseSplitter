package ui;

import model.Expense;

import java.util.Objects;
import java.util.Scanner;

public class ExpenseSplitter {

    public ExpenseSplitter() {
        splitExpense();
    }

    public void splitExpense() {
        Scanner console = new Scanner(System.in);
        System.out.println("What was the total cost?");
        int cost = console.nextInt();

        System.out.println("Please enter the names of who it was split between:");
        boolean quit = false;
        String statement = "The expense of " + cost + " split between: ";
        int count = 0;

        while (!quit) {
            String name = console.nextLine();
            statement = statement + name;
            System.out.println("press c to continue or q to quit");
            count++;
            if (Objects.equals(console.nextLine(), "q")) {
                quit = true;
            }
        }
        int splitCost = cost / count;
        System.out.println(statement);
        System.out.println("Each person owes: " + splitCost);

        Expense expense = new Expense(cost, splitCost);
    }
}
