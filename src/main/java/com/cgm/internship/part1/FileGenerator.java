package com.cgm.internship.part1;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

public class FileGenerator  implements Runnable{

    @Override
    public void run() {
        try {
            for(Integer i=1;i<=5000;i++){
                String pathName="C:\\dev\\exThreads/file"+i+".csv";
                File file = new File(pathName);
                file.createNewFile();
            }
            System.out.println("Finished creating files!");
        }
        catch (InterruptedIOException ex){
            ex.printStackTrace();
        }

        catch(IOException io) {
            io.printStackTrace();
        }


    }
}
