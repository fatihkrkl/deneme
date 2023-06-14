/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg3;
public class DepthFirstPaths {

    boolean[] marked;
    int[] edgeTo;
    int from;

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public Integer[] pathTo(int w) {
        int k = edgeTo[w];
        java.util.Stack<Integer> st = new java.util.Stack<Integer>();
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
        System.out.println("->" + Project3.hashTable.table[w]);

    }

    public DepthFirstPaths(GraphMatrix g, int from) {
        edgeTo = new int[g.getNumV()];
        marked = new boolean[g.getNumV()];
        this.from = from;
        dfs(g, from);
    }
       //recursive
    public void dfs(GraphMatrix g, int source) {
        marked[source] = true;
        Integer[] a = (Integer[]) g.neighborsArray(source);
        for (int i = 0; i < a.length; i++) {
            int neighbor = a[i];
            if (!marked[neighbor]) {
                //System.out.println("..."+neighbor);
                dfs(g, neighbor);
                edgeTo[neighbor] = source;
            }
        }
    }
    
}