package com.add;

public class WorkerP1 extends Thread {
    private  int start;
    private  int finish;
    private  int[] a;
    private  int[] b;
    private  int[] C;

    public WorkerP1(int start, int finish, int[]a, int[]b, int[]C){
        this.start= start;
        this.finish = finish;
        this.a=a;
        this.b=b;
        this.C=C;
    }
    /**
     * compute the vector C from index -> start to finish
     */
    @Override
    public void run(){
        for(int i=start;i<finish;i++){
            int temp = a[i] + b[i] ;
            if(temp>9){
                C[i] = 1;
            }else if(temp == 9){
                C[i] = 2;
            }else {
                C[i] = 0;
            }
        }
    }
}
