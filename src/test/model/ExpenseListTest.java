package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseListTest {
    ExpenseList expenseList;
    Expense e1;
    Expense e2;
    Expense e3;

    @BeforeEach
    void RunBefore() {
        expenseList = new ExpenseList();
        e1 = new Expense(20, 10, "sushi");
        e2 = new Expense(10, 2, "pizza takeout");
        e3 = new Expense(50, 5, "car rental");
    }

    @Test
    public void testAddExpenseOne() {
        expenseList.addExpense(e1);

        assertEquals(e1, expenseList.getExpense(0));
    }

    @Test
    public void testAddExpenseMultiple() {
        expenseList.addExpense(e1);
        expenseList.addExpense(e2);
        expenseList.addExpense(e3);

        assertEquals(e1, expenseList.getExpense(0));
        assertEquals(e2, expenseList.getExpense(1));
        assertEquals(e3, expenseList.getExpense(2));
    }

    @Test
    public void testDeleteExpenseIndexZero() {
        expenseList.addExpense(e1);
        expenseList.addExpense(e2);
        expenseList.addExpense(e3);

        assertEquals(e1, expenseList.getExpense(0));
        assertEquals(e2, expenseList.getExpense(1));
        assertEquals(e3, expenseList.getExpense(2));

        expenseList.deleteExpense(e1.getName());

        assertEquals(e2, expenseList.getExpense(0));
        assertEquals(e3, expenseList.getExpense(1));
    }

    @Test
    public void testDeleteExpenseIndexNotZero() {
        expenseList.addExpense(e1);
        expenseList.addExpense(e2);
        expenseList.addExpense(e3);

        assertEquals(e1, expenseList.getExpense(0));
        assertEquals(e2, expenseList.getExpense(1));
        assertEquals(e3, expenseList.getExpense(2));

        expenseList.deleteExpense(e2.getName());

        assertEquals(e1, expenseList.getExpense(0));
        assertEquals(e3, expenseList.getExpense(1));
    }

    @Test
    public void testViewExpenseOne() {
        expenseList.addExpense(e1);

        assertEquals("You have added 1 expense(s)", "You have added " + expenseList.getSize()
                + " expense(s)");

        assertEquals("Your expense sushi had a total of $20.0 split between 10 people. Each person owes $2.0",
                "Your expense " + e1.getName() + " had a total of $" + e1.getTotalCost() + " split between "
                        + e1.getNumPeople() + " people. Each person owes $"
                        + e1.splitCost(e1.getTotalCost(), e1.getNumPeople()));
    }

    @Test
    public void testViewExpenseMultiple() {
        expenseList.addExpense(e2);
        expenseList.addExpense(e3);

        assertEquals("You have added 2 expense(s)", "You have added " + expenseList.getSize()
                + " expense(s)");

        assertEquals("Your expense pizza takeout had a total of $10.0 split between 2 people. " +
                        "Each person owes $5.0", "Your expense " + e2.getName() + " had a total of $"
                        + e2.getTotalCost() + " split between " + e2.getNumPeople() + " people. Each person owes $"
                        + e2.splitCost(e2.getTotalCost(), e2.getNumPeople()));

        assertEquals("Your expense car rental had a total of $50.0 split between 5 people. Each person " +
                        "owes $10.0", "Your expense " + e3.getName() + " had a total of $"
                        + e3.getTotalCost() + " split between " + e3.getNumPeople() + " people. Each person owes $"
                        + e3.splitCost(e3.getTotalCost(), e3.getNumPeople()));
    }

}