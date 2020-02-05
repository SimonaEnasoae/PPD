package lab3a.V1;//exponent coef

public class MainV11 {
    public static  int P =4;
    public static Thread[] threads;
    private static  int N=10;

    public static void main(String[] args) {
        threads = new Thread[P];
       // lab3a.Util.generate(N,1000,10);
        ListV11 listV1=new ListV11();
        int rest = N%P;
        int nrOp = N/P;
        int start = 0;
        for(int i=0;i<P;i++){
            int finish = start+nrOp;
            if(rest>0){
                rest--;
                finish++;
            }
            threads[i] = new Worker1(start,finish,listV1);
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
        listV1.printInFile("out5.txt");

    }
}
