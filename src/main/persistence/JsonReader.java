// The following code is taken from the JsonReader class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java

package persistence;

import model.Expense;
import model.ExpenseList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads expense list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads expense list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ExpenseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses expense list from JSON object and returns it
    private ExpenseList parseExpenseList(JSONObject jsonObject) {
        ExpenseList li = new ExpenseList();
        JSONArray jsonArray = jsonObject.getJSONArray("list of expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(li, nextExpense);
        }
        return li;
    }

    // MODIFIES: li
    // EFFECTS: parses Expense from JSON object and adds it to ExpenseList
    private void addExpense(ExpenseList li, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double totalCost = jsonObject.getDouble("total cost");
        int numPeople = jsonObject.getInt("number of people");
        Expense expense = new Expense(totalCost, numPeople, name);
        li.setLogExpenseAddition(false);
        li.addExpense(expense);
    }
}
