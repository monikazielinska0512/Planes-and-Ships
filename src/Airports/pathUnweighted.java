package Airports;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The type Path unweighted.
 */
public class pathUnweighted {
    private LinkedList<Integer> path;

    /**
     * Instantiates a new Path unweighted.
     *
     * @param source the source
     * @param dest   the dest
     */
    public pathUnweighted(int source, int dest)
    {
        int v = 10;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }
        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 3, 0);
        addEdge(adj, 0, 9);
        addEdge(adj, 8, 0);
        addEdge(adj, 2, 1);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 3);
        addEdge(adj, 2, 9);
        addEdge(adj, 6, 2);
        addEdge(adj, 2, 4);
        addEdge(adj, 6, 3);
        addEdge(adj, 4, 6);
        addEdge(adj, 6, 5);;
        addEdge(adj, 4, 5);;
        addEdge(adj, 5, 7);
        addEdge(adj, 7, 6);
        addEdge(adj, 8, 6);
        addEdge(adj, 8, 7);
        addEdge(adj, 9, 8);
        addEdge(adj, 4, 3);
        this.path = printShortestDistance(adj, source, dest);
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
        adj.get(j).add(i);
    }

    private static LinkedList<Integer> printShortestDistance(ArrayList<ArrayList<Integer>> adj, int s, int dest) {
        int v = 10;
        int[] pred = new int[v];
        int[] dist = new int[v];

        if (BFS(adj, s, dest, v, pred, dist) == false) {
            return null;
        }

        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(0, crawl);
        while (pred[crawl] != -1) {
            path.add(0, pred[crawl]);
            crawl = pred[crawl];

        }
        return path;
    }

    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[]) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public LinkedList<Integer> getPath() {
        return path;
    }
}

