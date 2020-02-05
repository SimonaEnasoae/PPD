package com.general;

import java.io.IOException;

public class MainGenerate {
    public static void main(String[] args) throws IOException {
        Utils.generateNumbersToFile("test.txt",2,900,900);
    }
}
