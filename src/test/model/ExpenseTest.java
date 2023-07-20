package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//tests the methods found the Expense class
public class ExpenseTest {
    Expense e1;
    Expense e2;
    Expense e3;

    @BeforeEach
    void RunBefore() {
        e1 = new Expense(20, 10, "sushi");
        e2 = new Expense(10, 3, "pizza takeout");
        e3 = new Expense(50, 5, "car rental");
    }

    @Test
    public void testConstructor() {
        assertEquals(20, e1.getTotalCost());
        assertEquals(10, e1.getNumPeople());
        assertEquals("sushi", e1.getName());
    }

    @Test
    public void testSplitExpense() {
        assertEquals(2.0, e1.splitCost(e1.getTotalCost(), e1.getNumPeople()));
        assertEquals(3.3, e2.splitCost(e2.getTotalCost(), e2.getNumPeople()));
        assertEquals(10.0, e3.splitCost(e3.getTotalCost(), e3.getNumPeople()));
    }

    @Test
    public void testGetTotalCost() {
        assertEquals(20, e1.getTotalCost());
        assertEquals(10, e2.getTotalCost());
        assertEquals(50, e3.getTotalCost());
    }

    @Test
    public void testGetNumPeople() {
        assertEquals(10, e1.getNumPeople());
        assertEquals(3, e2.getNumPeople());
        assertEquals(5, e3.getNumPeople());
    }

    @Test
    public void testGetName() {
        assertEquals("sushi", e1.getName());
        assertEquals("pizza takeout", e2.getName());
        assertEquals("car rental", e3.getName());
    }
}