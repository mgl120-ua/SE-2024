package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    @Mock
    private ExpenseRepository mockRepository;
    @Mock
    private FancyService mockFancyService;

    private ExpenseManager manager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        manager = new ExpenseManager(mockRepository, mockFancyService);
    }

    @Test
    public void testCalculateTotalExpenses() {
        List<Expense> expenses = Arrays.asList(
                new Expense(100, "Food"),
                new Expense(150, "Utilities")
        );
        when(mockRepository.getExpenses()).thenReturn(expenses);

        long total = manager.calculateTotal();

        assertEquals(250, total, "El total calculado debería ser 250");

        verify(mockRepository).getExpenses();
    }

    @Test
    public void testCalculateTotalForCategoryWithMultipleExpenses() {
        List<Expense> foodExpenses = Arrays.asList(
                new Expense(120, "Food"),
                new Expense(130, "Food")
        );
        when(mockRepository.getExpensesByCategory("Food")).thenReturn(foodExpenses);

        long total = manager.calculateTotalForCategory("Food");
        assertEquals(250, total, "El total para la categoría 'Food' debería ser 250");

        verify(mockRepository).getExpensesByCategory("Food");
    }

    @Test
    public void testCalculateTotalForEmptyCategory() {
        when(mockRepository.getExpensesByCategory("Entertainment")).thenReturn(new ArrayList<>());

        long total = manager.calculateTotalForCategory("Entertainment");
        assertEquals(0, total, "El total para una categoría vacía debería ser 0");

        verify(mockRepository).getExpensesByCategory("Entertainment");
    }

    @Test
    public void testCalculateTotalInDollars_SuccessfulConversion() throws ConnectException {
        List<Expense> expenses = Arrays.asList(
                new Expense(100, "Food"),
                new Expense(100, "Utilities")
        );
        when(mockRepository.getExpenses()).thenReturn(expenses);
        when(mockFancyService.convert(anyDouble(), eq("PLN"), eq("USD")))
                .thenAnswer(invocation -> {
                    // Obtiene el primer argumento pasado al método convert
                    double amount = invocation.getArgument(0);

                    double convertedAmount = amount / (double) 4.0;

                    // Devuelve el valor convertido
                    return convertedAmount;
                });

        long totalInDollars = manager.calculateTotalInDollars();

        assertEquals(50.0, totalInDollars, "El total en dólares debería ser 50.0");

        verify(mockRepository).getExpenses();
        verify(mockFancyService, times(1)).convert(anyDouble(), eq("PLN"), eq("USD"));
    }

    @Test
    public void testCalculateTotalInDollars_ConnectException() throws ConnectException {
        List<Expense> expenses = Arrays.asList(
                new Expense(100, "Food"),
                new Expense(150, "Utilities")
        );
        when(mockRepository.getExpenses()).thenReturn(expenses);
        when(mockFancyService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(ConnectException.class);

        long totalInDollars = manager.calculateTotalInDollars();

        assertEquals(-1, totalInDollars, "El total en dólares debería ser -1");

        verify(mockRepository).getExpenses();
        verify(mockFancyService, times(1)).convert(anyDouble(), eq("PLN"), eq("USD"));
    }
}
