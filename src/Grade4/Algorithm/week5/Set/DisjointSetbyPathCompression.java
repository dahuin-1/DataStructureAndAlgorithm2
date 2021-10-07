package Grade4.Algorithm.week5.Set;

public class DisjointSetbyPathCompression {

    /*
    자신의 부모를 지속적으로 찾아 루트까지 도달하는 방식
    기존의 찾기 연산에서 return 문에 찾은 부모의 루트 집합 번호를 재귀적으로 지속하여 업데이트하는 것을 추가
    26번 줄을 참고하시면 됩니다.
     */

    static int[] parent;
    static int[] rank;

    // ① 초기화 연산 : 1~n 까지의 숫자가 주어진다고 가정하고 초기화
    public void initialize(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;

            // 현재 모든 랭크의 값은 1로 통일. 자기 자신만 존재하기 때문문
            rank[i] = 1;
        }
    }

    // ② 찾기 연산 : 각 원소가 어느 집합에 속한지를 확인한다.
    // 원소의 번호를 전달하면 그것들 재귀적으로 알아낼 수 있다.
    public int find(int e) {

        // 루트 노드는 자기 자신을 가리키며, 그것이 대표 집합의 번호가 된다.
        if (parent[e] == e) return e;

        // 자신이 가리키는 부모 집합을 찾아간다.
        return parent[e] = find(parent[e]);
    }

    // ③ 합 연산 : 전달된 두 개의 원소의 대표 집합을 동일하게 만든다.(합친다)
    public void union(int e1, int e2) {

        // 각 원소의 대표 집합을 찾아낸다.
        e1 = find(e1);
        e2 = find(e2);

        // 같은 집합인 상태라면 불 필요하다.
        if (e1 == e2) return;

        // 다른 집합이라면 더 작은 쪽으로 한 쪽의 번호를 바꾼다.
        if (e1 < e2) {
            parent[e2] = e1;
        } else {
            parent[e1] = e2;
        }
    }

    // ④ 최적화를 위한 union-by-rank 연산
    // 트리의 높이를 최소한으로 줄여서 수행
    public void unionByRank(int e1, int e2) {
        e1 = find(e1);
        e2 = find(e2);

        if (e1 == e2) return;

        // 다른 집합이면 더 rank가 작은 쪽으로 붙인다.
        // e2가 더 높이가 작다면 e1 밑에 e2를 넣어야 하므로 e2의 값을 바꾼다.
        if (rank[e1] > rank[e2]) {
            parent[e2] = e1;
            // e1가 더 높이가 작거나 같다면 e2 밑에 e1를 넣어야 하므로 e1의 값을 바꾼다.
        } else {
            parent[e1] = e2;
        }

        // 같은 크기의 rank면 union 해서 한 쪽의 아래쪽으로 갔으니 +1을 해줘야 한다.
        if (rank[e1] == rank[e2]) ++rank[e1];
    }
}