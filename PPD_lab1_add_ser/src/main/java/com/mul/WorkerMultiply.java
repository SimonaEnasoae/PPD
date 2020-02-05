package com.mul;

public class WorkerMultiply extends Thread{
    private  int start;
    private  int finish;
    private  int[] v;
    private  int[] w;
    private  int[] result;
    private  int n;

    public WorkerMultiply(int start, int finish, int[]v, int[]w, int[]result,int n){
        this.start= start;
        this.finish = finish;
        this.v=v;
        this.w=w;
        this.result=result;
        this.n=n;
    }
    @Override
    public void run(){
        for(int i=start;i<finish;i++){
            for(int j=0;j<n;j++) {
                int temp = result[i+j]+v[i] * w[j];
                result[i+j] = temp%10;
                result[i+j+1] += temp/10;
            }
        }
    }
}
