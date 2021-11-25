package Grade4.Algorithm.week12;

import java.util.*;
//지난번에는 선형 탐색을 사용했기 때문에 이번에는 힙 구조를 활용하였습니다.
public class DijkstraAlgorithm {

    int distance[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    // 정점개수
    private int V;
    List<List<Node>> adjacent; //인접

    public static void main(String[] args) {
        int V = 5;
        int source = 0;

        // 엣지의 인접 목록 표현
        List<List<Node> > adj = new ArrayList<List<Node> >();

        // 초기화
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // 삽입
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // 최단 경로 계산
        DijkstraAlgorithm da = new DijkstraAlgorithm(V);
        da.dijkstra(adj, source);

        for (int i = 0; i < da.distance.length; i++)
            System.out.println(source + " to " + i + " is "
                    + da.distance[i]);
    }

    public DijkstraAlgorithm(int V) {
        this.V = V;
        distance = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    public void dijkstra(List<List<Node> > adjacent, int start) {
        this.adjacent = adjacent;
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        //pq에 src 추가
        pq.add(new Node(start, 0));
        //src ~ src 거리 = 0
        distance[start] = 0;

        while (settled.size() != V) {

            //우선 순위 큐가 비어 있을 때 return
            if(pq.isEmpty()) return;

            //우선 순위 큐에서 최소 거리 노드 제거
            int u = pq.remove().node;

            //이미 포함하고 있다면 continue
            if(settled.contains(u)) continue;

            //else add
            settled.add(u);
            edgeNeighbours(u);
        }
    }
    // 인접구하기
    private void edgeNeighbours(int u) {

        int edgeDistance = -1;
        int newDistance = -1;

        //v의 인접
        for(int i = 0; i < adjacent.get(u).size(); i++) {
            Node v = adjacent.get(u).get(i);
            //현재 노드가 아직 처리되지 않았다먄
            if(!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = distance[u] + edgeDistance;

                //만약 최소비용 거리를 찾는다면
                if (newDistance < distance[v.node])
                    distance[v.node] = newDistance;

                //현재 노드를 큐에 추가
                pq.add(new Node(v.node, distance[v.node]));
            }
        }
    }
}
