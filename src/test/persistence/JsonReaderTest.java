// The following code is taken from the JsonReaderTest class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonReaderTest.java

package persistence;

import model.Expense;
import model.ExpenseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// tests methods in JsonReader
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ExpenseList li = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenseList.json");
        try {
            ExpenseList li = reader.read();
            assertEquals(0, li.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExpenseList.json");
        try {
            ExpenseList li = reader.read();
            assertEquals(3, li.getSize());

            Expense expense1 = li.getExpense(0);
            checkExpense(20.0, 10, "sushi", expense1);

            Expense expense2 = li.getExpense(1);
            checkExpense(10.0, 2, "pizza takeout", expense2);

            Expense expense3 = li.getExpense(2);
            checkExpense(50.0, 5, "car rental", expense3);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
