package lab3.V1;

import lab3.others.BlockingQueue;
import lab3.others.Monom;
import lab3a.ListV1;
import lab3a.ListV2;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool1 {
    private final int nThreads;
    private final PoolWorker1[] threads;
    private final Queue<Monom> queue;
    private final ListV2 listV1;

    public ThreadPool1(AtomicBoolean atomicBoolean, int nThreads, Queue<Monom> queue, ListV2 list) {
        this.nThreads = nThreads;
        this.queue=queue;
        this.listV1=list;
        threads = new PoolWorker1[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker1(atomicBoolean,queue,listV1);
            threads[i].start();
        }
    }


    public void join() {
        for (int i = 0; i < nThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}