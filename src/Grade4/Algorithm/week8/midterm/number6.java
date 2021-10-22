package Grade4.Algorithm.week8.midterm;
//60171620 김다흰
import java.util.*;

public class number6 {

    static class Plate {

        public int h, r, index;
        public int area;

        public Plate(int r, int h, int index) {
            this.r = r;
            this.h = h;
            this.index = index;
            area = r * r * h; //원의 넓이를 구할때 곱하는 루트는 생략하였습니다.
        }

        // 내 위에 박스를 올려 놓을 수 있는가?
        public boolean IsAbleToStackAbove(Plate b) {
            return index < b.index && area >= b.area;
           // i<j 이면 반드시 pi 는 pj 의 밑에만 놓일 수 있다. -> 내 인덱스가 위에 인덱스보다 작아야함
            //ri < rj 이면 pj 는 pi 의 위에 놓일 수 없다. (ri = rj 인 경우는 놓일 수 있음) => 내 넓이가 위 넓이보다 넓거나 같아야함
        }
    }

    static int maxStackHeightDP(Plate arr[], int n) {

        int result = 0;

        Plate[] data = new Plate[n];
        int[] maximumHeight = new int[n];

        for (int i = 0; i < n; ++i) {
            Plate plate = arr[i];
            data[i] = new Plate(plate.r, plate.h, plate.index);
        }

        for (int i = 0; i < n; ++i)
            maximumHeight[i] = data[i].h;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (data[j].IsAbleToStackAbove(data[i])) {
                    int temp = maximumHeight[j] + data[i].h;
                    maximumHeight[i] = Math.max(maximumHeight[i], temp);
                }
            }
        }

        result = maximumHeight[0];
        for (int i = 1; i < n; ++i)
            result = Math.max(result, maximumHeight[i]);
        return result;

    }

    static int maxStackHeightRec(Plate arr[], int n) {

        Plate[] data = new Plate[n];

        for (int i = 0; i < n; i++) {
            Plate plate = arr[i];
            data[i] = new Plate(plate.r, plate.h, plate.index);
        }

        for (int i = 0; i < data.length; i++)
            data[i].area = data[i].r * data[i].r;


        int[] maximumHeight = new int[n];
        for (int i = 0; i < n; i++)
            maximumHeight[i] = data[i].h;

        for (int i = 0; i < n; i++) {
            maximumHeight[i] = 0;
            Plate plate = data[i];
            int val = 0;

            for (int j = 0; j < i; j++) {
                Plate prevPlate = data[j];
                if (plate.r < prevPlate.r && plate.index > prevPlate.index) {
                    val = Math.max(val, maximumHeight[j]);
                }
            }
            maximumHeight[i] = val + plate.h;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, maximumHeight[i]);
        }
        return max;

    }

    public static void main(String[] args) {

        Plate[] arr = new Plate[7];
        arr[0] = new Plate(5, 3, 0);
        arr[1] = new Plate(7, 10, 1);
        arr[2] = new Plate(3, 3, 2);
        arr[3] = new Plate(2, 1, 3);
        arr[4] = new Plate(4, 7, 4);
        arr[5] = new Plate(2, 2, 5);
        arr[6] = new Plate(3, 3, 6);

        System.out.println("The maximum height is " + maxStackHeightDP(arr, 7));
       // System.out.println("The maximum height is " + maxStackHeightRec(arr, 7));
    }
}


