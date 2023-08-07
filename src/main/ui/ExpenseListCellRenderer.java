package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;

public class ExpenseListCellRenderer extends DefaultListCellRenderer {
    private final Expense pinnedExpense;
    private final ExpenseList expenseList;

    public ExpenseListCellRenderer(ExpenseList expenseList, Expense pinnedExpense) {
        this.expenseList = expenseList;
        this.pinnedExpense = pinnedExpense;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setForeground(isSelected ? Color.BLACK : list.getForeground());

        if (pinnedExpense != null && value != null) {
            Expense expense = expenseList.getExpense(index);
            if (pinnedExpense.equals(expense)) {
                label.setBackground(Color.YELLOW); // Highlight the pinned expense
            } else {
                label.setBackground(list.getBackground());
            }
        }
        return label;
    }
}
