package Grade4.Algorithm.week2.Hash;

import java.util.HashSet;

public class TestChaining {
    static int dataSize = 500;
    static int chainingTableSize = 512;
    static int openTableSize = 523;
    static int interval = 50;
    static int maxKeyValue = 100000;

    public static void main(String[] args) {
        int[] data = new int[dataSize];
        HashSet<Integer> rdata = new HashSet<Integer>();

        while (rdata.size() < dataSize) {
            rdata.add((int)(Math.random()*maxKeyValue));
        }
        int k = 0;
        for(int d : rdata) {
            data[k] = d;
            k++;
        }

        //Chaining

        //Insert

        int sumOfSuccess = 0;
        int sumOfFailure = 0;

        System.out.println(">>> Chaining");
        Chaining myHash = new Chaining(chainingTableSize);
        int repeat = dataSize / interval;
        for(int j = 0; j < repeat; j++) {
            int start = j * interval;
        }

    }

}
