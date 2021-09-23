package Grade4.Algorithm.week3.select;

public class SelectTest {

    int recursiveCount = 0;

    private void resetCount() {
        recursiveCount = 0;
    }

    private int getCount() {
        return recursiveCount;
    }
    
    public int select(int [] data, int p, int r, int i) {
        recursiveCount++;

        if (p > r) {
            System.out.println("Invalid argument!");
            return -1;
        }
        if (p == r) {
            return data[p];
        }
        int q = partition(data, p, r);
        int k = q-p; //안해도 되는데 뒤에 나오는 수식을 간단히 하려고 한다
        if (i < k)
            return select(data, p, q-1, i);
        else if (i==k)
            return data[q];
        else
            return select(data, q+1, r, i-(q-p+1));
    }

    private int partition(int[] data, int p, int r) {
        int pivot = p;

        int left = p;
        int right = r;

        while (true) {
            while (data[left] < data[pivot] && left < right) left++;
            while (data[right] > data[pivot] && right > left) right--;
            if (left < right) swapData(data, left, right);
            else break;
        }
        swapData(data, pivot, right);
        return right;
    }

    private void swapData(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    //-----------------------------------------
    public int linearSelect(int[] data, int p, int r, int i) {
        recursiveCount++;

        if (p > r) {
            System.out.println("Invalid argument!");
            return -1;
        }
        if (p == r) {
            return data[p];
        }
        int q = linearpartition(data, p, r);
        int k = q - p; //안해도 되는데 뒤에 나오는 수식을 간단히 하려고 한다
        if (i < k)
            return linearSelect(data, p, q-1, i);
        else if (i==k)
            return data[q];
        else
            return linearSelect(data, q+1, r, i-(q-p+1));
    }

    private int linearpartition(int[] data, int p, int r) {
        int pValue = median(data,p,r);
        int index=0;
        for (int i = p; i <= r; i++) {
            if(data[i] == pValue) {
                index = i;
                break;
            }
        }

        swapData(data, r, index);
        int pivot = r;

        int left = p;
        int right = r;

        while (true) {
            while (data[left] < data[pivot] && left < right) left++;
            while (data[right] > data[pivot] && right > left) right--;
            if (left < right) swapData(data, left, right);
            else break;
        }
        swapData(data, pivot, right);
        return right;
    }

    private int median(int[] data, int p, int r) {
        if((r-p+1)<=5)
            return median5(data, p, r);
        float f = r-p+1;
        int arrSize = (int) Math.ceil(f/5);
        int[] medianArr = new int[arrSize];
        for (int i = 0; i <arrSize ; i++) {
            medianArr[i] = median5(data, p+5*i, (int)Math.min(p+5*i+4, r));
        }
        return median(medianArr, 0, arrSize-1);
    }

    private int median5(int[] data, int p, int r) {
        if(p==r)
            return data[p];
        sort5(data, p, r);
        return data[p+(int)((r-p+1)/2)];
    }

    private void sort5(int[] data, int p, int r) {
        for (int i = 0; i < r; i++) {
            for (int j = i+1; j <= r; j++) {
                if (data[i] > data[j])
                    swapData(data,i,j);
            }

        }
    }


    public static void main(String[] args) {
        int[] data = {5, 27, 24, 6, 35, 3, 7, 8, 18, 71, 77, 9, 11, 32, 21, 4};
        
        SelectTest s = new SelectTest();
        for (int i = 0; i < data.length; i++) {
            System.out.print(s.select(data, 0, data.length-1, i)+"  ");
        }
        System.out.println();
        System.out.println("# of Recursive Calls of Select = "+s.getCount());
        
        s.resetCount();
        for (int i = 0; i <data.length; i++) {
            System.out.print(s.linearSelect(data, 0, data.length-1, i)+"  ");
        }
        System.out.println();
        System.out.println("# of Recursive Calls of LinearSelect = "+s.getCount());
    }


}
