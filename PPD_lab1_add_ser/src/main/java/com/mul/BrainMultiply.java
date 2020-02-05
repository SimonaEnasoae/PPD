package com.mul;

import com.general.BigNumber;

public class BrainMultiply {
    public static BigNumber multiply(BigNumber b1, BigNumber b2){
        int[] result= new int[b1.getSize()+b2.getSize()+1];
        int v[] = b1.getV();
        int w[] = b2.getV();
        for(int i=0;i<b1.getSize();i++){
            for(int j=0;j<b2.getSize();j++){
                int temp = result[i+j]+v[i]*w[j];
                result[i+j] = temp%10;
                result[i+j+1]=result[i+j+1]+temp/10;
            }
        }
        int n;
        if(result[b1.getSize()+b2.getSize()-1]!=0){
            n=b1.getSize()+b2.getSize();
        }
        else{
            n=b1.getSize()+b2.getSize()-1;
        }
        BigNumber r =new BigNumber(result,n);
        return r;
    }
}
