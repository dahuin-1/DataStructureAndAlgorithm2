package Grade4.Algorithm.week11;

public class Dijkstra {

    //최소 거리 값
    static final int V=9;

    public static void main(String[] args) {
        //가중치 인접 행렬  9*9
        int[][] weight = new int[][] { { 0,  4,  0,  0,  0,  0,  0,  8,  0 },
                { 4,  0,  8,  0,  0,  0,  0, 11,  0 },
                { 0,  8,  0,  7,  0,  4,  0,  0,  2 },
                { 0,  0,  7,  0,  9, 14,  0,  0,  0 },
                { 0,  0,  0,  9,  0, 10,  0,  0,  0 },
                { 0,  0,  4, 14, 10,  0,  2,  0,  0 },
                { 0,  0,  0,  0,  0,  2,  0,  1,  6 },
                { 8, 11,  0,  0,  0,  0,  1,  0,  7 },
                { 0,  0,  2,  0,  0,  0,  6,  7,  0 } };

        Dijkstra ShortestPath = new Dijkstra();
        ShortestPath.dijsktra(weight,0);
    }

    public void dijsktra(int[][] weight, int src) {
        int[] distance = new int[V];
        Boolean[] visited = new Boolean[V];
        //초기화
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        System.out.println("초기화된 배열");
        toString(distance);
        System.out.println();
        distance[src] = 0;  // source 정점에서 자기자신까지의 거리는 언제나 0
        for (int count = 0; count < V-1; count++) {
            int u = minDistance(distance, visited); //최단경로를 못찾은 정점
            System.out.println( count + ".  " + u + " 까지의 최단경로 찾기");
            visited[u] = true;
            for (int v = 0; v < V; v++) {
                System.out.println( " ." + v + " : " + visited[v]  +  "   " + weight[u][v] + " +  " + distance[u] + " < " + distance[v] );
                // 1. !visited[v] = 아직 방문한적 없는 정점
                // 2. weight[u][v] != 0 ; u에서 v까지의 엣지가 있어야함 (거리가 0 아니여야함 )
                // 3. 최단경로를 찾으려는 정점이 현재 경로가 infinite 가 아니여아 함 (값이 있어야함)
                // 4. (((distance[u] + weight[u][v]) < distance[v])) ; 시작정점에서 u까지의 거리 + u ~ v 사이의 가중치  < 시작정점에서 v까지의 현재 거리보다 작아야 함
                /*                  0
                                  / |
                               (3)  |
                            4 <    (7)
                               (2)  |
                                  \ |    <- 이 경우, 시작정점 0에서 4까지의 거리 (3) + 4와 1사이의 가중치 (2) < 시작정점 0에서 1까지의 거리 (7)
                                    1
                        */
                if (!visited[v] && (weight[u][v] != 0) &&  (distance[u] != Integer.MAX_VALUE)  && (((distance[u] + weight[u][v]) < distance[v]))) {
                    distance[v] = distance[u] + weight[u][v];
                }
            }
        }
        toString(distance);
    }

    public int minDistance(int[] distance, Boolean[] visited) {

        int min = Integer.MAX_VALUE;
        int min_index = -1;

        System.out.println("최단경로 못찾은 정점 찾기");
        for (int v = 0; v < V; v++) {
            // 아직 방문하지 않았고, 배열이 초기값(infinite)값과 같거나 작으면
            if (!visited[v] && distance[v] <= min) {
                //아직 최단경로를 찾지 못한 정점임
                System.out.println("" + v  + " : " + distance[v]);
                min = distance[v];
                min_index = v;
            }
        }
        return min_index;
    }

    public void toString(int[] distance) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i+ " \t " + distance[i]);
        }
        System.out.println();

    }


}
