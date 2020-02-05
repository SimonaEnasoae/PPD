package lab3.others;

import lab3a.IListPol;
import lab3a.ListV1;

import java.io.*;

public class MainTest {

    public static void main(String[] args) {
        BlockingQueue queue =new BlockingQueue();
        IListPol listPol =new ListV1();

        int nThreads =4;
        Thread[] threads = new Writer[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Writer(queue);
            threads[i].start();
        }
        Thread reader=new Reader(listPol,queue);
        reader.run();
        try {
            reader.join();
            for (int i = 0; i < nThreads; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listPol.printInFile("out4.txt");

    }
    public static class Reader extends Thread {
        IListPol list;
        BlockingQueue queue;
        public Reader(IListPol list,BlockingQueue queue){
            this.list=list;
            this.queue=queue;
        }
        public void run(){
            for(int i=0;i<2;i++) {
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
                        // System.out.println(array[0] + " " + array[1]);
                        Monom monom=new Monom(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
                        Task task =new Task(list,monom);
                        //synchronized (queue) {
                        queue.put(task);
                        //queue.notify();
                        //}
                        line = reader.readLine();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //to do threadpool
        }
    }
    public static class Writer extends Thread {
        BlockingQueue queue;
        public Writer(BlockingQueue queue){
            this.queue=queue;
        }
        public void run(){
            Task task;
            while (true) {
                try {
                    task = queue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
