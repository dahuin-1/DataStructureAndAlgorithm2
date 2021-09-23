package Grade4.Algorithm.week3.select;

import java.util.Arrays;

public class SelectSort {

    public void selectionSort(int array[]) {
        int size = array.length;

        for (int step = 0; step < size - 1; step++) {
            int min = step;
            for (int i = step + 1; i < size; i++) {
                if (array[i] < array[min]) {
                    min = i;
                }
            }

            // swap
            int temp = array[step];
            array[step] = array[min];
            array[min] = temp;
        }
    }

    public static void main(String args[]) {
        int[] data = {20, 12, 10, 15, 2};
        SelectSort ss = new SelectSort();
        ss.selectionSort(data);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));
    }
}

