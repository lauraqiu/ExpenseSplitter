package persistence;

import model.ExpenseList;
import model.Expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExpense(double totalCost, int numPeople, String name, Expense expense) {
        assertEquals(name, expense.getName());
        assertEquals(totalCost, expense.getTotalCost());
        assertEquals(numPeople, expense.getNumPeople());
    }
}
