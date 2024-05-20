package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import org.mockito.InOrder;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import static org.mockito.Mockito.*;

public class ExpenseRepositoryTest {

    @Test
    void loadExpensesTest() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository repository = new ExpenseRepository(mockDatabase);
        repository.loadExpenses();

        mockDatabase.close();

        assertTrue(repository.getExpenses().isEmpty(), "the list should be empty");

        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();
    }

    @Test
    void saveExpensesTest() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        ExpenseRepository repository = new ExpenseRepository(mockDatabase);

        Expense newExpense = new Expense();
        newExpense.setAmount(100);
        newExpense.setCategory("Utilities");
        repository.addExpense(newExpense);

        repository.saveExpenses();

        verify(mockDatabase).persist(newExpense);

        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase, times(1)).persist(newExpense);
        inOrder.verify(mockDatabase).close();
    }
}
