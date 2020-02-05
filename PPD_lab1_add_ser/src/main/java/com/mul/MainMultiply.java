package com.mul;

import com.add.Brain;
import com.add.Worker;
import com.general.BigNumber;
import com.general.Utils;

import java.io.IOException;

public class MainMultiply {
    public static void main(String[] args) throws IOException {
        BigNumber bn1 = Utils.read("test.txt",0);
        BigNumber bn2 = Utils.read("test.txt",1);
        long startTime = System.nanoTime();

        Utils.echilibrate(bn1,bn2);
        BigNumber result = BrainMultiply.multiply(bn1,bn2);

        long endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0);
        Utils.writeNumber(result,"f1.txt");
  }
}
