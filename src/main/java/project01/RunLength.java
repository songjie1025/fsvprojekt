package project01;

import java.util.ArrayList;
import java.util.List;

public class RunLength {
    public static <T> List<Run<T>> encode(List<T> input) {
        // Note: you may assume without checking that input contains no null elements.
        // Java will complain if you try something like this:
        //     if(input.contains(null))

        List<Run<T>> result = new ArrayList<>();
        
        // TODO: implement this method
        if (input.isEmpty()) return result;

        T prev = input.get(0);
        int count = 1;

        for (int i = 1; i < input.size(); i++) {
            T current = input.get(i);
            if (current.equals(prev)) {
                count++;
            } else {
                result.add(new Run<>(count, prev));
                count = 1;
                prev = current;
            }
        }
        result.add(new Run<>(count, prev));

        return result;
    }

    public static <T> List<T> decode(List<Run<T>> runs) {
        List<T> result = new ArrayList<>();

        // TODO: implement this method
        for (Run<T> run : runs) {
            for (int i = 0; i < run.count(); i++) {
                result.add(run.elem());
            }
        }

        return result;
    }

    public static Integer sum(List<Run<Integer>> runs) {
        // TODO: implement this method (you may peek)
        int totalSum = 0;
        for (Run<Integer> run : runs) {
            totalSum += run.count() * run.elem();
        }
        return totalSum;
    }
}
