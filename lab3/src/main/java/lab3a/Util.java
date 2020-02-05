package lab3a;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

public class Util {
    /**
     *
     * @param k nr de fisiere
     * @param n nr de numere
     * @param p coef maxim dorit
     */
    public static void generate(int k, int n, int p) {
        Random rand = new Random();

        for (int i = 0; i < k; i++) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab3\\src\\main\\resources\\dir\\file" + i + ".txt"), StandardCharsets.UTF_8))) {
                for (int j = 0; j < n; j++) {
                    int rand_int1 = rand.nextInt(p)+1;
                    int rand_int2 = rand.nextInt(100)+1;
                    writer.write(rand_int1 + " " + rand_int2 + "\n");
                }

            } catch (IOException ex) {
                System.out.println("error");
            }


        }
    }

    public static void read(File file) {
//        BufferedReader reader;
//        int[] arrayInt = new int[0];
//        String line;
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            line = reader.readLine();
//            while (line != null) {
//                String[] array;
//                array = line.split(" ");
//                System.out.println(array[0] + " " + array[1]);
//                line = reader.readLine();
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static void readFiles(int k){
        for(int i=0;i<k;i++){
            File f =new File("C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab3\\src\\main\\resources\\dir\\file" + i + ".txt");
            read(f);
            System.out.println(" ");
        }
    }
}