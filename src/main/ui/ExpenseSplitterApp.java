package ui;

import model.Expense;
import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// runs application, allows user to select from adding a new expense, deleting an expense, viewing existing expenses
public class ExpenseSplitterApp {
    static Scanner console = new Scanner(System.in);
    private ExpenseList expenseList;
    private static final String JSON_STORE = "./data/expenseList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates a new Expense Splitter App and runs it
    public ExpenseSplitterApp() {
        expenseList = new ExpenseList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // EFFECTS: asks user what they want to do on the app ex. add, delete, view expenses, or quit
    public void runApp() {
        System.out.println("Enter l to load a previous expense list or n to start new");
        if (console.nextLine().equalsIgnoreCase("l")) {
            loadExpenseList();
        }
        while (true) {
            System.out.println("Enter a to add an expense to split, d to delete an split expense, "
                    + "or v to view your split expense tracker or q to quit: ");
            String choice = console.nextLine();

            if (choice.equalsIgnoreCase("a")) {
                addExpense();
            } else if (choice.equalsIgnoreCase("d")) {
                deleteExpense();
            } else if (choice.equalsIgnoreCase("v")) {
                viewExpense(expenseList);
            } else if (choice.equalsIgnoreCase("q")) {
                System.out.println("Enter s if you want like to save, otherwise enter any other letter");
                if (console.nextLine().equalsIgnoreCase("s")) {
                    saveExpenseList();
                }
                System.out.println("Exiting program");
                break;
            }
        }
    }

    public void addExpense() {
        Expense expense = splitExpense();
        expenseList.addExpense(expense);
        System.out.println("Your expense has been split and added to the expense tracker");
    }

    public void deleteExpense() {
        System.out.println("Please enter the name of the expense you would like to delete");
        String name = console.nextLine();
        if (expenseList.deleteExpense(name)) {
            System.out.println("The expense " + name.toLowerCase() + " has been deleted");
        } else {
            System.out.println("Expense not found!");
        }
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

    private void saveExpenseList() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadExpenseList() {
        try {
            expenseList = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
