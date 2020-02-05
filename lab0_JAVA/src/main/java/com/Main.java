package com;
import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        Utils.write(2,"hey");
        Utils.generateNumbersToFile("test.txt",2,1000000,1000000);
        File file1 = new File("test1.txt");
        File file2 = new File("test2.txt");
        System.out.println(Utils.compareFile(file1,file2));
    }

}