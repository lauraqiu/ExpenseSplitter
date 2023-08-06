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
                deleteExpense();
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
        buttonPanel.add(deleteButton);
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

    public void deleteExpense() {
        String name = nameField.getText();
        if (expenseList.deleteExpense(name)) {
            JOptionPane.showMessageDialog(this, "The expense " + name + " has been deleted",
                    "Expense Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Expense not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        clearInputFields();
    }

    @SuppressWarnings("methodlength")
    public void viewExpenses() {
        StringBuilder expensesInfo = new StringBuilder();
        for (int i = 0; i < expenseList.getSize(); i++) {
            Expense expense = expenseList.getExpense(i);
            String name = expense.getName();
            double totalCost = expense.getTotalCost();
            int numPeople = expense.getNumPeople();
            double splitCost = expense.splitCost(totalCost, numPeople);
            expensesInfo.append("Your expense ").append(name).append(" had a total of $").append(totalCost)
                    .append(" split between ").append(numPeople).append(" people. Each person owes $").append(splitCost)
                    .append("\n");
        }

        JTextArea textArea = new JTextArea(expensesInfo.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "View Expenses", JOptionPane.INFORMATION_MESSAGE);

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
