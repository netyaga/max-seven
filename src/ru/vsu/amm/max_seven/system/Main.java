package ru.vsu.amm.max_seven.system;

import ru.vsu.amm.max_seven.custom_thread.CustomThread;
import ru.vsu.amm.max_seven.tools.FindMaxMultipleOfSeven;
import ru.vsu.amm.max_seven.tools.Randomizer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    final static int ELEMS_PER_THREAD = 100;
    static int globalResult = 1;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Input array size:");
        Scanner sc = new Scanner(System.in);


        int arraySize = sc.nextInt();
        int[] array = Randomizer.getRandomArray(arraySize);

        int countThread = (arraySize % ELEMS_PER_THREAD) + 1;
        countThread = (countThread < 8)? countThread : 8;
        int actualElemPerThread = arraySize / countThread;

        List<Thread> listThread = new LinkedList<>();
        ReentrantLock lock = new ReentrantLock();
        for(int number = 1; number < countThread; number++)
            listThread.add(new CustomThread(array, (number - 1)*actualElemPerThread, number * actualElemPerThread, globalResult, lock));
        for (Thread thread: listThread)
            thread.start();

        int mainsResult = FindMaxMultipleOfSeven.findMaxMultipleOfSeven(array, (countThread - 1)*actualElemPerThread, arraySize);
        try{
            lock.lock();
            if(FindMaxMultipleOfSeven.mainCondition(globalResult, mainsResult)){
                globalResult = mainsResult;
            }
        }
        finally {
            lock.unlock();
        }

        for (Thread thread: listThread)
            thread.join();

        System.out.println("Generated array: ");
        for (int elem: array)
            System.out.printf("%d;", elem);
        if(mainsResult != 1)
            System.out.printf("\nMax elem: %d", mainsResult);
        else
            System.out.println("\nArray not contains multyple of seven elem");

    }

}
