package maxflowalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class GraphMaxFlow {

    int capacity[][];
    int flow[][];
    int capacityFlow[][];
    int vertices;
    int maxFlow;
    int source, sink;
    private Queue<Integer> Q;
    boolean[] isVisited;
    int parent[];
    boolean vis[];

    GraphMaxFlow(int n, int s, int t) {
        source = s;
        sink = t;
        vertices = n;
        capacity = new int[vertices][vertices];
        flow = new int[vertices][vertices];
        capacityFlow = new int[vertices][vertices];
        isVisited = new boolean[vertices];

        parent = new int[vertices];
        maxFlow = 0;
        Q = new LinkedList<Integer>();
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                capacity[i][j] = 0;
                flow[i][j] = 0;
                capacityFlow[i][j] = 0;
            }
        }
        vis = new boolean[vertices];
    }

    void DFS2(int node) {
        vis[node] = true;

        for (int i = 0; i < vertices; i++) {
            if (capacity[node][i] != 0 && !vis[i]) {
                DFS2(i);
            }
        }
    }

    public boolean generate(int edgenumbers) {

        Random rand = new Random();
        int from, to, tmpCapacity;
        int cnt = 0;
        boolean flag = true;

        while (flag) {
            while (cnt < edgenumbers) {
                from = rand.nextInt(vertices);
                to = rand.nextInt(vertices);
                tmpCapacity = rand.nextInt(vertices);
                if (from != to && capacity[to][from] == 0 && capacity[from][to] == 0) {
                    if (to == source || from == sink) {
                        continue;
                    }
                    capacity[from][to] = tmpCapacity;
                    cnt++;
                }
            }

            for (int l = 0; l < vertices; l++) {
                vis[l] = false;
            }
            DFS2(source);
            if (vis[sink]) {
                flag = false;
                break;
            }
            cnt = 0;

            // init capacity
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    capacity[i][j] = 0;
                    capacity[j][i] = 0;
                }
            }

            System.out.println("The Graph does n't have path from source to sink so please input another edges");
        }
        showGraph();
        return flag;
    }

    public boolean inputfromuser(int edgenumbers) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int from, to, tmpCapacity;
        int cnt = 0;
        boolean flag = true;
        while (flag) {
            while (cnt < edgenumbers) {
                System.out.println("Please enter an edge (from,to)");
                from = Integer.parseInt(br.readLine());
                to = Integer.parseInt(br.readLine());
                System.out.println("Please enter the capacity of this edge");
                tmpCapacity = Integer.parseInt(br.readLine());
                if (from != to && capacity[to][from] == 0 && capacity[from][to] == 0) {
                    if (to == source || from == sink) {
                        continue;
                    }

                    capacity[from][to] = tmpCapacity;
                    cnt++;
                    System.out.println("cnt = " + cnt);
                }
            }

            for (int l = 0; l < vertices; l++) {
                vis[l] = false;
            }

            DFS2(source);
            if (vis[sink]) {
                flag = false;
                break;
            }
            cnt = 0;
            // init capacity

            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    capacity[i][j] = 0;
                    capacity[j][i] = 0;
                }
            }
            System.out.println("The Graph does n't have path from source to sink so please input another edges");
        }
        return flag;

    }

    void showGraph() {
        System.out.println("source = " + source + " sink= " + sink);
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (capacity[i][j] > 0) {
                    System.out.println("(" + i + ", " + j + ", " + capacity[i][j] + ")");
                }
            }
        }
    }

    void dfs(int u) {
        isVisited[u] = true;
        for (int v = 0; v < vertices; v++) {
            // check if edge in residual network
            if (capacityFlow[u][v] > 0 && isVisited[v] == false) {
                parent[v] = u;
                dfs(v);
            }
        }

    }

    void initFlowToZero() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                flow[i][j] = 0;
            }
        }
    }

    void updateResidualNetwork() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (capacity[i][j] > 0) {
                    capacityFlow[i][j] = capacity[i][j] - flow[i][j];
                    capacityFlow[j][i] = flow[i][j];
                }

            }
        }
    }

    boolean findaPathInResdaulNetworkBFS() {    //  BFS
        for (int i = 0; i < vertices; i++) {
            isVisited[i] = false;
            parent[i] = -1;
        }
        int v;
        Q.add(source);
        parent[source] = source;
        isVisited[source] = true;
        while (!Q.isEmpty()) {
            v = Q.remove();
            for (int w = 0; w < vertices; w++) {
                if (capacityFlow[v][w] > 0) {
                    if (!isVisited[w]) {
                        parent[w] = v;
                        Q.add(w);
                        isVisited[w] = true;
                    }
                }
            }
        }
        return isVisited[sink];
    }

    int fordFulkersonBFS() {
        maxFlow = 0;
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                flow[j][i] = 0;
                capacityFlow[i][j] = capacityFlow[j][i] = 0;
            }
        }

        updateResidualNetwork();

        int mn = Integer.MAX_VALUE;


        while (findaPathInResdaulNetworkBFS()) {
            int u;
            for (int v = sink; v != source; v = parent[v]) {
                u = parent[v];
                mn = Math.min(mn, capacityFlow[u][v]);
            }
            for (int v = sink; v != source; v = parent[v]) {
                u = parent[v];

                if (capacity[u][v] > 0) {
                    flow[u][v] += mn;
                } else {
                    flow[v][u] -= mn;
                }

            }

            updateResidualNetwork();
            maxFlow += mn;
        }
        return maxFlow;

    }

    boolean findPathDFS() {

        for (int i = 0; i < vertices; i++)//all vertices are not visited
        {
            isVisited[i] = false;
        }
        for (int i = 0; i < vertices; i++) {
            parent[i] = -1;// p is used to store a path from source to sink
        }
        dfs(source);
        return isVisited[sink];
    }

    public int fordFulkersonDFS() {
        maxFlow = 0;
        initFlowToZero();
        updateResidualNetwork();
        int mn = Integer.MAX_VALUE;
        while (findPathDFS()) {
            int u;
            for (int v = sink; v != source; v = parent[v]) {
                u = parent[v];
                mn = Math.min(mn, capacityFlow[u][v]);
            } // to find minimum value at path
            for (int v = sink; v != source; v = parent[v]) {
                u = parent[v];

                if (capacity[u][v] > 0) {
                    flow[u][v] += mn;
                } else {
                    flow[v][u] -= mn;
                }

            }
            updateResidualNetwork();
            maxFlow += mn;
        }
        return maxFlow;
    }

}
