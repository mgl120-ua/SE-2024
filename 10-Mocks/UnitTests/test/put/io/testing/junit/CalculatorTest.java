package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        int result1 = calculator.add(2, 5);
        assertEquals(7, result1);
        
        int result2 = calculator.add(-2, 2);
        assertEquals(0, result2);
    }

    @Test
    public void testMultiply() {
        int result1 = calculator.multiply(3, 4);
        assertEquals(12, result1);

        int result2 = calculator.multiply(-2, 5);
        assertEquals(-10, result2);
    }

    @Test
    public void testAddPositiveNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {calculator.addPositiveNumbers(-12, 5);});
    }
}