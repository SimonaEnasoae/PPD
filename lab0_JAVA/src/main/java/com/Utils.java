package com;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

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
     * write in a xlxs file on the rowTable row the string
     * @param rowTabel the row of the file
     * @param data the string
     * @throws IOException if the file doesn't exist
     */
    public static void write(int rowTabel, String data) throws IOException {
        Workbook workbook = new XSSFWorkbook("poi-generated-file.xlsx"); // new HSSFWorkbook() for generating `.xls` file

        Sheet sheet;
        try {
            sheet = workbook.createSheet("first");
        }catch (Exception ex){
            sheet = workbook.getSheet("first");
        }

        Row row = sheet.createRow(rowTabel-1);

        row.createCell(0)
                .setCellValue(data);


        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx",true);
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();
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
    /**
     * write generated numbers in a file, numbers between min and max numbers
     *
     * @param fileName the name of file
     * @param size how many numbers to be generated
     * @param min the minimum number
     * @param max the maximum number
     */
//    public static void generateNumbersFile(String fileName, int size,BigNumber min,BigNumber max){
//        PrintWriter writer = null;
//        try {
//            writer = new PrintWriter(fileName, "UTF-8");
//            for (int counter = size; counter > 0; counter--) {
//                BigNumber bigNumber = generateNumber(min,max);
//                writer.println(bigNumber.number());
//            }
//            writer.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * generate a number between minimum and maximum
     *
     * @param min the minimum number
     * @param max the maximum number
     * @return the generated number
     */
//    public static BigNumber generateNumber(BigNumber min, BigNumber max) {
//        int number = min.size + (int) (Math.random() * ((max.size - min.size +1)));
//        int[] v = new int[100];
//        boolean flag = false;
//        if (number > min.size && number<max.size){
//            v[number-1] = 1+ (int) (Math.random() * 9);
//            for(int index=0; index<number-2; index++){
//                v[index] = (int) (Math.random() * 10);
//            }
//        }
//        else if (number == min.size) {
//            v[min.size - 1] = min.v[min.size - 1] + (int) (Math.random() * (9 - (min.v[min.size - 1])));
//            if (v[min.size - 1] != min.v[min.size - 1]) {
//                flag = true;
//            }
//            for (int index = min.size - 2; index >= 0; index--) {
//                if (flag == false && v[index - 1] != min.v[index - 1]) {
//                    flag = true;
//                }
//                if (flag == true) {
//                    v[index] = (int) (Math.random() * 10);
//                } else {
//                    v[index] = min.v[index] + (int) (Math.random() * (9 - (min.v[index])));
//                }
//            }
//        }
//        else {
//            v[max.size - 1] = max.v[max.size - 1] + (int) (Math.random() * (9 - (max.v[max.size - 1])));
//            if (v[max.size - 1] != max.v[max.size - 1]) {
//                flag = true;
//            }
//            for (int index = max.size - 2; index >= 0; index--) {
//                if (flag == false && v[index - 1] != max.v[index - 1]) {
//                    flag = true;
//                }
//                if (flag == true) {
//                    v[index] = (int) (Math.random() * 10);
//                } else {
//                    v[index] = (int) (Math.random() * max.v[index]);
//                }
//            }
//        }
//        BigNumber bigNumber = new BigNumber(v,number);
//        return bigNumber;
//    }

}
