package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseListTest {
    Expense e1;
    Expense e2;
    Expense e3;

    ExpenseList li0;
    ExpenseList li1;
    ExpenseList li2;
    ExpenseList li3;

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
    public void testGetSize() {
        assertEquals(3, li3.getSize());
        assertEquals(0, li0.getSize());
    }

    @Test
    public void testViewExpenseOne() {
        assertEquals("You have added 1 expense(s)", "You have added " + li1.getSize()
                + " expense(s)");

        assertEquals("Your expense sushi had a total of $20.0 split between 10 people. Each person owes $2.0",
                "Your expense " + li1.getExpense(0).getName() + " had a total of $"
                        + li1.getExpense(0).getTotalCost() + " split between " + li1.getExpense(0).getNumPeople()
                        + " people. Each person owes $"
                        + li1.getExpense(0).splitCost(li1.getExpense(0).getTotalCost(),
                        li1.getExpense(0).getNumPeople()));
    }

    @Test
    public void testViewExpenseMultiple() {

        assertEquals("You have added 2 expense(s)", "You have added " + li2.getSize()
                + " expense(s)");

        assertEquals("Your expense sushi had a total of $20.0 split between 10 people. Each person owes $2.0",
                "Your expense " + li2.getExpense(0).getName() + " had a total of $"
                        + li2.getExpense(0).getTotalCost() + " split between " + li2.getExpense(0).getNumPeople()
                        + " people. Each person owes $"
                        + li2.getExpense(0).splitCost(li2.getExpense(0).getTotalCost(),
                        li2.getExpense(0).getNumPeople()));

        assertEquals("Your expense pizza takeout had a total of $10.0 split between 2 people. " +
                        "Each person owes $5.0", "Your expense " + li2.getExpense(1).getName()
                        + " had a total of $" + li2.getExpense(1).getTotalCost() + " split between "
                        + li2.getExpense(1).getNumPeople() + " people. Each person owes $"
                        + li2.getExpense(1).splitCost(li2.getExpense(1).getTotalCost(),
                        li2.getExpense(1).getNumPeople()));

//        assertEquals("Your expense car rental had a total of $50.0 split between 5 people. Each person " +
//                        "owes $10.0", "Your expense " + li3.getExpense(0).getName() + " had a total of $"
//                        + li3.getExpense(0).getTotalCost() + " split between " + li3.getExpense(0).getNumPeople()
//                        + " people. Each person owes $"
//                        + li3.getExpense(0).splitCost(li3.getExpense(0).getTotalCost(),
//                        li3.getExpense(0).getNumPeople()));
    }

}