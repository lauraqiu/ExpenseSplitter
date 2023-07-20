package model;

// holds information about an expense's total cost, number of people involved, and name of expense
// also splits expense according to the cost and number of people involved
public class Expense {
    private final double totalCost;
    private final int numPeople;
    private final String name;

    // REQUIRES: totalCost and numPeople > 0 and name to be a valid string
    // EFFECTS: constructs an expense with its total cost, number of people, and expense name
    public Expense(double totalCost, int numPeople, String name) {
        this.totalCost = totalCost;
        this.numPeople = numPeople;
        this.name = name;
    }

    // REQUIRES: totalCost and numPeople > 0
    // EFFECTS: returns the split cost of an expense
    public double splitCost(double totalCost, int numPeople) {
        return totalCost / numPeople;
    }

    // EFFECTS: returns the total cost of an expense
    public double getTotalCost() {
        return totalCost;
    }

    // EFFECTS: returns the number of people involved in an expense
    public int getNumPeople() {
        return numPeople;
    }

    // EFFECTS: returns the name of an expense
    public String getName() {
        return name;
    }
}
