package ru.vsu.amm.max_seven.custom_thread;

import ru.vsu.amm.max_seven.tools.FindMaxMultipleOfSeven;

import java.util.concurrent.locks.ReentrantLock;

public class CustomThread extends Thread {
    int[] array;
    int start;
    int end;
    Integer globalResult;
    ReentrantLock lock;

    public CustomThread(int[] array, int start, int end, Integer globalResult, ReentrantLock lock) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.globalResult = globalResult;
        this.lock = lock;
    }

    @Override
    public void run() {
        int result = FindMaxMultipleOfSeven.findMaxMultipleOfSeven(array, start, end);
        try {
            lock.lock();
            if (FindMaxMultipleOfSeven.mainCondition(globalResult, result)) {
                globalResult = result;
            }
        } finally {
            lock.unlock();
        }
    }
}
