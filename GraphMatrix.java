package project.pkg3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class GraphMatrix {
    int edges[][];
    LinkedList<Integer>[] edges2;
    int numV;
    int numE;

    public GraphMatrix(int V) {
        this.numV = V;
        edges = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                edges[i][j] = 0;
            }
        }
        edges2 = new LinkedList[numV];
        for (int i = 0; i < numV; i++) {
            edges2[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;
        edges2[from].add(to);
        edges2[to].add(from);
    }

    public boolean isAdjacent(int v1, int v2) {
        return (edges[v1][v2] != 0);
    }

    public int degree(int v) {
        int degree = 0;
        for (int i = 0; i < numV; i++) {
            degree += edges[v][i];
        }
        return degree;
    }
    
    public static int findMaxDegree(GraphMatrix g){
        int degree = 0;
        int maxDegreeVertex = 0;
        for (int i = 0; i < g.numV; i++) {
            System.out.println("Degree: "+g.degree(i));
            if(g.degree(i)>degree){
                degree = g.degree(i);
                maxDegreeVertex = i;
            }
        }
        return maxDegreeVertex;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                s.append(edges[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int getNumV() {
        return numV;
    }
    
    public LinkedList<Integer> neighborsList(int from) {
        return (LinkedList<Integer>) edges2[from].clone();
    }

    public Integer[] neighborsArray(int from) {
        Integer[] ar = new Integer[edges2[from].size()];
        edges2[from].toArray(ar);
        return ar;
    }
    
    

}
