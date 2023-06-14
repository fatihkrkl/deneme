/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg3;

import java.util.LinkedList;

public class BreadthFirstSearch {

    boolean[] marked;
    int[] edgeTo; 
    int[] distTo;
    int from;

    public BreadthFirstSearch(GraphMatrix g, int from) {
        edgeTo = new int[g.getNumV()];// warning this is initalized to 0 
        marked = new boolean[g.getNumV()];
        distTo = new int[g.getNumV()];
        this.from = from;
        bfs(g, from);// method bfs is not recursive
    }

    public boolean hasPathTo(int w) { //source vertex: from
        return marked[w];
    }

    public int distTo(int w) { //source vertex: from
        return distTo[w];
    }
    // a path to w from "from"
    public Integer[] pathTo(int w) {
        int k = edgeTo[w];
        java.util.Stack<Integer> st = new java.util.Stack<>();
        st.push(k);
        while (k != this.from) {
            k = edgeTo[k];
            st.push(k); 
        }
        //st.push(from);

        Integer[] path = new Integer[st.size()];
        for (int i = 0; i < path.length; i++) {
            path[i] = st.pop();
        }
        return path;
    }

    public void printPathTo(int w) {

        Integer[] path = pathTo(w);

        for (int i = 0; i < path.length; i++) {

            System.out.print("->" + Project3.hashTable.table[path[i]]);
        }
        System.out.print("->" + Project3.hashTable.table[w]);
    }

    // this is not recursive
    public void bfs(GraphMatrix g, int source) {
        marked[source] = true;
        Integer[] a = (Integer[]) g.neighborsArray(source);
        if (a.length == 0) {
            return;
        }
        // this is to work as a queue
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(source);
        while (!q.isEmpty()) {
            source = q.removeFirst();
            a = (Integer[]) g.neighborsArray(source);
            for (int i = 0; i < a.length; i++) {
                int w = a[i];
                if (!marked[w]) {
                    //System.out.println(w + ".");
                    q.addLast(w);
                    marked[w] = true;
                    edgeTo[w] = source;
                    distTo[w] = distTo[source] + 1;
                }
            }
        }
    }
}
