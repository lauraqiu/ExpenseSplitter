package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

// holds a list of expenses, can add, delete, or view expenses
public class ExpenseList implements Writeable {
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
    public boolean deleteExpense(String name) {
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getName().equalsIgnoreCase(name)) {
                expenseList.remove(i);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of expenses stored in the list of expenses
    public int getSize() {
        return expenseList.size();
    }

    // EFFECTS: returns an expense from the list of expenses
    public Expense getExpense(int i) {
        return expenseList.get(i);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list of expenses", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Expense e: expenseList) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }
}
