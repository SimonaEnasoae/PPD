package com;

import com.add.Brain;
import com.general.BigNumber;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testComputeTest1(){
        int array[];
        array = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int array1[];
        array1 = new int[] {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        BigNumber b1 = new BigNumber(array,8);
        BigNumber b2 = new BigNumber(array,8);
        BigNumber b3 = Brain.compute(b1,b2);
        for(int i=0;i<b1.getSize();i++){
            assertEquals(b3.getV()[i],array1[i]);
        }
    }
    @Test
    public void testComputeTest2(){
        int array[];
        array = new int[] {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        int array1[];
        array1 = new int[] {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};
        BigNumber b1 = new BigNumber(array,8);
        BigNumber b2 = new BigNumber(array,8);
        BigNumber b3 = Brain.compute(b1,b2);
        for(int i=0;i<b1.getSize();i++){
            assertEquals(b3.getV()[i],array1[i]);
        }
    }
}