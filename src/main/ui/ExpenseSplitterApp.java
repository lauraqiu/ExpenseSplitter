// The following code is taken from the WorkRoomApp class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java

package ui;

import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// runs application, allows user to select from adding a new expense, deleting an expense, viewing existing expenses
public class ExpenseSplitterApp {
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
        SwingUtilities.invokeLater(() -> {
            boolean loadOption = promptLoadData(); // Prompt user to load data at the start

            if (loadOption) {
                loadExpenseList(); // Load data from file
            }

            ExpenseSplitterGUI ui = new ExpenseSplitterGUI(expenseList, this);
            ui.setVisible(true);

            if (ui.shouldQuitApplication()) { // Check if the "Quit" button is clicked
                promptSaveData(); // Prompt user to save data when the application ends
            }
        });
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
}
