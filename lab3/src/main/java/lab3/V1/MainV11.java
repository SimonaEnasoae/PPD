package lab3.V1;

import lab3.others.BlockingQueue;
import lab3.others.Monom;
import lab3.others.ReadWorker;
import lab3.others.ThreadPool;
import lab3a.IListPol;
import lab3a.ListV1;
import lab3a.ListV2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainV11 {
    public static  int P =4;
    private static  int N=10;

    public static void main(String[] args) {
        Queue<Monom> queue =new ArrayDeque<>();
        Lock lock =new ReentrantLock();
        ListV2 listPol =new ListV2(lock);
        AtomicBoolean atomicBoolean =new AtomicBoolean(false);
        ThreadPool1 threadPool =new ThreadPool1(atomicBoolean,P,queue,listPol);
        long startTime = System.nanoTime();

        ReadWorker1 readWorker =new ReadWorker1(atomicBoolean,queue,N);
        readWorker.start();

        try {
            readWorker.join();
            threadPool.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);
        listPol.printInFile("out5.txt");

//        System.out.println("hello");
    }
}
