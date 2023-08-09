package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests the methods in the ExpenseList Class
public class ExpenseListTest {
    Expense e1;
    Expense e2;
    Expense e3;

    ExpenseList li0;
    ExpenseList li1;
    ExpenseList li2;
    ExpenseList li3;

    Event event = null;

    @BeforeEach
    void RunBefore() {
        e1 = new Expense(20, 10, "sushi");
        e2 = new Expense(10, 2, "pizza takeout");
        e3 = new Expense(50, 5, "car rental");

        li0 = new ExpenseList();

        li1 = new ExpenseList();
        li1.addExpense(e1);

        li2 = new ExpenseList();
        li2.addExpense(e1);
        li2.addExpense(e2);

        li3 = new ExpenseList();
        li3.addExpense(e1);
        li3.addExpense(e2);
        li3.addExpense(e3);
    }

    @Test
    public void testAddExpenseTrueLogExpenseAddition() {
        li0.setLogExpenseAddition(true);
        li0.addExpense(e1);

        for (Event e: EventLog.getInstance()) {
            event = e;
        }

        assertEquals("New expense added to expense list", event.getDescription());
    }

    @Test
    public void testAddExpenseFalseLogExpenseAddition() {
        li0.setLogExpenseAddition(false);
        li0.addExpense(e1);

        assertTrue(event == null);
    }

    @Test
    public void testAddExpenseOne() {
        li0.addExpense(e1);

        assertEquals(e1, li0.getExpense(0));
    }

    @Test
    public void testAddExpenseMultiple() {
        li1.addExpense(e2);
        li1.addExpense(e3);

        assertEquals(e1, li1.getExpense(0));
        assertEquals(e2, li1.getExpense(1));
        assertEquals(e3, li1.getExpense(2));
    }

    @Test
    public void testDeleteExpenseIndexZero() {
        li3.deleteExpense(e1.getName());

        assertEquals(e2, li3.getExpense(0));
        assertEquals(e3, li3.getExpense(1));
    }

    @Test
    public void testDeleteExpenseIndexNotZero() {
        li3.deleteExpense(e2.getName());

        assertEquals(e1, li3.getExpense(0));
        assertEquals(e3, li3.getExpense(1));
    }

    @Test
    public void testDeleteExpenseNonExistent(){
        li1.deleteExpense("nonexistent");

        assertEquals(e1, li1.getExpense(0));
    }

    @Test
    public void testGetSize() {
        assertEquals(0, li0.getSize());
        assertEquals(1, li1.getSize());
        assertEquals(2, li2.getSize());
        assertEquals(3, li3.getSize());
    }

    @Test
    public void testGetSizeAddOne() {
        assertEquals(0, li0.getSize());

        li0.addExpense(e1);

        assertEquals(1, li0.getSize());
    }

    @Test
    public void testGetExpense() {
        assertEquals(e1, li3.getExpense(0));
        assertEquals(e2, li3.getExpense(1));
        assertEquals(e3, li3.getExpense(2));
    }
}