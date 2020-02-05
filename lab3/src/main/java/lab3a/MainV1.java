package lab3a;//exponent coef

public class MainV1 {
    public static  int P =4;
    public static Thread[] threads;
    private static  int N=1000;

    public static void main(String[] args) {
        threads = new Thread[P];
        //lab3a.Util.generate(N,1000,10);
        IListPol listV1=new ListV1();
        int rest = N%P;
        int nrOp = N/P;
        int start = 0;
        for(int i=0;i<P;i++){
            int finish = start+nrOp;
            if(rest>0){
                rest--;
                finish++;
            }
            threads[i] = new Worker(start,finish,listV1);
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
        listV1.printInFile("out1.txt");

    }
}
