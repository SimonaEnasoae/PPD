package lab3.others;

import lab3a.IListPol;
import lab3a.ListV1;

public class MainV1 {
    public static  int P =4;
    private static  int N=100;

    public static void main(String[] args) {
        BlockingQueue queue =new BlockingQueue();
        IListPol listPol =new ListV1();
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
        listPol.printInFile("out3.txt");

    }
}
