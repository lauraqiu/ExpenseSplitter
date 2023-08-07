package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseSplitterGUI extends JFrame {
    private ExpenseList expenseList;
    private ExpenseSplitterApp app;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel buttonPanel;
    private JTextField nameField;
    private JTextField costField;
    private JTextField numPeopleField;
    private boolean quitApplication = false;

    @SuppressWarnings("methodlength")
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
        JButton deleteButton = new JButton("Delete Expense");
        JButton viewButton = new JButton("View Expenses");
        JButton quitButton = new JButton("Quit");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExpenses();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewExpenses();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitApplication();
            }
        });

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

    public void addExpense() {
        String name = nameField.getText();
        double cost = Double.parseDouble(costField.getText());
        int numPeople = Integer.parseInt(numPeopleField.getText());

        Expense expense = new Expense(cost, numPeople, name);
        expenseList.addExpense(expense);
        JOptionPane.showMessageDialog(this, "Your expense has been split and added to the expense tracker",
                "Expense Added", JOptionPane.INFORMATION_MESSAGE);

        clearInputFields();
    }

    public void deleteExpense(String name) {
        if (expenseList.deleteExpense(name)) {
            JOptionPane.showMessageDialog(this, "The expense " + name + " has been deleted",
                    "Expense Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Expense not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("methodlength")
    public void viewExpenses() {
        while (true) {
            String[] expenseNames = new String[expenseList.getSize()];
            StringBuilder[] detailedInfo = new StringBuilder[expenseList.getSize()];

            for (int i = 0; i < expenseList.getSize(); i++) {
                Expense expense = expenseList.getExpense(i);
                expenseNames[i] = expense.getName();

                // Create detailed information for each expense
                double totalCost = expense.getTotalCost();
                int numPeople = expense.getNumPeople();
                double splitCost = expense.splitCost(totalCost, numPeople);
                detailedInfo[i] = new StringBuilder();
                detailedInfo[i].append("Expense: ").append(expense.getName()).append("\n")
                        .append("Total Cost: $").append(totalCost).append("\n")
                        .append("Number of People: ").append(numPeople).append("\n")
                        .append("Split Cost: $").append(splitCost).append("\n");
            }

            JList<String> expenseListJList = new JList<>(expenseNames);
            expenseListJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JScrollPane scrollPane = new JScrollPane(expenseListJList);

            int choice = JOptionPane.showConfirmDialog(this, scrollPane,
                    "Select an expense to view:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (choice == JOptionPane.OK_OPTION) {
                int selectedIndex = expenseListJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedExpenseName = expenseNames[selectedIndex];
                    int deleteChoice = JOptionPane.showConfirmDialog(this,
                            detailedInfo[selectedIndex].toString() + "\n\nDo you want to delete this expense?",
                            "Delete Expense", JOptionPane.YES_NO_OPTION);

                    if (deleteChoice == JOptionPane.YES_OPTION) {
                        deleteExpense(selectedExpenseName);
                        break; // Exit the loop after deleting the expense
                    }
                }
            } else {
                break; // Exit the loop if the user clicks "Cancel" or closes the window
            }
        }
    }




    private void clearInputFields() {
        nameField.setText("");
        costField.setText("");
        numPeopleField.setText("");
    }

    public boolean shouldQuitApplication() {
        return quitApplication;
    }

    private void quitApplication() {
        quitApplication = true; // Set the flag to indicate the "Quit" button is clicked
        int response = JOptionPane.showConfirmDialog(this,
                "Do you want to save data before quitting?", "Save Data", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            app.saveExpenseList(); // Call saveExpenseList() from the ExpenseSplitterApp instance
        }
        System.exit(0); // Quit the application
    }
}
