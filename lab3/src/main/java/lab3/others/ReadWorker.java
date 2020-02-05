package lab3.others;

import lab3a.IListPol;

import java.io.*;

public class ReadWorker extends Thread {
    private final BlockingQueue queue;
    private final ThreadPool threadPool;
    private int nrFiles;
    private IListPol listPol;
    public ReadWorker(ThreadPool threadPool,BlockingQueue queue, IListPol listPol, int nrFiles){
        this.threadPool=threadPool;
        this.queue=queue;
        this.nrFiles=nrFiles;
        this.listPol=listPol;
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
                    Task task =new Task(listPol,monom);
                    queue.put(task);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            threadPool.interupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i'm done too");
    }
}
