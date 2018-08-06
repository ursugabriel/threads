package com.cgm.internship.part1;

import java.io.*;
import java.time.LocalDate;
import java.util.Random;

public class FileGenerator implements Runnable {
    public int numberOfRows = 50;
    String[] productList = {"Apple", "Juice", "Pen", "Can", "Bottle", "Book", "File", "Paper"};
    String pathName;

    public FileGenerator(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public void run() {
        try {
                File file = new File(pathName);
                file.createNewFile();
                System.out.println("Created file " + pathName);
                for (int indexRows = 1; indexRows < numberOfRows; indexRows++) {
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new java.io.FileWriter(file, true))) {
                        bufferedWriter.write(getRandomString() + ", " + getRandomDate() + ", " + getRandomUnitSold() + ", " + getRandomPricePerUnit());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (FileNotFoundException e) {
                        System.out.println("File  not found!");
                    } catch (IOException e) {
                        System.out.println("Writing error!");
                    }
                }
            Thread.sleep(50);
        } catch (InterruptedIOException ex) {
            ex.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private LocalDate getRandomDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2018, 8, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        return randomBirthDate;
    }

    public String getRandomString() {
        Random random = new Random();
        return productList[random.nextInt(productList.length)];
    }

    public Integer getRandomUnitSold() {
        Random random = new Random();
        return random.nextInt(40);
    }

    public Double getRandomPricePerUnit() {
        Random r = new Random();
        double price = 10.0 + r.nextDouble() * 2.0;
        return Math.round(price * 100.0) / 100.0;
    }

}
