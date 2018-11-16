package ru.vsu.amm.max_seven.tools;

public class FindMaxMultipleOfSeven {

    public static int findMaxMultipleOfSeven(int[] array, int start, int end) {
        int max = 1;
        for (int i = start; i < end; i++){
            if (mainCondition(max, array[i])){
                max = array[i];
            }
        }

        return max;
    }

    public static boolean mainCondition(int max, int elem) {
        return elem % 7 == 0 && ( max == 1 || elem > max);
    }
}
