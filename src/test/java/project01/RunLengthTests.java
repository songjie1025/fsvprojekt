package project01;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class RunLengthTests {

    // Note: this method can be used to run the unit test without a test framework (e.g. to debug)
    public static void main(String[] args) {
        RunLengthTests r = new RunLengthTests();
        r.example();
    }

    @Test
    void example() {
        List<String> input = List.of("A", "A", "B");
        List<Run<String>> expected = List.of(new Run<>(2, "A"), new Run<>(1, "B"));
        Assertions.assertEquals(expected, RunLength.encode(input));
    }

    public static Integer sum(List<Integer> elems) {
        return elems.stream().reduce(0, Integer::sum);
    }

    @Property
    void canDecode(@ForAll List<String> input) {

        List<Run<String>> encoded = RunLength.encode(input);
        List<String> decoded = RunLength.decode(encoded);
        Assertions.assertEquals(input, decoded);
}

    @Property
    void optimizedSum(@ForAll List<Integer> input) {

        List<Run<Integer>> encoded = RunLength.encode(input);
        int optimizedSum = RunLength.sum(encoded);
        int referenceSum = sum(input);
        Assertions.assertEquals(referenceSum, optimizedSum);
    }
}
