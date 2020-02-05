package com.mul;

import com.add.Brain;
import com.general.BigNumber;
import com.general.Utils;

import java.io.File;
import java.io.IOException;

public class MainParalel {
    public static  int P ;

    public static Thread threads[];
    private static  int MAX;
    public static int result[][];
    public static int resultEnd[];
    public static void main(String[] args) throws IOException {

        MAX= Integer.parseInt(args[1]);
        //P= Integer.parseInt(args[0]);
        P=4;
        resultEnd = new int[MAX];
        result = new int[P][MAX];
        threads = new Thread[P];
        BigNumber bn2 = Utils.read("test.txt",1);
        BigNumber bn1 = Utils.read("test.txt",0);
        Utils.echilibrate(bn1,bn2);

        int len = bn1.getSize();

        int rest = len%P;
        int nrOp = len/P;
        int start = 0;
        for(int i=0;i<P;i++){
            int finish = start+nrOp;
            if(rest>0){
                rest--;
                finish++;
            }
            threads[i] = new WorkerMultiply(start,finish,bn1.getV(),bn2.getV(),result[i],bn2.getSize());
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
        int n;
        if(result[P-1][bn1.getSize()+bn2.getSize()-1]!=0){
            n=bn1.getSize()+bn2.getSize();
        }
        else{
            n=bn1.getSize()+bn2.getSize()-1;
        }
        for(int i=0;i<n;i++) {
            int temp =resultEnd[i];
            for(int j=0;j<P;j++){
                temp+=result[j][i];
            }
            resultEnd[i]= temp%10;
            resultEnd[i+1]=temp/10;
        }
        BigNumber r =new BigNumber(resultEnd,n);
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);

        Utils.writeNumber(r,"f2.txt");
        File f1 =new File("f1.txt");
        File f2= new File("f2.txt");
        Boolean val =Utils.compareFile(f1,f2);
        System.out.println(val);
    }
}
