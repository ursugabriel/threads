package com.cgm.internship.part2.completablefuture;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int nrOfFiles=100;
        Set<CompletableFuture<Double>> allFuturesSet = new HashSet<>();
        for(int i=1;i<=nrOfFiles;i++){
            CompletableFuture<Double> completableFuture = CompletableFuture.supplyAsync(new ThreadExample("C:\\dev\\exThreads/file" + i + ".csv"));
            allFuturesSet.add(completableFuture);
        }
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                allFuturesSet.toArray(new CompletableFuture[ allFuturesSet.size()]));

        allFutures.whenComplete((aDouble, throwable) -> System.out.println("All tasks completed!"));
        System.out.println("All tasks completed without when complete");
        CompletableFuture<List<Double>> allFutureResults =
                allFutures.thenApply(v-> allFuturesSet.stream().map(allFuturesResult->allFuturesResult.join()).collect(Collectors.toList()));
        System.out.println("All tasks completed without when complete2");

        CompletableFuture<Double> sumOfSales =
                allFutureResults.thenApply(futureList-> futureList.stream().mapToDouble(Double::intValue).sum());
        System.out.println("All tasks completed without when complete3");

        try {
            System.out.println(Math.round(sumOfSales.get() * 100.0) / 100.0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
