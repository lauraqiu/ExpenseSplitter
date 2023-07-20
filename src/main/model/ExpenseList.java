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
        int i = 0;
        boolean found = false;
        while (i < expenseList.size() && (!found)) {
            if (expenseList.get(i).getName().equalsIgnoreCase(name)) {
                expenseList.remove(i);
                found = true;
            }
            i++;
        }
    }

    public Expense getExpense(int i) {
        return expenseList.get(i);
    }

    public int getSize() {
        return expenseList.size();
    }
}
