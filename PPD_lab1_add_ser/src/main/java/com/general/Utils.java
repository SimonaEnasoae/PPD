package com.general;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.util.Arrays;

public class Utils {
    /**
     * write generated numbers in a file
     *
     * @param fileName the name of file
     * @param size how many numbers to be generated
     * @param min the minimum number
     * @param max the maximum number
     */
    public static void generateNumbersToFile(String fileName, int size,int min,int max){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for (int counter = size; counter > 0; counter--) {
                int lenght = min + (int) (Math.random() * (max - min));
                int number = 1+(int) (Math.random() * 9);
                writer.print(number);
                for (int index=0;index<lenght-1;index++){
                    number = (int) (Math.random() * 10);
                    writer.print(number);
                }
                writer.print("\n");

            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**
     * read a big number from a file
     * @param file  the file's name
     * @param index the line in the file
     * @return the big number
     */
    public static BigNumber read(String file, int index){
        BufferedReader reader;
        int[] arrayInt = new int[0];
        try {
            reader = new BufferedReader(new FileReader(file));
            // read next line
            String line = reader.readLine();
            String [] array;
            if(index == 1){
                line = reader.readLine();
                array = line.split("");
                arrayInt = Arrays.asList(array).stream().mapToInt(Integer::parseInt).toArray();
            }else{
                array = line.split("");
                arrayInt = Arrays.asList(array).stream().mapToInt(Integer::parseInt).toArray();
            }
            ArrayUtils.reverse(arrayInt);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BigNumber(arrayInt,arrayInt.length);
    }

    /**
     * add zeros a the beginning of the smaller number to have the same length as the other one
     * @param first the first big number
     * @param second the second big number
     */
    public static void echilibrate(BigNumber first,BigNumber second){
        int max = first.getSize();
        if(max<second.getSize()){
            int []array = new int[second.getSize()-first.getSize()];
            Arrays.fill(array, 0);
            int[] arrayFilled= ArrayUtils.addAll(first.getV(),array);
            first.setV(arrayFilled);
            first.setSize(second.getSize());
        }else{
            int []array = new int[max-second.getSize()];
            Arrays.fill(array, 0);
            int[] arrayFilled= ArrayUtils.addAll(second.getV(),array);
            second.setV(arrayFilled);
            second.setSize(max);
        }

    }

    /**
     *  save a number in a file
     * @param bigNumber the big number
     * @param file the file name
     */
    public static void writeNumber(BigNumber bigNumber, String file) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.println(bigNumber.number());
        writer.close();
    }
    /**
     * compare 2 files, true if the files have the same content, false otherwise
     * @param file1 the name of the first file
     * @param file2 the name of the second file
     * @return
     */
    public static boolean compareFile(File file1, File file2){

        BufferedReader reader1;
        BufferedReader reader2;
        try {
            reader1 = new BufferedReader(new FileReader(file1));
            reader2 = new BufferedReader(new FileReader(file2));
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            while (line1 != null && line2 != null) {
                // read next line
                if(!line1.equals(line2)){
                    return false;
                }
                line1 = reader1.readLine();
                line2 = reader2.readLine();
            }
            reader1.close();
            reader2.close();
            if(line1 == null && line2==null) {
                return true;
            }
            else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
