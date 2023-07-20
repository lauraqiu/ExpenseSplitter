package ui;

import model.Expense;
import model.ExpenseList;

import java.util.Scanner;

// runs application, allows user to select from adding a new expense, deleting an expense, viewing existing expenses
public class ExpenseSplitterApp {
    static Scanner console = new Scanner(System.in);

    public ExpenseSplitterApp() {
        runApp();
    }

    public void runApp() {
        ExpenseList expenseList = new ExpenseList();
        System.out.println("Hello, what would you like to do?");

        while (true) {
            System.out.println("Enter a to add an expense to split, d to delete an split expense, "
                    + "or v to view your split expense tracker or q to quit: ");
            String choice = console.nextLine();

            if (choice.equalsIgnoreCase("a")) {
                Expense expense = splitExpense();
                expenseList.addExpense(expense);
                System.out.println("Your expense has been split and added to the expense tracker");
            } else if (choice.equalsIgnoreCase("d")) {
                System.out.println("Please enter the name of the expense you would like to delete");
                String name = console.nextLine();
                expenseList.deleteExpense(name);
                System.out.println("The expense " + name + " has been deleted");
            } else if (choice.equalsIgnoreCase("v")) {
                viewExpense(expenseList);
            } else if (choice.equalsIgnoreCase("q")) {
                System.out.println("Exiting program");
                break;
            }
        }
    }

    // REQUIRES: console inputs are > 0 or valid strings for name
    // EFFECTS: returns an expense object and prints the expense name and how much each person owes
    public static Expense splitExpense() {
        System.out.println("Enter the name of the expense:");
        String name = console.nextLine();

        System.out.println("What was the total cost?");
        double cost = console.nextDouble();
        console.nextLine();

        System.out.println("How many people do you want to split it between?");
        int numPeople = console.nextInt();
        console.nextLine();

        Expense expense = new Expense(cost, numPeople, name);
        System.out.println("Expense name: " + name);
        System.out.println("Each person owes: $" + expense.splitCost(cost, numPeople));

        return expense;
    }

    // EFFECTS: prints statements that tell the user information about each expense on the list of expenses
    public static void viewExpense(ExpenseList expenseList) {
        System.out.println("You have added " + expenseList.getSize() + " expense(s)");
        for (int i = 0; i < expenseList.getSize(); i++) {
            Expense expense = expenseList.getExpense(i);
            String name = expense.getName();
            double totalCost = expense.getTotalCost();
            int numPeople = expense.getNumPeople();
            double splitCost = expense.splitCost(totalCost, numPeople);
            System.out.println("Your expense " + name + " had a total of $" + totalCost
                    + " split between " + numPeople + " people. Each person owes $" + splitCost);
        }
    }
}
