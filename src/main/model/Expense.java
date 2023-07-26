// The following code is taken from the Thingy class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/model/Thingy.java

package model;

import org.json.JSONObject;
import persistence.Writeable;

// holds information about an expense's total cost, number of people involved, and name of expense
// also splits expense according to the cost and number of people involved
public class Expense implements Writeable {
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
        return (double) Math.round((totalCost / numPeople) * (int) Math.pow(10, 1)) / (int) Math.pow(10, 1);
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

    // EFFECTS: Converts the Expense object to a JSON representation and returns JSON object representing
    // the Expense object with its total cost, number of people, and expense name
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("total cost", totalCost);
        json.put("number of people", numPeople);
        json.put("name", name);
        return json;
    }
}
