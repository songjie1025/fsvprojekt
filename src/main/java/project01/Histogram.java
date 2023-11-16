package project01;

import java.util.*;


public class Histogram {
    int[] frequency;
    int min, max;
    //min/max:minimale/maximale wert in data

    //hier konstruktor
    public Histogram(List<Integer> data) {
        //erwartet:darstellung min/max wert in data
        max= Collections.max(data);
        min=Collections.min(data);

        //the length of frequency should be the amount of unique numbers in data
        Set<Integer> uniqueSet = new HashSet<>(data);
        int uniqueCount = uniqueSet.size();
        frequency = generateRandomArray(uniqueCount);

        for (int value : data) {
            // TODO: update frequencies here
            // Hint: look at method count below
        }
    }

    private static int[] generateRandomArray(int length) {
        int[] randomArray = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomArray[i] = random.nextInt();
        }

        return randomArray;

    }

    // Note: this constructor is provided as a convenience,
    //       it calls the primary constructor above
    public Histogram(Integer... data) {
        this(Arrays.asList(data));
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    //期待的结果:在eingabe(data)中value出现了几次
    public int count(int value) {
        int index = value - min;

        if (0 <= index && index < frequency.length)
            return frequency[index];
        else
            return 0;
    }
}
