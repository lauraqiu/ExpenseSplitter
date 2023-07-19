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

    public void deleteExpense(Expense expense) {
        int i = 0;
        boolean found = false;
        while (i < expenseList.size() && (!found)) {
            if (expenseList.get(i).getName().equalsIgnoreCase(expense.getName())) {
                expenseList.remove(i);
                found = true;
            }
            i++;
        }
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public Expense getExpense(String name) {
        int i = 0;
        while (i < expenseList.size()) {
            if (expenseList.get(i).getName().equalsIgnoreCase(name)) {
                return expenseList.get(i);
            }
            i++;
        }
        return null;
    }
}
