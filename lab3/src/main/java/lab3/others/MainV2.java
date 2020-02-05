package lab3.others;

import lab3a.IListPol;
import lab3a.ListV2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainV2 {
    public static  int P =4;
    private static  int N=100;

    public static void main(String[] args) {
        BlockingQueue queue =new BlockingQueue();
        Lock lock =new ReentrantLock();
        IListPol listPol =new ListV2(lock);
        ThreadPool threadPool =new ThreadPool(P,queue);
        long startTime = System.nanoTime();

        ReadWorker readWorker =new ReadWorker(threadPool,queue,listPol,N);
        readWorker.start();

        try {
            readWorker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);
        listPol.printInFile("out4.txt");

        //System.out.println("hello");
    }
}
