package project01;

import java.util.List;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

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
    void histogramDoesNotCrash(@ForAll List<@IntRange(min=-10,max=10) Integer> data) {
        Assumptions.assumeTrue(!data.isEmpty()); //to ensure data is not empty,otherwise we have NosuchElementException
        new Histogram(data);
    }

    @Property
    void histogramCount(@ForAll List<@IntRange(min=10,max=100) Integer> data,@ForAll @IntRange(min=10, max=100) int value) {
        Assertions.assertEquals(new Histogram(data).count(value), countOccurrences(value, data));
    }



    @Property
    void histogramRange(@ForAll List<@IntRange(min=10,max=100) Integer> data, @ForAll @IntRange(min=10,max=100) int value) {
        Assume.that(countOccurrences(value, data) > 0);
        Assertions.assertTrue(value <= new Histogram(data).max );
        Assertions.assertTrue(value >= new Histogram(data).min);
    }

    /**
     * beim laufen des letzten Tests haben wir folgende Rückmeldung bekommen:
     *
     * C:\Users\Huawei\.jdks\openjdk-20.0.1\bin\java.exe -ea -Didea.test.cyclic.buffer.size=1048576 "-javaagent:D:\Intelij IDEA\IntelliJ IDEA 2023.1.1\lib\idea_rt.jar=4462:D:\Intelij IDEA\IntelliJ IDEA 2023.1.1\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "C:\Users\Huawei\.m2\repository\org\junit\platform\junit-platform-launcher\1.9.1\junit-platform-launcher-1.9.1.jar;D:\Intelij IDEA\IntelliJ IDEA 2023.1.1\lib\idea_rt.jar;D:\Intelij IDEA\IntelliJ IDEA 2023.1.1\plugins\junit\lib\junit5-rt.jar;D:\Intelij IDEA\IntelliJ IDEA 2023.1.1\plugins\junit\lib\junit-rt.jar;D:\Dokument\FsvProjekt\projekt1\target\test-classes;D:\Dokument\FsvProjekt\projekt1\target\classes;C:\Users\Huawei\.m2\repository\org\junit\jupiter\junit-jupiter\5.9.1\junit-jupiter-5.9.1.jar;C:\Users\Huawei\.m2\repository\org\junit\jupiter\junit-jupiter-api\5.9.1\junit-jupiter-api-5.9.1.jar;C:\Users\Huawei\.m2\repository\org\opentest4j\opentest4j\1.2.0\opentest4j-1.2.0.jar;C:\Users\Huawei\.m2\repository\org\junit\platform\junit-platform-commons\1.9.1\junit-platform-commons-1.9.1.jar;C:\Users\Huawei\.m2\repository\org\junit\jupiter\junit-jupiter-params\5.9.1\junit-jupiter-params-5.9.1.jar;C:\Users\Huawei\.m2\repository\org\junit\jupiter\junit-jupiter-engine\5.9.1\junit-jupiter-engine-5.9.1.jar;C:\Users\Huawei\.m2\repository\org\junit\platform\junit-platform-engine\1.9.1\junit-platform-engine-1.9.1.jar;C:\Users\Huawei\.m2\repository\net\jqwik\jqwik\1.7.0\jqwik-1.7.0.jar;C:\Users\Huawei\.m2\repository\org\apiguardian\apiguardian-api\1.1.2\apiguardian-api-1.1.2.jar;C:\Users\Huawei\.m2\repository\net\jqwik\jqwik-api\1.7.0\jqwik-api-1.7.0.jar;C:\Users\Huawei\.m2\repository\net\jqwik\jqwik-web\1.7.0\jqwik-web-1.7.0.jar;C:\Users\Huawei\.m2\repository\net\jqwik\jqwik-time\1.7.0\jqwik-time-1.7.0.jar;C:\Users\Huawei\.m2\repository\net\jqwik\jqwik-engine\1.7.0\jqwik-engine-1.7.0.jar" com.intellij.rt.junit.JUnitStarter -ideVersion5 -junit5 "project01.HistogramTests,histogramRange(java.util.List,int)"
     * timestamp = 2023-11-16T17:42:29.701198600, HistogramTests:histogramRange =
     *                               |-----------------------jqwik-----------------------
     * tries = 1000                  | # of calls to property
     * checks = 287                  | # of not rejected calls
     * generation = RANDOMIZED       | parameters are randomly generated
     * after-failure = SAMPLE_FIRST  | try previously failed sample, then previous seed
     * when-fixed-seed = ALLOW       | fixing the random seed is allowed
     * edge-cases#mode = MIXIN       | edge cases are mixed in
     * edge-cases#total = 20         | # of all combined edge cases
     * edge-cases#tried = 20         | # of edge cases tried in current run
     * seed = -2779569602006570398   | random seed to reproduce generated values
     *
     * Process finished with exit code 0
     *
     * das Problem ist,nur Minderheit der gesamte Versuchen hat die Assumption gepasst(es ist ein Problem wegen Verlust der Effizienz)
     * d.h.,für die meistens Fälle,die value kommt überhaupt nicht in data vor
     * Dies kännen wir beheben,indem wir die Intrange noch verkleinern z.B setze max=11 in beide Intrange
     * aber damit verkleinert auch die Path Coverage des Tests
     */
}
