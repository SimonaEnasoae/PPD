package com.add;

import com.general.BigNumber;
import com.general.Utils;

import java.io.File;
import java.io.IOException;

public class MainParalelV2 {
    public static  int P ;

    public static Thread[] threads;
    private static  int MAX ;
    public static int result[];
    public static int C[];
    public static int E[];

    public static void main(String[] args) throws IOException {
        MAX= Integer.parseInt(args[1]);
        P= Integer.parseInt(args[0]);

        E = new int[MAX];
        result = new int[MAX];
        C = new int[MAX];
        threads = new Thread[P];

        BigNumber bn1 = Utils.read("test.txt",0);
        BigNumber bn2 = Utils.read("test.txt",1);
        Utils.echilibrate(bn1,bn2);

        long startTime = System.nanoTime();

        //first part
        int len = bn1.getSize();
        int nrOp = len/P;
        int rest = len%P;
        int start = 0;

        for(int i=0;i<P;i++){
            int finish = start+nrOp;
            if(rest>0){
                finish++;
                rest--;
            }
            threads[i] = new WorkerP1(start,finish,bn1.getV(),bn2.getV(),C);
            start = finish;
            threads[i].start();
        }
        joinThreads();

        //second part
        nrOp = len/P;
        rest = len%P;
        start = 0;

        for(int i=0;i<P;i++){
        int finish = start+nrOp;
        if(rest>0){
            finish++;
            rest--;
        }
        threads[i] = new WorkerP2(start,finish,C,E);
        start = finish;
        threads[i].start();
        }
        joinThreads();

        //third part
        nrOp = len/P;
        rest = len%P;
        start = 0;

        for(int i=0;i<P;i++){
            int finish = start+nrOp;
            if(rest>0){
                finish++;
                rest--;
            }
            threads[i] = new WorkerP3(start,finish,bn1.getV(),bn2.getV(),E,result);
            start = finish;
            threads[i].start();
        }
        joinThreads();

        int n;
        if(E[bn1.getSize()-1]==1){
            n=bn1.getSize()+1;
            result[n-1]=1;
        }else{
            n=bn1.getSize();
        }
        BigNumber resultNumber = new BigNumber(result,n);
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);
        Utils.writeNumber(resultNumber,"f3.txt");
        File f1 =new File("f1.txt");
        File f2= new File("f3.txt");
        Boolean val =Utils.compareFile(f1,f2);
        System.out.println(val);
    }

    private static void joinThreads() {
        for (int i = 0; i < P; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
