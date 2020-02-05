package lab3.V1;

import lab3.others.Task;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue1 {

    private Queue<Task> queue = new ArrayDeque<Task>();

    public BlockingQueue1(){
    }

    public  void put(Task task) {

        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }

    }

    public  Task take() throws InterruptedException {
        Task item;
        synchronized (queue) {
            //while (queue.isEmpty()) {
                queue.wait();
            //}
            item = queue.poll();
            if(item!=null) {
                queue.notify();
            }

        }
        return item;

    }
    public Boolean isEmpty(){
        return this.queue.size()==0;
    }

}