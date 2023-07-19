package model;

// expense object
public class Expense {
    private final int initExpense;
    private final int splitExpense;
    private final String name;

    // pass in cost
    // constructor
    public Expense(int initExpense, int splitExpense, String name) {
        this.initExpense = initExpense;
        this.splitExpense = splitExpense;
        this.name = name;
    }

    public int getInitExpense() {
        return initExpense;
    }

    public int getSplitExpense() {
        return splitExpense;
    }

    public String getName() {
        return name;
    }
}
