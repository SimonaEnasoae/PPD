package lab3a;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//exponent coef
public class MainV2 {
    public static  int P =4;
    public static Thread[] threads;
    private static  int N=10;

    public static void main(String[] args) {
        threads = new Thread[P];
        //  lab3a.Util.generate(N,1000,10);
        Lock lock =new ReentrantLock();
        IListPol listV2=new ListV2(lock);
        int rest = N%P;
        int nrOp = N/P;
        int start = 0;
        for(int i=0;i<P;i++){
            int finish = start+nrOp;
            if(rest>0){
                finish++;
                rest--;
            }
            threads[i] = new Worker(start,finish,listV2);
            start = finish;
        }
        long startTime = System.nanoTime();
        for(int i=0;i<P;i++){
            threads[i].start();
        }
        for(int i=0;i<P;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);
        //listV2.printList();
        listV2.printInFile("out2.txt");

    }
}
