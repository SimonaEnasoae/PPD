package com.add;

public class WorkerP3 extends Thread {
    private  int start;
    private  int finish;
    private  int[] a;
    private  int[] b;
    private  int[] E;
    private  int[] result;

    public WorkerP3(int start, int finish, int[] a, int[] b, int[] E, int[] result){
        this.start= start;
        this.finish = finish;
        this.a=a;
        this.b=b;
        this.E=E;
        this.result=result;
    }
    /**
     * compute the sum from index -> start to finish
     */
    @Override
    public void run(){
        for(int i=start;i<finish;i++){
            if(i!=0) {
                if(E[i-1]==1) {
                    result[i] = (a[i] + b[i] + E[i - 1]) % 10;
                }else {
                    result[i] = (a[i] + b[i]) % 10;
                }
            }else {
                result[i] = (a[i] + b[i]) % 10;
             }
        }
    }
}
