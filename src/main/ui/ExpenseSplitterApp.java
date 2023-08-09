// The following code is taken from the WorkRoomApp class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java

package ui;

import model.Expense;
import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.util.Scanner;

// runs application, allows user to select from adding a new expense, deleting an expense, viewing existing expenses
public class ExpenseSplitterApp {
    private ExpenseList expenseList;
    private static final String JSON_STORE = "./data/expenseList.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private boolean splashScreenShown = false;
    static Scanner console = new Scanner(System.in);

    // EFFECTS: creates a new Expense Splitter App and runs it
    public ExpenseSplitterApp() {
        expenseList = new ExpenseList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // EFFECTS: shows splashscreen, loads and saves file, lets user use app functions
    public void runApp() {
        showSplashScreen(3000);

        // Wait until the splash screen is closed before proceeding
        while (splashScreenShown) {
            try {
                Thread.sleep(100); // Sleep for a short duration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // if user wants to load previous file
        if (promptLoadData()) {
            loadExpenseList();
        }

        SwingUtilities.invokeLater(() -> {
            ExpenseSplitterGUI ui = new ExpenseSplitterGUI(expenseList, this);
            ui.setVisible(true);

            // prompts user to save data
            if (ui.shouldQuitApplication()) {
                promptSaveData();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds an expense to the list
    public void addExpense() {
        Expense expense = splitExpense();
        expenseList.addExpense(expense);
        System.out.println("Your expense has been split and added to the expense tracker");
    }

    // MODIFIES: this
    // EFFECTS: deletes an expense from the list
    public void deleteExpense(Expense expense) {
        if (expenseList.deleteExpense(expense.getName())) {
            System.out.println("The expense " + expense.getName() + " has been deleted");
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

    // EFFECTS: saves the expense list to file
    public void saveExpenseList() {
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

    // EFFECTS: prompts the user with the option to save data to a file when the application ends
    private void promptSaveData() {
        int response = JOptionPane.showConfirmDialog(null,
                "Do you want to save data to file?", "Save Data", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            saveExpenseList();
        }
    }

    // EFFECTS: prompts the user with the option to load data from a file when the application starts
    private boolean promptLoadData() {
        int response = JOptionPane.showConfirmDialog(null,
                "Do you want to load data from file?", "Load Data", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    // EFFECTS: displays splash screen
    @SuppressWarnings("methodlength")
    private void showSplashScreen(int duration) {
        JWindow splashScreen = new JWindow();
        try {
            InputStream inputStream = ExpenseSplitterApp.class.getResourceAsStream("./expenseSplitterImage.png");
            BufferedImage originalImage = ImageIO.read(inputStream);

            // Scale the original image to the desired size
            Image scaledImage = originalImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(imageIcon); /// displays image
            splashScreen.getContentPane().add(imageLabel, BorderLayout.CENTER); // centers image
            splashScreen.pack(); // resize to desired image
            splashScreen.setLocationRelativeTo(null); // centers splash screen on computer screen
            splashScreen.setVisible(true); // makes splash screen appear on screen

            // Set a timer to close the splash screen after the specified duration
            splashScreenDuration(duration, splashScreen);
        } catch (Exception e) {
            System.out.println("Unable to load splash screen image: " + e.getMessage());
            splashScreenShown = false; // In case of an exception, set the flag false to prevent an infinite loop
        }
    }

    // EFFECTS: the splash screen is shown for some duration of time
    public void splashScreenDuration(int duration, JWindow splashScreen) {
        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            // when duration is over:
            public void actionPerformed(ActionEvent e) {
                splashScreen.dispose(); // closes splash screen
                splashScreenShown = false; // indicate that splash screen is closed
            }
        });
        timer.setRepeats(false); // only run the timer once
        timer.start();
        splashScreenShown = true; // splash screen is shown
    }
}
