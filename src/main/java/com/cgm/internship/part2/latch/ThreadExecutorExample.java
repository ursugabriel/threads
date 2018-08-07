package com.cgm.internship.part2.latch;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class ThreadExecutorExample implements Runnable {
    private String pathName;
    private CountDownLatch latch;
    private int nameThread;
    static double SUM =0.0;

    public ThreadExecutorExample(int nameThread, String pathName, CountDownLatch latch) {
        this.nameThread = nameThread;
        this.pathName = pathName;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            File file = new File(pathName);
            file.createNewFile();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line=bufferedReader.readLine();
                while (line!=null){
                    SUM += Double.parseDouble(line.substring(line.lastIndexOf(' '),line.length())) * Double.parseDouble(line.substring(line.lastIndexOf(' '),line.length()-1));
                    line=bufferedReader.readLine();
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathName, true));
                bufferedWriter.write("");
                bufferedWriter.write("Total sum per file: "+Math.round(SUM * 100.0) / 100.0);
                bufferedWriter.flush();
                bufferedWriter.close();


            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            } catch (IOException e) {
                System.out.println("Writing error!");
            }
        } catch (InterruptedIOException ex) {
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +" finished!");
        latch.countDown();
    }
}
