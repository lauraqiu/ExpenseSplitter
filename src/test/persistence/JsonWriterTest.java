package persistence;

import model.Expense;
import model.ExpenseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    // NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    // write data to a file and then use the reader to read it back in and check that we
    // read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ExpenseList li = new ExpenseList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExpenseList() {
        try {
            ExpenseList li = new ExpenseList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenseList.json");
            writer.open();
            writer.write(li);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenseList.json");
            li = reader.read();
            assertEquals(0, li.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExpenseList() {
        try {
            ExpenseList li = new ExpenseList();
            li.addExpense(new Expense(20.0, 10, "sushi"));
            li.addExpense(new Expense(10.0, 2, "pizza takeout"));
            li.addExpense(new Expense(50.0, 5, "car rental"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExpenseList.json");
            writer.open();
            writer.write(li);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExpenseList.json");
            li = reader.read();
            assertEquals(3, li.getSize());

            Expense expense1 = li.getExpense(0);
            checkExpense(20.0, 10, "sushi", expense1);

            Expense expense2 = li.getExpense(1);
            checkExpense(10.0, 2, "pizza takeout", expense2);

            Expense expense3 = li.getExpense(2);
            checkExpense(50.0, 5, "car rental", expense3);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
