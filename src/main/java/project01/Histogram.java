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

        //the length of frequency should be the amount of unique numbers in data.
        // Initalize frequency with an array with that length,with value full of 0s
        /**
         * steps:1.turn data into arraylist
         * 2.create uniqueElemArrayList to store unique members of data
         * 3.for each elem in data: uniqueAL iterieren,add if not contained in it
         * 4.use size() to get its length
         */

        ArrayList<Integer> ArraylistFromData =  new ArrayList<>(data); //1.

        ArrayList<Integer> UniqueElemArrayList = new ArrayList<>(); //2.

        for (int element:ArraylistFromData){
            if(!contains(element,UniqueElemArrayList)){
                UniqueElemArrayList.add(element);
            }
        }

        int lengthOfFrequency = UniqueElemArrayList.size();

        frequency = generateNullArray(lengthOfFrequency);

        for (int value : data) {
            //we are looking for IndexOfValue in histogram then we run frequency[IndexOfValue]++;
            //IndexOfValue in histogram = IndexOfValue in uniqueElemArray defined above

            Collections.sort(UniqueElemArrayList);
            int IndexOfValue = UniqueElemArrayList.indexOf(value);

            frequency[IndexOfValue]++;
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
        //因为在histogram中的位置要从小到大(左->右)

        if (0 <= index && index < frequency.length) //筛选出有效index
            return frequency[index]; //也就是return value在data中出现的次数(frequency)
        else
            return 0;
    }
}
