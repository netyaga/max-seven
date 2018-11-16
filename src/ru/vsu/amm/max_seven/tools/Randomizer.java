package ru.vsu.amm.max_seven.tools;

import java.util.Random;

public class Randomizer {

    final static int RANDOM_RANGE = 100;

    public static int[] getRandomArray(int arraySize) {
        Random rnd = new Random();
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
            array[i] = rnd.nextInt(RANDOM_RANGE) - RANDOM_RANGE/2;
        return array;
    }
}
