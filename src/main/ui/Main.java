package ui;

import model.Expense;
import model.ExpenseList;

import java.util.Scanner;

public class Main {
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        ExpenseList expenseList = new ExpenseList();
        System.out.println("Hello, what would you like to do?");
        System.out.println("Enter a to add an expense to split, d to delete an split expense, "
                + "or v to view your split expense tracker");
        String choice = console.nextLine();

        if (choice.equalsIgnoreCase("a")) {
            Expense expense = splitExpense();
            expenseList.addExpense(expense);
            System.out.println("Your expense has been split and added to the expense tracker");
        } else if (choice.equalsIgnoreCase("d")) {
            System.out.println("Please enter the name of the expense you would like to delete");
            String name = console.nextLine();
            expenseList.deleteExpense(name);
        } else if (choice.equalsIgnoreCase("v")) {
            for (int i = 0; i < expenseList.getSize(); i++) {
                System.out.println("Your expense " + expenseList.getExpense(i).getName() + "had a total of "
                        + expenseList.getExpense(i).getInitExpense() + ". Each person owes $"
                        + expenseList.getExpense(i).getSplitExpense());
            }
        }
    }

    public static Expense splitExpense() {
        System.out.println("Enter the name of the expense:");
        String name = console.nextLine();

        System.out.println("What was the total cost?");
        int cost = console.nextInt();

        System.out.println("How many people do you want to split it between?");
        int numPeople = console.nextInt();

        int splitCost = cost / numPeople;
        System.out.println("For the expense: " + name);
        System.out.println("Each person owes: " + splitCost);

        Expense expense = new Expense(cost, splitCost, name);
        return expense;
    }
}

