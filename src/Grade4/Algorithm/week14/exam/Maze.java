package Grade4.Algorithm.week14.exam;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Stack;

public class Maze {

    ///////////////////////////////////////////////////////////////////
///
    String numId = "60171620";   // �й�
    String name = "김다흰";    // �̸�
///////////////////////////////////////////////////////////////////

    public class Coordinate {
        int x, y;

        public Coordinate(int i, int j) {
            x = i;
            y = j;
        }

        public boolean equals(Coordinate other) {
            return (this.x == other.x) && (this.y == other.y);
        }

        public void copyFrom(Coordinate other) {
            this.x = other.x;
            this.y = other.y;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    int size;
    Coordinate start, destin;

    public Maze(int n) {
        size = n;
        start = new Coordinate(0, 0);
        destin = new Coordinate(size - 1, size - 1);

        System.out.println("<< " + numId + " : " + name + " >>");
    }

    public int DFS(int[][] in) {
        int[][] m = deepCopy(in);
        show("Initial State", m);

        Coordinate p = new Coordinate(start.x, start.y);
        int seq = 1;
        int n = DFS(m, p, seq); // sequence-value starts from 1, ie.  [0,0]=1
       // show("DFS result", m);
        return n;
    }

    public int DFS(int[][] m, Coordinate p, int seq) {
///////////////////////////////////////////////////////////////////
/// 1) 여기에 DFS 코드 작성
        Stack<Coordinate> st = new Stack<Coordinate>();
        int ROW = m.length;
        int COL = m.length;
        Boolean[][] visited = new Boolean[ROW][COL];


        while (!st.empty()) {

            Coordinate curr = p;

            int i = curr.x;
            int j = curr.y;

            if (!isValid(visited, i, j, m))
                continue;

            visited[i][j] = true;

            System.out.print(m[i][j] + "");

            //Coordinate adjx = adjacent(curr,m).;
            HashSet<Coordinate> k = adjacent(curr, m);
            //st.push(adjx);
            if(!k.isEmpty()) {

            }

            seq = calcHVal(curr);
        }

///////////////////////////////////////////////////////////////////

        return seq; //왜?
    }


    public Boolean isValid(Boolean[][] visited, int row, int col, int[][] m) {

        if (row < 0 || col < 0 ||
                row >= m.length || col >= m.length)
            return false;

        return !visited[row][col];

    }

    public void BFS(int[][] in) {
        int[][] m = deepCopy(in);
        Coordinate p = new Coordinate(start.x, start.y);

        Deque<Coordinate> Q = new ArrayDeque<>();
        Q.add(p);
        int seq = 1;

        while (Q.peek() != null) {
///////////////////////////////////////////////////////////////////
/// 2) 여기에 BFS 코드 작성


///////////////////////////////////////////////////////////////////
        }
        show("BFS result", m);
    }

    public void Dijkstra(int[][] in) {

        int[][] m = deepCopy(in);
        Coordinate p = new Coordinate(start.x, start.y);
        int seq = 1;
        int V = m.length;
///////////////////////////////////////////////////////////////////
/// 3) 여기에 Dijkstra 코드 작성

        int[][] distance = new int[V][V];
        Boolean[] visited = new Boolean[V];

        //초기화
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        System.out.println();
        distance[0][0] = 1;
       // m[0][0] = 1;

        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (!visited[v] && (((distance[u][v] + m[u][v]) < distance[u][v]))) {
                    distance[u][v] = distance[u][v] + m[u][v];
                }
            }
        }
        show("Dijkstra result", distance);
    }


///////////////////////////////////////////////////////////////////

    public void AStar(int[][] in) {
        int[][] m = deepCopy(in);
        Coordinate p = new Coordinate(start.x, start.y);
        int seq = 1;
///////////////////////////////////////////////////////////////////
/// 4) 여기에 A* 코드 작성


///////////////////////////////////////////////////////////////////

        show("Dijkstra + A* result", m);
    }


    private HashSet<Coordinate> adjacent(Coordinate u, int[][] maze) {  // can filter 1-boundary condition, 2-not the wall(-1)
        HashSet<Coordinate> retSet = new HashSet<>();
        if (u.x - 1 >= 0 && maze[u.x - 1][u.y] != -1) retSet.add(new Coordinate(u.x - 1, u.y));
        if (u.x + 1 < size && maze[u.x + 1][u.y] != -1) retSet.add(new Coordinate(u.x + 1, u.y));
        if (u.y - 1 >= 0 && maze[u.x][u.y - 1] != -1) retSet.add(new Coordinate(u.x, u.y - 1));
        if (u.y + 1 < size && maze[u.x][u.y + 1] != -1) retSet.add(new Coordinate(u.x, u.y + 1));
        return retSet;
    }

    private int calcHVal(Coordinate c) {
        double temp = Math.sqrt((destin.x - c.x) * (destin.x - c.x) + (destin.y - c.y) * (destin.y - c.y));
        return (int) temp;
    }

    private void show(String s, int[][] m) {
        System.out.println("\n [ " + s + " ]");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3d", m[i][j]);
            }
            System.out.println();
        }
    }

    private int[][] deepCopy(int[][] m) {
        int[][] ret = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                ret[i][j] = m[i][j];
        return ret;
    }

    public static void main(String[] args) {
        int[][] input = {{0, -1, 0, 0, 0, 0, 0, -1, 0, -1},
                {0, -1, 0, -1, -1, -1, 0, -1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, -1, 0, -1, 0, -1, -1, 0, 0},
                {-1, 0, -1, 0, -1, 0, 0, -1, -1, 0},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, -1, 0, 0, -1, -1, 0, -1, -1},
                {0, -1, -1, 0, 0, 0, 0, 0, 0, -1},
                {0, -1, 0, 0, -1, 0, 0, -1, 0, 0},
                {0, 0, -1, 0, 0, 0, -1, -1, 0, 0}};

        int size = input.length;

        Maze me = new Maze(size);
        me.DFS(input);
         me.BFS(input);
        me.Dijkstra(input);
         me.AStar(input);

    }

}

