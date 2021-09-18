package Grade4.Algorithm.week2.Hash;

/*
이차조사 방식
collision 발생 시 +x^2 으로 제곱수를 증가시킴

문제점
second clustering 발생(2차 군집화)
비어있는 공간이 있음에도 건너뛰어 저장실패
 */
public class OpenAddQuadratic {

    int nOfHops = 0;
    double threshold = 0.99;

    int[] keys;
    //int[] vals;
    int tableSize; //maxSize
    int numberOfItems; //currentSize

    public OpenAddQuadratic(int capacity) {
        numberOfItems = 0;
        tableSize = capacity;
        keys = new int[tableSize];
       // vals = new int[tableSize];
    }

    public boolean contains(int key) {
        hashSearch(key);
        return true;
    }

    private int hashFunction(int key) {
        return key % tableSize;
    }

    public int hashInsert(int key) {
        int temp = hashFunction(key);
        int i = temp;
        int j = 1;
        do {
            if (key == 0) {
                keys[i] = key;
             //   vals[i] = val;
                numberOfItems++;
                nOfHops = 1;
                return nOfHops;
            }
            if (keys[i] == key) {
               // vals[i] = val;
                nOfHops = 1;
                return nOfHops;
            }
            i = (i + j * j++) % tableSize;
        }
        while (i != temp);
        return nOfHops;
    }

    //key로 val 찾기
    public int hashSearch(int key) {
        int i = hashFunction(key);
        int j = 1;
        nOfHops = 1;
        while (keys[i] != 0) {
            if (keys[i] == key) {
                return key;
            }
            i = (i + j * j++) % tableSize;
            System.out.println("i" + i);
        }
        return -key;
    }

    public String hashDelete(int key) {
        if (!contains(key)) {
            return "key가 없습니다";
        }
        int i = hashFunction(key);
        int j = 1;
        while (key != keys[i]) {
            i = (i + j * j++) % tableSize;
        }
      //  keys[i] = vals[i] = null;
        keys[i] = 0;
        for (i = (i + j * j++) % tableSize; keys[i] != 0; i = (i + j * j++) % tableSize) {
            int temp1 = keys[i];
           // String temp2 = vals[i];
           // keys[i] = vals[i] = null;
            keys[i] = 0;
            numberOfItems--;
            hashInsert(temp1);
        }
        return "삭제완료";
    }

    public double loadfactor() {
        return (double) numberOfItems / tableSize;
    }

    public void showTable() {
        System.out.println("Current Hash Table");
        for (int i = 0; i < tableSize; i++) {
            System.out.println(keys[i] + " ");
        }
        System.out.println();
    }
}
