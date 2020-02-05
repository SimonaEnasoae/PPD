package lab3.V1;

import lab3.others.BlockingQueue;
import lab3.others.Monom;
import lab3.others.Task;
import lab3a.IListPol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReadWorker1 extends Thread {
    private final Queue<Monom> queue;
    private final AtomicBoolean atomicBoolean;
    private int nrFiles;
    public ReadWorker1(AtomicBoolean atomicBoolean, Queue<Monom> queue, int nrFiles){
        this.atomicBoolean=atomicBoolean;
        this.queue=queue;
        this.nrFiles=nrFiles;
    }
    @Override
    public void run() {
        for(int i=0;i<nrFiles;i++) {
            File file = new File("C:\\Users\\Simona\\Desktop\\An 3\\PPD\\lab3\\src\\main\\resources\\dir\\file" + i + ".txt");
            BufferedReader reader;
           // int[] arrayInt = new int[0];
            String line;
            try {
                reader = new BufferedReader(new FileReader(file));
                line = reader.readLine();
                while (line != null) {
                    String[] array;
                    array = line.split(" ");
                    Monom monom=new Monom(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
                   // Task task =new Task(listPol,monom);
                    synchronized (queue) {
                        queue.add(monom);
                        queue.notifyAll();
                    }
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        atomicBoolean.set(true);
        System.out.println("i'm done too");
    }
}
