package com.add;

import com.general.BigNumber;

public class Brain {
    /**
     * compute 2 big numbers
     * @param first the first number
     * @param second the second number
     * @return the sub between the numbers
     */
    public static BigNumber compute(BigNumber first, BigNumber second){
        int[] firstNumber = first.getV();
        int[] secondNumber = second.getV();
        int[] result = new int[first.getSize()+1];
        for(int i=0;i<first.getSize();i++){
//            if(i==1){
//                System.out.println(result[i]);
//            }
            int temp = firstNumber[i]+secondNumber[i]+result[i];
            result[i]=temp%10;
//            if(i==1){
//                System.out.println(firstNumber[i]);
//                System.out.println(secondNumber[i]);
//                System.out.println(result[i]);
//            }
            result[i+1]=temp/10;
        }
        int n;
        if(result[first.getSize()]!=0){
            n= first.getSize()+1;
        }else{
            n=first.getSize();
        }
        return new BigNumber(result,n);
    }
}
