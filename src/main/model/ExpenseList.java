package model;

import java.util.ArrayList;

// holds a list of expenses, can add, delete, or view expenses
public class ExpenseList {
    private ArrayList<Expense> expenseList;

    // EFFECTS: constructs a new list of expenses
    public ExpenseList() {
        expenseList = new ArrayList<>();
    }

    // REQUIRES: expense != null
    // EFFECTS: adds an expense to the list of expenses
    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    // REQUIRES: name is a valid string
    // EFFECTS: deletes an expense from the list of expenses
    public void deleteExpense(String name) {
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getName().equalsIgnoreCase(name)) {
                expenseList.remove(i);
                break;
            }
        }
    }

    // EFFECTS: returns the number of expenses stored in the list of expenses
    public int getSize() {
        return expenseList.size();
    }

    // EFFECTS: returns an expense from the list of expenses
    public Expense getExpense(int i) {
        return expenseList.get(i);
    }

    // EFFECTS: prints statements detailing each expense in the list of expenses
    public void viewExpense() {
        System.out.println("You have added " + expenseList.size() + " expense(s)");
        for (int i = 0; i < expenseList.size(); i++) {
            Expense expense = expenseList.get(i);
            String name = expense.getName();
            double totalCost = expense.getTotalCost();
            int numPeople = expense.getNumPeople();
            double splitCost = expense.splitCost(totalCost, numPeople);
            System.out.println("Your expense " + name + " had a total of $" + totalCost
                    + " split between " + numPeople + " people. Each person owes $" + splitCost);
        }
    }
}
