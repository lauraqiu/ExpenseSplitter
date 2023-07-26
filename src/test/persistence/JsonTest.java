// The following code is taken from the JsonTest class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonTest.java

package persistence;

import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tests Expense objects are correctly converted to JSON
public class JsonTest {
    protected void checkExpense(double totalCost, int numPeople, String name, Expense expense) {
        assertEquals(name, expense.getName());
        assertEquals(totalCost, expense.getTotalCost());
        assertEquals(numPeople, expense.getNumPeople());
    }
}
