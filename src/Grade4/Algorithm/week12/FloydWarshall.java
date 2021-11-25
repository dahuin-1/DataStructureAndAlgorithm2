package Grade4.Algorithm.week12;

import java.util.Enumeration;

public class FloydWarshall {

    static int INF = 1000000;

    public static void main(String[] args) {
        FloydWarshall fw = new FloydWarshall();
        int[][] array = new int[][] {
                {0, 5, INF, 8},
                {7, 0, 9, INF},
                {2, INF, 0, 4},
                {INF, INF, 3, 0}
        };
        fw.floydWarshall(array);
    }

    public void floydWarshall(int[][] array) {
        int[][] distance = new int[array.length][array.length];
        // 초기화
        for(int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, distance[i], 0, array.length);
        }
        // k = 거쳐가는 노드
        for(int k = 0; k < array.length; k++) {
            // i = 출발 노드
            for(int i = 0; i < array.length; i++) {
                // j = 도착 노드
                for(int j = 0; j < array.length; j++) {
                    if(distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                    System.out.println(i + " to " + j + " is "
                            + distance[i][j]);
            }
        }
    }
}
