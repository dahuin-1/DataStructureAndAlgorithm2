package Grade4.Algorithm.week10.spanningtree;
/*
프림 알고리즘은 아래의 순서대로 작동한다:

그래프에서 하나의 꼭짓점을 선택하여 트리를 만든다.
그래프의 모든 변이 들어 있는 집합을 만든다.
모든 꼭짓점이 트리에 포함되어 있지 않은 동안
트리와 연결된 변 가운데 트리 속의 두 꼭짓점을 연결하지 않는 가장 가중치가 작은 변을 트리에 추가한다.
알고리즘이 종료됐을 때 만들어진 트리는 최소 비용 신장트리가 된다.
-위키백과-
 */
public class PrimAlgorithm {

    static int[][] weight;
    static boolean[] selected = new boolean[weight.length];
    static int[] dist = new int[weight.length];
    static int NoEdge = 1000;

    public static void main(String[] args) {
        PrimAlgorithm prim= new PrimAlgorithm();
        prim.primAlgorithm(0, weight.length,weight,selected,dist);
    }

    public int primAlgorithm(int start, int countOfVertices, int[][] weight, boolean[] selected, int[] dist) {
        System.out.println("hello");
        int u,v;

        for (u = 0; u < countOfVertices; u++) {
            dist[u] = NoEdge;
            selected[u] = false;
        }
        dist[start] = 0; //시작정점과 시작정점간의 거리는 0이다

        for (int i = 0; i < countOfVertices; i++) {
            u = getMinVertex(countOfVertices); //최소거리를 가진 정점을 얻고
            selected[u] = true; //선택한다
        }
        if(dist[u] == NoEdge) { return 0; } //만약에 경로가 없으면 0 출력하고 종료한다
        System.out.println(u); //정점을 출력한다

        for(v = 0; v < countOfVertices; v++) {
            if(weight[u][v] != NoEdge) { //연결이 되어있고
                if(!selected[v] && weight[u][v] < dist[v]) { //// 아직 선택되지 않았으며 기존의 dist[visit] 값보다 작다면
                    dist[v] = weight[u][v]; // dist[v]의 값을 갱신해준다.

                }
            }
        }
        return dist[v];
    }

    public int getMinVertex(int n) {
        int v = 0;
        for(int i = 0 ; i < n; i++) {
            if(!selected[i]) { //선택되지 않았다면
                v = i; //선택
                break;
            }
        }
        for(int i = 0; i < n; i++) { //위에서 선택한 애가 진짜 최소거리인지 확인
            if (!selected[i] && (dist[i] < dist[v])) { //선택한적 없는 애들 중에서 기존의 값보다 작은 애가 있으면
                v = i; //갱신
            }
        }
        return v;
    }



}
