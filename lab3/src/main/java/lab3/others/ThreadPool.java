package lab3.others;

public class ThreadPool {
    private final int nThreads;
    private final PoolWorker[] threads;
    private final BlockingQueue queue;


    public ThreadPool(int nThreads,BlockingQueue queue) {
        this.nThreads = nThreads;
        this.queue=queue;
        threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker(queue);
            threads[i].start();
        }
    }

    public void interupt() throws InterruptedException {
        while (!queue.isEmpty()){
            Thread.sleep(1000);
        }
        for (int i = 0; i < nThreads; i++) {
            threads[i].interrupt();
        }
    }
}