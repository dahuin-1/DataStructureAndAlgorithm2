package Grade4.Algorithm.week2.Hash;

import java.util.HashMap;
import java.util.HashSet;

public class TestOpenAddLinear {

    static int dataSize = 500;
    static int chainingTableSize = 512;
    static int openTableSize = 523;
    static int interval = 50;
    static int maxKeyValue = 100000;

    public static void main(String[] args) {
        int[] data = new int[dataSize];
        HashSet<Integer> rdata = new HashSet<Integer>();

        while (rdata.size() < dataSize) {
            rdata.add((int) (Math.random() * maxKeyValue));
        }
        int k = 0;
        for (int d : rdata) {
            data[k] = d;
            k++;
        }

        //Open Addressing Linear

        //Insert

        int sumOfSuccess = 0;
        int sumOfFailure = 0;

        System.out.println(">>> OpenAddressing-Linear");
        OpenAddLinear myHash = new OpenAddLinear(chainingTableSize);
        int repeat = dataSize / interval;
        for (int j = 0; j < repeat; j++) {
            int start = j * interval;
            int end = (j + 1) * interval;
            sumOfFailure = 0;
            sumOfSuccess = 0;
            int maxCount = 0;
            for (int i = start; i < end; i++) {
                myHash.hashInsert(data[i]);
            }

            for (int i = start; i < end; i++) {
                int count = myHash.hashSearch(data[i]);
                if (count >= 0) {
                    sumOfSuccess += count;
                    if (count > maxCount) maxCount = count;
                } else {
                    sumOfFailure += count;
                    if ((-count) > maxCount) maxCount = -count;
                }
            }

            System.out.print("\n [Insert] Number of investigation : Success ( ~ " + (j + 1) + " * " + interval
                    + sumOfSuccess + " Max. Hop Count = " + maxCount);
            System.out.println(" Load Factor = " + myHash.loadfactor()
                    + " Average Hop Count = " + ((double) sumOfSuccess / interval));
        }

        //Search with existing set

        sumOfFailure = 0;
        sumOfSuccess = 0;
        int sucessCount = 0, failCount = 0;
        for (int j = 0; j < dataSize; j++) {
            int count = myHash.hashSearch(data[j]);
            if (count >= 0) {
                sumOfSuccess += count;
                sucessCount++;
            } else {
                sumOfFailure += count;
                failCount++;
            }
        }
        System.out.print("\n [Search1] Average Number of investigation : Success = " + sumOfSuccess
                + "(" + sucessCount + ")" + " Average Hop Count = " + ((double) sumOfSuccess / sucessCount)
                + " Failure = " + (-sumOfFailure) + "(" + failCount + ")"
                + " Average Hop Count = " + ((double) -sumOfFailure / failCount));
        // Search with new random numbers

        sumOfSuccess = 0;
        sumOfFailure = 0;
        sucessCount = 0;
        for (int j = 0; j <dataSize; j++) {
            int count = myHash.hashSearch((int)(Math.random()*maxKeyValue));
            if (count >= 0) {
                sumOfSuccess += count;
                sucessCount++;
            }
            else {
                sumOfFailure += count;
                failCount++;
            }
        }
        System.out.print("\n [Search1] Average Number of investigation : Success = " + sumOfSuccess
                + "(" + sucessCount + ")" + " Average Hop Count = " + ((double) sumOfSuccess / sucessCount)
                + " Failure = " + (-sumOfFailure) + "(" + failCount + ")"
                + " Average Hop Count = " + ((double) -sumOfFailure / failCount));
    }
}
