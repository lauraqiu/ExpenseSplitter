package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseSplitterGUI extends JFrame {
    private final ExpenseList expenseList;
    private final ExpenseSplitterApp app;

    private final JPanel inputPanel;
    private final JPanel outputPanel;
    private final JPanel buttonPanel;

    private final JTextField nameField;
    private final JTextField costField;
    private final JTextField numPeopleField;

    private Expense pinnedExpense = null;
    private boolean quitApplication = false;

    // MODIFIES: expenseList, app
    // EFFECTS: creates main panel, allowing user to add an expense, view expenses, or quit
    public ExpenseSplitterGUI(ExpenseList expenseList, ExpenseSplitterApp app) {
        this.expenseList = expenseList;
        this.app = app;

        inputPanel = new JPanel();
        outputPanel = new JPanel();
        buttonPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name of the Expense:");
        JLabel costLabel = new JLabel("Total Cost:");
        JLabel numPeopleLabel = new JLabel("Number of People:");

        nameField = new JTextField(20);
        costField = new JTextField(10);
        numPeopleField = new JTextField(5);

        JButton addButton = new JButton("Add Expense");
        JButton viewButton = new JButton("View Expenses");
        JButton quitButton = new JButton("Quit");

        actionListeners(addButton);
        actionListeners(viewButton);
        actionListeners(quitButton);

        setUpHomePanel(nameLabel, costLabel, numPeopleLabel, addButton, viewButton, quitButton);
    }

    // EFFECTS: handle user actions when button is clicked
    public void actionListeners(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText().equals("Add Expense")) {
                    addExpense();
                } else if (button.getText().equals("View Expenses")) {
                    viewExpenses();
                } else if (button.getText().equals("Quit")) {
                    quitApplication();
                }
            }
        });
    }

    // EFFECTS: sets up the layout of the main panel of the app
    public void setUpHomePanel(JLabel nameLabel, JLabel costLabel, JLabel numPeopleLabel, JButton addButton,
                               JButton viewButton, JButton quitButton) {
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(costLabel);
        inputPanel.add(costField);
        inputPanel.add(numPeopleLabel);
        inputPanel.add(numPeopleField);

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(quitButton);

        setTitle("Expense Splitter App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

    // MODIFIES: expenseList
    // EFFECTS: adds an expense to the list of expenses
    public void addExpense() {
        String name = nameField.getText();
        double cost = Double.parseDouble(costField.getText());
        int numPeople = Integer.parseInt(numPeopleField.getText());

        Expense expense = new Expense(cost, numPeople, name);
        expenseList.addExpense(expense);
        JOptionPane.showMessageDialog(this, "Your expense has been split and added to "
                        + "the expense tracker", "Expense Added", JOptionPane.INFORMATION_MESSAGE);
        clearInputFields();
    }

    // EFFECTS: allows user to view their list of expenses
    @SuppressWarnings("methodlength")
    public void viewExpenses() {
        while (true) {
            // create an array to store the names and detailed information of expenses
            String[] expenseNames = new String[expenseList.getSize()];
            StringBuilder[] detailedInfo = new StringBuilder[expenseList.getSize()];

            // loops through expense list and gets each expense
            expenseInformation(expenseNames, detailedInfo);

            ExpenseListCellRenderer cellRenderer = new ExpenseListCellRenderer(expenseList, pinnedExpense);

            // Create a JList with the custom cell renderer
            JList<String> expenseListJList = new JList<>(expenseNames);
            expenseListJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Set the custom cell renderer
            expenseListJList.setCellRenderer(cellRenderer);

            // create a panel that allows user to scroll through expense names
            JScrollPane scrollPane = new JScrollPane(expenseListJList);

            // user can select an expense
            // user can click "Ok" or "Cancel"
            int choice = JOptionPane.showConfirmDialog(this, scrollPane,
                    "Select an expense to view:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // if user clicks ok (selects an expense):
            if (choice == JOptionPane.OK_OPTION) {
                // get the index of the selected expense in the list
                int selectedIndex = expenseListJList.getSelectedIndex();
                // handle selected expense and passes the index, name, and detailed info in
                if (selectedIndex != -1) {
                    String selectedExpenseName = expenseNames[selectedIndex];
                    StringBuilder selectedExpenseInfo = detailedInfo[selectedIndex];
                    handleSelectedExpense(selectedIndex, selectedExpenseName, selectedExpenseInfo);
                }
            } else {
                // Exit the loop if the user clicks "Cancel" or closes the window
                break;
            }
        }
    }

    // MODIFIES: expenseNames, detailedInfo
    // EFFECTS: creates a list of expense names and strings with expense information
    private void expenseInformation(String[] expenseNames, StringBuilder[] detailedInfo) {
        // loops through expense list and gets each expense
        for (int i = 0; i < expenseList.getSize(); i++) {
            Expense expense = expenseList.getExpense(i);
            expenseNames[i] = expense.getName();    // populate expenseNames with expense names

            double totalCost = expense.getTotalCost();
            int numPeople = expense.getNumPeople();
            double splitCost = expense.splitCost(totalCost, numPeople);
            detailedInfo[i] = new StringBuilder();
            // for each expense, create detailed information string and add to detailedInfo
            detailedInfo[i].append("Expense: ").append(expense.getName()).append("\n")
                    .append("Total Cost: $").append(totalCost).append("\n")
                    .append("Number of People: ").append(numPeople).append("\n")
                    .append("Split Cost: $").append(splitCost).append("\n");
        }
    }

    // EFFECTS: allows user to view detailed information about an expense and pin or delete it
    private void handleSelectedExpense(int selectedIndex, String selectedExpenseName, StringBuilder detailedInfo) {
        int option;
        String[] options = {"Pin", "Delete", "Cancel"};

        // if the selected expense is pinned, change the "pin" button to "unpin"
        if (pinnedExpense != null && pinnedExpense.getName().equals(selectedExpenseName)) {
            options[0] = "Unpin";
        }

        // shows user information detailed information about the expense and the buttons from options
        option = JOptionPane.showOptionDialog(this,
                detailedInfo.toString(), selectedExpenseName,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        // actions based on what button the user clicks
        if (option == 0) {
            // Pin or Unpin the expense
            pinExpense(selectedIndex);
        } else if (option == 1) {
            // Delete the expense
            deleteExpense(selectedExpenseName);
        }
    }

    // EFFECTS: allows user to pin or unpin an expense
    private void pinExpense(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < expenseList.getSize()) {
            Expense selectedExpense = expenseList.getExpense(selectedIndex);
            if (pinnedExpense != null && pinnedExpense.equals(selectedExpense)) {
                // Unpin the expense
                pinnedExpense = null;
            } else {
                // Pin the expense
                pinnedExpense = selectedExpense;
            }
        }
    }

    // MODIFIES: expenseList
    // EFFECTS: deletes an expense from the list of expenses
    public void deleteExpense(String name) {
        if (expenseList.deleteExpense(name)) {
            JOptionPane.showMessageDialog(this, "The expense " + name + " has been deleted",
                    "Expense Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Expense not found!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: indicates if the "Quit" button has been clicked
    public boolean shouldQuitApplication() {
        return quitApplication;
    }

    // EFFECTS: quits app if the "Quit" button has been clicked
    private void quitApplication() {
        quitApplication = true; // indicates the "Quit" button is clicked
        int response = JOptionPane.showConfirmDialog(this,
                "Do you want to save data before quitting?", "Save Data", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            app.saveExpenseList();
        }
        System.exit(0); // Quit the application
    }

    // EFFECTS: clears the text input fields
    private void clearInputFields() {
        nameField.setText("");
        costField.setText("");
        numPeopleField.setText("");
    }
}
