package com.cgm.internship.part1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MiddleMan {
    public ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
    public Boolean continueCreating=Boolean.TRUE;

    public void put(Integer data) throws InterruptedException {
        this.queue.put(data);
    }

    public Integer get() throws InterruptedException {
        return this.queue.poll(1, TimeUnit.SECONDS);
    }
}
