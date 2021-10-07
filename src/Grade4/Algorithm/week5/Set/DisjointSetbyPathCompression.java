package Grade4.Algorithm.week5.Set;

public class DisjointSetbyPathCompression {

    /*
    자신의 부모를 지속적으로 찾아 루트까지 도달하는 방식
    기존의 찾기 연산에서 return 문에 찾은 부모의 루트 집합 번호를 재귀적으로 지속하여 업데이트하는 것을 추가
    26번 줄을 참고하시면 됩니다.
     */

    static int parent[];
    static int rank[];

    //초기화 연산 : 1~n 까지의 숫자가 주어진다고 가정하고 초기화
    public void initialize(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int e) {
        if (parent[e] == e) return e;
        return parent[e] = find(parent[e]);
    }

    public void union(int e1, int e2) {
        e1 = find(e1);
        e2 = find(e2);

        if (e1 == e2) return;

        if (e1 < e2) {
            parent[e2] = e1;
        } else {
            parent[e1] = e2;
        }
    }

    public void unionByRank(int e1, int e2) {
        e1 = find(e1);
        e2 = find(e2);

        if (e1 == e2) return;

        if (rank[e1] > rank[e2]) {
            parent[e2] = e1;
        } else {
            parent[e1] = e2;
        }

        if (rank[e1] == rank[e2]) ++rank[e1];
    }
}