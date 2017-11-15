import java.io.*;
import java.util.*;

/**
 * November 11, 2017
 * Mid-Atlantic Regionals 2017, Problem B
 * https://open.kattis.com/problems/securitybadge
 * BFS, Intervals
 */
public class SecurityBadge {
    static int N, L, B, S, D;
    static Queue<Integer> queue = new ArrayDeque<Integer>();
    static boolean[] visited;
    static List<Edge>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        B = sc.nextInt();
        S = sc.nextInt()-1;
        D = sc.nextInt()-1;
        graph = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0 ; i < N; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        List<Long> coords = new ArrayList<Long>();
        for (int i = 0; i < L; i++) {
            int u = sc.nextInt()-1;
            int v = sc.nextInt()-1;
            long lower = sc.nextLong();
            long upper = sc.nextLong()+1;
            coords.add(upper);
            coords.add(lower);
            graph[u].add(new Edge(lower, upper, v));
        }
        Compressor<Long> compressor = new Compressor<Long>(coords);
        long count = 0;
        for (int i = 0; i < compressor.real.size()-1; i++) {
            long lower = compressor.uncompress(i);
            long upper = compressor.uncompress(i+1);
            if (bfs(lower, upper)) {
                count += (upper - lower);
            }
        }
        System.out.println(count);
    }

    static boolean bfs(long lower, long upper) {
        queue.clear();
        queue.add(S);
        Arrays.fill(visited, false);
        while (!queue.isEmpty()) {
            Integer curr = queue.poll();
            if (visited[curr]) continue;
            if (curr == D) return true;
            visited[curr] = true;
            for (Edge neighbor : graph[curr]) {
                if (neighbor.contains(lower, upper)) {
                    queue.add(neighbor.toDoor);
                }
            }
        }
        return false;
    }

    static class Compressor<T extends Comparable<T>> {
        List<T> real;
        Compressor(List<T> real) {
            Collections.sort(this.real = real);
        }
        int compress(T realval) {
            return Collections.binarySearch(real, realval);
        }
        T uncompress(int compval) { return real.get(compval);}
    }

    static class Edge {
        long realLower, realUpper;
        int toDoor;
        Edge(long realLower, long realUpper, int toDoor) {
            this.realLower = realLower;
            this.realUpper = realUpper;
            this.toDoor = toDoor;
        }
        boolean contains(long l, long u) {
            return (realLower <= l && u <= realUpper);
        }
    }
}
