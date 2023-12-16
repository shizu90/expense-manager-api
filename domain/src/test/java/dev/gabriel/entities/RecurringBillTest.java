package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecurringBillTest {
    @Test
    @DisplayName("Recurring bill calculate next payment date should work properly.")
    public void calculateNextPaymentDateTestCase() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        IRecurringBill recurringExpense = RecurringExpense
                .create(1L, "Expense", "Expense", 80.0, ExpenseCategory.ENTERTAINMENT, 4);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(recurringExpense.getNextPaymentDate());
        calendar.add(Calendar.DATE, 4);
        Date expectedPreviousPaymentDate = new Date();
        Date expectedNextPaymentDate = calendar.getTime();
        calendar.clear();

        recurringExpense.calculateNextPaymentDate();

        Assertions.assertEquals(formatter.format(expectedPreviousPaymentDate), formatter.format(recurringExpense.getPreviousPaymentDate()));
        Assertions.assertEquals(formatter.format(expectedNextPaymentDate), formatter.format(recurringExpense.getNextPaymentDate()));

        recurringExpense.calculateNextPaymentDate();

        Assertions.assertEquals(formatter.format(expectedPreviousPaymentDate), formatter.format(recurringExpense.getPreviousPaymentDate()));
        Assertions.assertEquals(formatter.format(expectedNextPaymentDate), formatter.format(recurringExpense.getNextPaymentDate()));
    }

    @Test
    @DisplayName("Should return recurring bill next payment date properly.")
    public void getNextPaymentDateTestCase() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        IRecurringBill recurringExpense = RecurringExpense
                .create(1L, "Expense", "Expense", 80.0, ExpenseCategory.ENTERTAINMENT, 4);

        Assertions.assertEquals(formatter.format(new Date()), formatter.format(recurringExpense.getNextPaymentDate()));
    }

    @Test
    @DisplayName("Should return recurring bill previous payment date properly.")
    public void getPreviousPaymentDateTestCase() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        IRecurringBill recurringExpense = RecurringExpense
                .create(1L, "Expense", "Expense", 80.0, ExpenseCategory.ENTERTAINMENT, 4);

        Assertions.assertEquals(formatter.format(new Date()), formatter.format(recurringExpense.getPreviousPaymentDate()));
    }

    @Test
    @DisplayName("Should return recurring bill days occurrence properly.")
    public void getDaysOccurrenceCase() {
        IRecurringBill recurringExpense = RecurringExpense
                .create(1L, "Expense", "Expense", 80.0, ExpenseCategory.ENTERTAINMENT, 4);

        Assertions.assertEquals(4, recurringExpense.getDaysOccurrence());
    }
}
