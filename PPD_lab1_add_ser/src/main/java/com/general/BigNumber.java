package com.general;


public class BigNumber {
    private int[] v;
    private int size;

    public BigNumber() {
        v = new int[100];
    }

    public BigNumber(int[] v, int size) {
        this.v = v;
        this.size = size;
    }

    public int[] getV() {
        return v;
    }

    public void setV(int[] v) {
        this.v = v;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        String s =" ";
        for(int index=0;index<size;index++){
            s =s+v[index]+" ";
        }
        s+=" ";
        return "BigNumber{" +
                "v=" + s +
                ", size=" + size +
                '}';
    }

    public String number() {
        String s ="";
        for(int index=size-1;index>=0;index--){
            s =s+v[index];
        }
        return s;
    }
}
