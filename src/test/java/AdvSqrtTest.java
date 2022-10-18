import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class AdvSqrtTest {

    static AdvSqrt as = new AdvSqrt();
    static final double EPSILON = 0.0000000000000000000000001;

    @Test
    public void testPositiveNumbersWithoutRounding() {
        Assertions.assertEquals(2.0, as.sqrt(4));
        Assertions.assertEquals(6.0, as.sqrt(36));
        Assertions.assertEquals(18.0, as.sqrt(324));
    }

    @Test
    public void testNegativeNumber() {
        Assertions.assertEquals(Double.NaN, as.sqrt(-1));
    }

    @Test
    public void testNegativeInfinity() {
        Assertions.assertEquals(Double.NaN, as.sqrt(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testNegativeZero() {
        Assertions.assertEquals(Double.NaN, as.sqrt(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void testPositiveInfinity() {
        Assertions.assertEquals(Double.POSITIVE_INFINITY, as.sqrt(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testNaN() {
        Assertions.assertEquals(Double.NaN, as.sqrt(Double.NaN));
    }

    /**
     * Tests if rounding is correct for numbers - checking distance from
     * answer that was given by function to correct square value and then comparing
     * it to the distance from the closest double.
     */
    @Test
    public void testNumbersWithRounding() {
        for (int init = 4; init <= 200; ++init) {
            double asSqr = as.sqrt(init);
            double preciseSqr = Math.sqrt(init);

            if (Math.abs(preciseSqr - asSqr) < EPSILON) {
                return;
            }
            double secondOption;
            if (preciseSqr < asSqr) {
                // The closest double value to the precise sqrt value.
                secondOption = Math.nextDown(asSqr);
            } else {
                secondOption = Math.nextAfter(asSqr, Double.POSITIVE_INFINITY);
            }

            if (Math.abs(secondOption - preciseSqr) < Math.abs(asSqr - preciseSqr)) {
                // We need to check if rounding was correct (so, the answer value has 1
                // as last bit of mantissa.
                Assertions.assertEquals(0, Double.doubleToLongBits(asSqr) % 2);
            }
        }
    }
}