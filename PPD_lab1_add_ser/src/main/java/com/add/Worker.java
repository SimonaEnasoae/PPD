package com.add;

public class Worker extends Thread {
    private  int start;
    private  int finish;
    private  int[] a;
    private  int[] b;
    private  int[] result;
    private  int[] transport;

    public Worker(int start, int finish, int[]a, int[]b, int[]result,int transport[]){
        this.start= start;
        this.finish = finish;
        this.a=a;
        this.b=b;
        this.result=result;
        this.transport = transport;
    }

    /**
     * compute the sum from index -> start to finish and the carry
     */
    @Override
    public void run(){
        for(int i=start;i<finish;i++){
            result[i] = (a[i] + b[i] )% 10;
            transport[i+1] = (a[i] + b[i] )/ 10;
        }
    }
}
