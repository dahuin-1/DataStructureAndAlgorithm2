package Grade4.Algorithm.week10.spanningtree;

import java.util.*;
import java.lang.*;

public class KruskalAlgorithm {

    static class Edge {
        int from, destination, weight;
    }

    // union-find
    static class subset {
        int parent, rank;
    }

    int V, E; // V =  정점 수 & E = 간선 수
    Edge[] edge; // 간선집합


    KruskalAlgorithm(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    int find(subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    void Union(subset[] subsets, int x, int y) {
        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        //높은 랭크 트리 아래 낮은 랭크 트리 붙이기 (유니온)
        if (subsets[xRoot].rank
                < subsets[yRoot].rank)
            subsets[xRoot].parent = xRoot;
        else if (subsets[xRoot].rank
                > subsets[yRoot].rank)
            subsets[yRoot].parent = xRoot;
            //랭크가 같다면 하나가 루트가 된다
        else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    void KruskalMST() {
        Edge[] result = new Edge[V];

        int e = 0;
        int i;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edge); //오름차순으로 정렬

        subset[] subsets = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.from);
            int y = find(subsets, next_edge.destination);

            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }

        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].from + " -- "
                    + result[i].destination
                    + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }

}

/*
  static final long INF = 1000L;
    static long[][] weight = {
            {0,29,INF,INF,INF,10,INF},
            {29,0,16,INF,INF,INF,15},
            {INF,16,0,12,INF,INF,INF},
            {INF,INF,12,0,22,INF,18},
            {INF,INF,INF,22,0,27,25},
            {10,INF,INF,INF,27,0,INF},
            {INF,15,INF,18,25,INF,0}
    };

    static boolean[] selected = new boolean[weight.length];
    static long[] dist = new long[weight.length];

    public static void main(String[] args) {
        PrimAlgorithm prim= new PrimAlgorithm();
        prim.primAlgorithm(0, weight.length,weight,selected,dist);
    }
 */