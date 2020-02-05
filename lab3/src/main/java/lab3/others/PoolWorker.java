package lab3.others;

public class PoolWorker extends Thread {
    BlockingQueue queue;
    public PoolWorker(BlockingQueue queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        Task task=null;
        Boolean running=true;
        while (running) {
            try {
                task = queue.take();
                task.run();
            } catch (InterruptedException e) {
               // System.out.println("i'm done");
                running=false;
            }
        }
    }
}