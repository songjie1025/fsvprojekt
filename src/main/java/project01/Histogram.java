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
        frequency = generateNullArray(uniqueCount);

        for (int value : data) {
            int IndexOfValue = value - min;
            frequency[IndexOfValue]++;
        }
    }

    private static int[] generateNullArray(int length) {
        int[] NullArray = new int[length];

        for (int i = 0; i < length; i++) {
            NullArray[i] = 0;
        }

        return NullArray;

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
        //因为在histogram中的位置要从小到大(左->右)

        if (0 <= index && index < frequency.length) //筛选出有效index
            return frequency[index]; //也就是return value在data中出现的次数(frequency)
        else
            return 0;
    }
}
