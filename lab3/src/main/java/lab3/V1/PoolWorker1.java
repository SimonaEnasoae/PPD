package lab3.V1;

import lab3.others.BlockingQueue;
import lab3.others.Monom;
import lab3.others.Task;
import lab3a.ListV1;
import lab3a.ListV2;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class PoolWorker1 extends Thread {
    private final AtomicBoolean atomicBoolean;
    Queue<Monom> queue;
    ListV2 listV1;
    public PoolWorker1(AtomicBoolean atomicBoolean, Queue<Monom> queue, ListV2 list) {
        this.atomicBoolean = atomicBoolean;
        this.queue = queue;
        this.listV1=list;
    }
    @Override
    public void run() {
        while (!atomicBoolean.get()) {
            synchronized (queue) {
                try {
                    queue.wait();
                    Monom m = queue.poll();
                    if (m != null) {
                        queue.notify();
                        listV1.insert(m.getExp(),m.getCoef());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}