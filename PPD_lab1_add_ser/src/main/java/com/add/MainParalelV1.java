package com.add;

import com.general.BigNumber;
import com.general.Utils;

import java.io.File;
import java.io.IOException;

public class MainParalelV1 {
    public static  int P ;

    public static Thread threads[];
    private static  int MAX;
    public static int result[];
    public static int transport[] ;

    public static void main(String[] args) throws IOException {
        MAX= Integer.parseInt(args[1]);
        P= Integer.parseInt(args[0]);
        threads = new Thread[P];
        result = new int[MAX];
        transport = new int[MAX];
        BigNumber bn1 = Utils.read("test.txt",0);
        BigNumber bn2 = Utils.read("test.txt",1);
        Utils.echilibrate(bn1,bn2);
        int len = bn1.getSize();
        long startTime = System.nanoTime();
        int nrOp = len/P;
        int rest = len%P;
        int start = 0;

        for(int i=0;i<P;i++){
            int finish = start+nrOp;
            if(rest>0){
                finish++;
                rest--;
            }
            threads[i] = new Worker(start,finish,bn1.getV(),bn2.getV(),result,transport);
            start = finish;
            threads[i].start();
        }

        for(int i=0;i<P;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<bn1.getSize()+1;i++){
            int temp = result[i]+transport[i];
            result[i] = temp%10;
            transport[i+1]=transport[i+1]+temp/10;
        }
        int n;
        if(transport[bn1.getSize()]==1){
            n=bn1.getSize()+1;
        }else{
            n=bn1.getSize();
        }
        BigNumber resultNumber = new BigNumber(result,n);
        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);
        Utils.writeNumber(resultNumber,"f2.txt");
        File f1 =new File("f1.txt");
        File f2= new File("f2.txt");
        Boolean val =Utils.compareFile(f1,f2);
        System.out.println(val);
    }
}