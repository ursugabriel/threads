package com.cgm.internship.part2;

import java.io.*;
import java.util.concurrent.Callable;

public class ThreadExecutorExample implements Callable<Double> {
    private String pathName;

    public ThreadExecutorExample(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public Double call() {
        double sum = 0.0;
        try {
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

        return Math.round(sum * 100.0) / 100.0;
    }
}
