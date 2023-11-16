package project01;

import java.util.List;

import net.jqwik.api.ForAll;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Property;

public class HistogramTests {
    // Note: this method can be used to run the unit test without a test framework (e.g. to debug)
    public static void main(String[] args) {
        HistogramTests h = new HistogramTests();
        h.example();
    }

    @Test
    void example() {
        Histogram histogram = new Histogram(2, 3, 0, 5, 5, 6, -2, 9, 0, 3, 5);
        Assertions.assertEquals(-2, histogram.min());
        Assertions.assertEquals(9, histogram.max());
        //complete assertion for all input from -3 to 10 like asked in A1.2-2
        Assertions.assertEquals(0,histogram.count(-1));

        Assertions.assertEquals(2, histogram.count(0));
        Assertions.assertEquals(0, histogram.count(1));
        Assertions.assertEquals(1, histogram.count(2));
        Assertions.assertEquals(2,histogram.count(3));
        Assertions.assertEquals(0,histogram.count(4));
        Assertions.assertEquals(3,histogram.count(5));
        Assertions.assertEquals(1,histogram.count(6));
        Assertions.assertEquals(0,histogram.count(7));
        Assertions.assertEquals(0,histogram.count(8));
        Assertions.assertEquals(1,histogram.count(9));
        Assertions.assertEquals(0,histogram.count(10));
    }

    int countOccurrences(int value, List<Integer> data) {
        return (int) data.stream().filter(i -> i == value).count();
    }

    @Property //
    void histogramDoesNotCrash(@ForAll List<@Positive Integer> data) {
        new Histogram(data);
    }

    @Property // correspnds to A2-2 to value from -3 to 10
    void histogramCount(@ForAll List<@IntRange(min=-3,max=10) Integer> data, @ForAll @IntRange(min=-3,max=10) int value)
    {
        Assertions.assertEquals(new Histogram(data).count(value),countOccurrences(value,data));
    }

    @Property
    void histogramRange(@ForAll List<@IntRange(min=-3,max=10) Integer> data, @ForAll @IntRange(min=-3,max=10) int value) {
        Assertions.assertTrue(countOccurrences(value,data)>0);
        Assertions.assertTrue(value <= new Histogram(data).max );
        Assertions.assertTrue(value >= new Histogram(data).min);
    }
}
