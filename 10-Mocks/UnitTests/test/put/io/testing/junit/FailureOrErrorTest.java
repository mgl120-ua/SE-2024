package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FailureOrErrorTest {
    @Test
    public void test1() {
        assertTrue(false);
    }

    @Test
    public void test2() {
        throw new RuntimeException("Arbitrary exception thrown");
    }
}
