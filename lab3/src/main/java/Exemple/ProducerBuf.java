package Exemple;

import lab3.others.Monom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerBuf extends Thread {
    private Buffer buffer;
    private String name;
    private  int nrFiles;
    private static AtomicInteger val = new AtomicInteger(1);
    //private static int iteratii = 20;

    ProducerBuf(Buffer buffer,String name,int nrFiles) {
        this.buffer = buffer;
        this.name = name;
        this.nrFiles =nrFiles;
    }

    @Override
    public void run() {
        for(int i=0;i<nrFiles;i++) {
            File file = new File("C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab3\\src\\main\\resources\\dir\\file" + i + ".txt");
            BufferedReader reader;
            String line;
            try {
                reader = new BufferedReader(new FileReader(file));
                line = reader.readLine();
                while (line != null) {
                    String[] array;
                    array = line.split(" ");
                    Monom monom=new Monom(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
                    buffer.produce(monom,name);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Am terminat de produs " + name);

        buffer.setFinish(new AtomicBoolean(true));
    }
}
