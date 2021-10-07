package Grade4.Algorithm.week5.Set;

public class DisjointSetbyRank {

  /*
  Union-by-Rank (rank는 트리의 높이)
  새로운 집합을 서로 합칠 때,
  새 트리를 기존 트리에서 높이가 더 낮은 트리를 높은 트리에 합쳐야 한다는 것
  작은 트리가 큰 트리 밑에 가면 높이가 더 변하지 않기 때문이다.
   */

  static int parent[];
    static int rank[];

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
        return find(parent[e]);
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
