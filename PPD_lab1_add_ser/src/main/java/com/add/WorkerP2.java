package com.add;

public class WorkerP2 extends Thread{
    private  int start;
    private  int finish;
    private  int[] E;
    private  int[] C;

    public WorkerP2(int start, int finish, int[]C,int[]E){
        this.start= start;
        this.finish = finish;
        this.E=E;
        this.C=C;
    }
    /**
     * compute the vector E from index -> start to finish
     */
    @Override
    public void run(){
        for(int i=start;i<finish;i++){
           switch (C[i]) {
               case 1:
                   E[i] = 1;
                   break;
               case 0:
                   E[i] = 0;
                   break;
               case 2:
                   int index = i;
                   boolean checkCarry = false;
                   while (index >= 0) {
                       if (C[index] == 0) {
                           checkCarry = false;
                           index = -1;
                       } else if (C[index] == 1) {
                           checkCarry = true;
                           index = -1;
                       }
                       index--;
                   }
                   if(checkCarry){
                       E[i]=1;
                   }else {
                       E[i]=2;
                   }
                   break;
               default:
                   break;
           }
        }
    }
}
