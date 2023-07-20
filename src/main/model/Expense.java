package model;

// expense object
public class Expense {
    private final double totalCost;
    private final int numPeople;
    private final String name;

    // pass in cost
    // constructor
    public Expense(double totalCost, int numPeople, String name) {
        this.totalCost = totalCost;
        this.numPeople = numPeople;
        this.name = name;
    }

    public double splitCost(double totalCost, int numPeople) {
        return totalCost / numPeople;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public String getName() {
        return name;
    }
}
