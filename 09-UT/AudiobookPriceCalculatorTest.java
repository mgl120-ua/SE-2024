package put.io.testing.audiobooks;

import org.junit.jupiter.api.Test;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {

    private AudiobookPriceCalculator calculator;

    @Before
    public void setUp() {
        calculator = new AudiobookPriceCalculator();
    }
    @Test
    public void testCalculate_notSubscriber() {
        setUp();
        Customer subscriber = new Customer("Marta", Customer.LoyaltyLevel.STANDARD, false);
        Audiobook audiobook = new Audiobook("The Little Prince" , 10.0);
        assertEquals(10.0, calculator.calculate(subscriber, audiobook), 0.01);
    }

    @Test
    public void testCalculate_Subscriber() {
        setUp();
        Customer subscriber = new Customer("Marta", Customer.LoyaltyLevel.STANDARD, true);
        Audiobook audiobook = new Audiobook("The Little Prince" , 10.0);
        assertEquals(0.0, calculator.calculate(subscriber, audiobook), 0.01);
    }
    @Test
    public void testCalculate_SilverLoyalty() {
        setUp();
        Customer silverCustomer = new Customer("Pedro",Customer.LoyaltyLevel.SILVER, false);
        Audiobook audiobook = new Audiobook("Harry Potter", 10.0);
        assertEquals(9.0, calculator.calculate(silverCustomer, audiobook), 0.01);
    }
    @Test
    public void testCalculate_GoldLoyalty() {
        setUp();
        Customer goldCustomer = new Customer("Alfredo", Customer.LoyaltyLevel.GOLD, false);
        Audiobook audiobook = new Audiobook("The Hobbit", 10.0);
        assertEquals(8.0, calculator.calculate(goldCustomer, audiobook), 0.01);
    }

}