package Grade4.Algorithm.week12;

public class BellmanFord {
    class Edge {
        int src, dest, weight;
        Edge() {
            src = dest = weight = 0;
        }
    };

    int V, E;
    Edge edge[];

    BellmanFord(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public void bellmanFord(BellmanFord graph, int start) {
        int V = graph.V, E = graph.E;
        int distance[] = new int[V];

        // 시작점으로부터의 거리를 INF로 초기화
        for (int i = 0; i < V; ++i)
            distance[i] = Integer.MAX_VALUE;
        //src ~ src 거리 = 0
        distance[start] = 0;

        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) //새로 발견한 거리가 더 작으면
                    distance[v] = distance[u] + weight; // 갱신
            }
        }

        //음의 가중치를 갖는 사이클이 포함되어 있지 않으면 최단 경로를 구할수 있다 - 위키백과
        //(간선이 음의 가중치를 갖는 경우, 그리고 그 간선이 순환구조를 띄는 경우 최단 경로를 구할 수 없다)
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (distance[u] != Integer.MAX_VALUE &&
                    distance[u] + weight < distance[v]) {
                System.out.println("도달할수 없음");
                return;
            }
        }
        System.out.println("BellmanFord");
        for (int i = 0; i < V; ++i)
            System.out.println("vertex " + i + "'s Distance from source is " + distance[i]);
    }

    public static void main(String[] args)
    {
        int V = 5; // vertices
        int E = 8; // edges

        BellmanFord graph = new BellmanFord(V, E);

        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        graph.bellmanFord(graph, 0);
    }
}
