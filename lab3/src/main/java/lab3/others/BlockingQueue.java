package lab3.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue {

    private Queue<Task> queue = new LinkedList<Task>();
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();

    public BlockingQueue(){
    }

    public  void put(Task task) {

        lock.lock();
        try {

            queue.add(task);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public  Task take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                    notEmpty.await();
            }
            Task item = queue.remove();
            return item;
        } finally {
            lock.unlock();
        }
    }
    public Boolean isEmpty(){
        return this.queue.size()==0;
    }

}