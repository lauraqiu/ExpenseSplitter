package model;

// expense object
public class Expense {
    private final int initExpense;
    private final int splitExpense;

    // pass in cost
    // constructor
    public Expense(int initExpense, int splitExpense) {
        this.initExpense = initExpense;
        this.splitExpense = splitExpense;
    }

    public int getInitExpense() {
        return initExpense;
    }

    public int getSplitExpense() {
        return splitExpense;
    }

}
