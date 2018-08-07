package com.cgm.internship.part2.completablefuture;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ThreadExample implements Supplier<Double> {
    private String pathName;

    public ThreadExample(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public Double get() {
        double sum = 0.0;
        try {
            System.out.println(Thread.currentThread().getName()+" started!");
            File file = new File(pathName);
            file.createNewFile();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line=bufferedReader.readLine();
                while (line!=null){
                    sum += Double.parseDouble(line.substring(line.lastIndexOf(' '),line.length())) * Double.parseDouble(line.substring(line.lastIndexOf(' '),line.length()-1));
                    line=bufferedReader.readLine();
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathName, true));
                bufferedWriter.write("");
                bufferedWriter.write("Total sum per file: "+Math.round(sum * 100.0) / 100.0);
                bufferedWriter.write("");
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            } catch (IOException e) {
                System.out.println("Writing error!");
            }
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedIOException ex) {
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Math.round(sum * 100.0) / 100.0;
    }
}
