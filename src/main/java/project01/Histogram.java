package project01;

import java.util.*;


public class Histogram {
    int[] frequency;
    int min, max;
    //min/max:minimale/maximale wert in data

    //hier konstruktor
    public Histogram(List<Integer> data) {
        //erwartet:darstellung min/max wert in data
        //need to ensure date is not empty otherwise we have CannotResolveParameterException in property base test
        if (!data.isEmpty()) {
            max = Collections.max(data);
            min = Collections.min(data);
        }


        //frequency=[min,min+1,min+2,...,max-1,max]
        frequency = generateNullArray(max - min + 1);

        for (int value : data) {
            //we are looking for IndexOfValue in histogram then we run frequency[IndexOfValue]++;

            int index = value - min;
            frequency[index]++;
        }
    }

    private boolean contains(int target, ArrayList<Integer> array) {
        for (int num : array) {
            if (num == target) {
                return true;
            }
        }
        return false;

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
        //[min,min+1,...,max-1,max]

        if (0 <= index && index < frequency.length) //筛选出有效index
            return frequency[index]; //也就是return value在data中出现的次数(frequency)
        else
            return 0;
    }
}
