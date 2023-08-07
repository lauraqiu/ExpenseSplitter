package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// controls how each expense is visually displayed by highlighting pinned expenses
public class ExpenseListCellRenderer extends DefaultListCellRenderer {
    private final ArrayList<Expense> pinnedExpenses; // Update to store a list of pinned expenses
    private final ExpenseList expenseList;

    // EFFECTS: constructs an ExpenseListCellRenderer object
    public ExpenseListCellRenderer(ExpenseList expenseList, ArrayList<Expense> pinnedExpenses) {
        this.expenseList = expenseList;
        this.pinnedExpenses = pinnedExpenses;
    }

    // EFFECTS: highlights the background colour of pinned expense and keeps text colour black
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        // if cell is selected, sets text colour to black, otherwise, uses default foreground colour
        label.setForeground(isSelected ? Color.BLACK : list.getForeground());

        if (pinnedExpenses.contains(expenseList.getExpense(index))) {
            label.setBackground(Color.YELLOW); // if expense is pinned, we highlight it
        } else {
            label.setBackground(list.getBackground());
        }
        return label;
    }
}

