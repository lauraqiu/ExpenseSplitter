package model;

import java.util.ArrayList;

public class ExpenseList {
    private ArrayList<Expense> expenseList;

    public ExpenseList() {
        expenseList = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    public void deleteExpense(String name) {
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getName().equalsIgnoreCase(name)) {
                expenseList.remove(i);
                break;
            }
        }
    }

    public int getSize() {
        return expenseList.size();
    }

    public Expense getExpense(int i) {
        return expenseList.get(i);
    }

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
