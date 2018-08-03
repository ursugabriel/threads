package com.cgm.internship.part1;

import org.apache.commons.lang.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class FileWriter implements Runnable {


    @Override
    public void run() {

        String pathName = "C:\\dev\\exThreads/file" + i + ".csv";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathName, true))) {
            bufferedWriter.write(getRandomString(), getRandomDate(), getRandomUnitSold(), getRandomPricePerUnit());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File  not found!");
        } catch (IOException e) {
            System.out.println("Writing error!");
        }
    }

    private String getRandomDate() {
        return RandomStringUtils.random(4, false, true) + "-" + RandomStringUtils.random(2, false, true) + "-" + RandomStringUtils.random(2, false, true);
    }

    public String getRandomString() {
        return RandomStringUtils.random(2, false, true);
    }

    public Integer getRandomUnitSold() {
        Random random = new Random();
        return random.nextInt(40);
    }

    public Double getRandomPricePerUnit() {
        Random r = new Random();
         return  r.nextDouble()
    }
}
