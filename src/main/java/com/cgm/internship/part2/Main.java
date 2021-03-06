package com.cgm.internship.part2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int nrOfFiles=100;
        double sumOfSales = 0.0;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Set<Future<Double>> threadExecutors = new HashSet<>();
        for(int i=1;i<=nrOfFiles;i++){
            Future<Double> future=executorService.submit(new ThreadExecutorExample(i,"C:\\dev\\exThreads/file" + i + ".csv"));
            threadExecutors.add(future);
        }
        try {
            executorService.shutdown();
            for(Future<Double> sum: threadExecutors){
                sumOfSales+=sum.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Math.round(sumOfSales * 100.0) / 100.0);
    }
}
