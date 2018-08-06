package com.cgm.internship.part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NR_OF_FILES = 100;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= NR_OF_FILES; i++) {
            executorService.execute(new FileGenerator("C:\\dev\\exThreads/file" + i + ".csv"));
        }
        executorService.shutdown();
    }

}
