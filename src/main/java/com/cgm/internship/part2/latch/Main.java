package com.cgm.internship.part2.latch;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int nrOfFiles=100;
        CountDownLatch latch = new CountDownLatch(nrOfFiles);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(int i=1;i<=nrOfFiles;i++){
            executorService.submit(new ThreadExecutorExample(i,"C:\\dev\\exThreads/file" + i + ".csv",latch));
        }
        System.out.println("Waiting children to complete...");
        try {
            latch.await();
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " has finished!");
        System.out.println(Math.round(ThreadExecutorExample.SUM * 100.0) / 100.0);
    }
}
