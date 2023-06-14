package project.pkg3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project3 {
    
public static LinearProbingHash hashTable = new LinearProbingHash(107);

    public static void main(String[] args) throws Exception { 
        Scanner input = new Scanner(System.in);
        System.out.println("Lets start our program...\n\nPlease press enter to start");
        input.nextLine();
        System.out.println("Please enter the graph file to start(hint:test2.txt):");
        GraphMatrix g = readfromFile(input.nextLine());
            int operation = -1;
            while (operation!=0){
                System.out.println("\nChoose an operation below...");
                System.out.println("\n1)Is there a connection between two characters:\n3)Show the shortest path length between two characters:\n5)Show BFS path between two characters:\n6)Show DFS path between two characters:\n7)Show how many people in a character's connection graph:\n8)Exit from the program:");
                operation= input.nextInt();
                if (operation>8 && operation <1 && operation !=0){
                operation= input.nextInt();}
                switch (operation){
                    case 1:
                        System.out.println("First Character:");
                        BreadthFirstSearch bfs = new BreadthFirstSearch(g, hashTable.geti(input.next()));
                        System.out.println("Second Character:");
                        if(bfs.hasPathTo(hashTable.geti(input.next())))
                            System.out.println("Yes, there is a path between.");
                        else
                            System.out.println("No, there is no path between.");
                        break;
                    case 2:
                        System.out.println("First Character:");
                        String name=input.next();
                        System.out.println("Lower Bound:");
                        int lb=input.nextInt();
                        System.out.println("Upper Bound:");
                        int ub=input.nextInt();
        
                        int v1= hashTable.geti(name);
       
                        allPaths nap= new allPaths(hashTable,g,v1);
        
                        nap.startLengthPath(ub, lb);
                        
                        break;
                    case 3:
                        ShortestPath t = new ShortestPath();
                        System.out.println("First Character:");
                        int first = hashTable.geti(input.next());
                        System.out.println("Second Character:");
                        t.dijkstra(g.edges, first,hashTable.geti(input.next()));
                        break;
                    case 4:
                        System.out.println("First Character:");
                        String name1=input.next();
                        System.out.println("Second Character:");
                        String name2=input.next();
        
                        int v2= hashTable.geti(name1);
                        int v3= hashTable.geti(name2);
       
                        allPaths nop= new allPaths(hashTable,g,v2);
        
                        nop.startNop(v3);
                        break;
                    case 5:
                        System.out.println("First Character:");
                        bfs = new BreadthFirstSearch(g, hashTable.geti(input.next()));
                        System.out.println("Second Character:");
                        bfs.printPathTo(hashTable.geti(input.next()));
                        break;
                    case 6:
                        System.out.println("First Character:");
                        DepthFirstPaths dps = new DepthFirstPaths(g, hashTable.geti(input.next()));
                        System.out.println("Second Character:");
                        dps.printPathTo(hashTable.geti(input.next()));
                        break;
                    case 7:
                        System.out.println("Enter the character:");
                        t = new ShortestPath();
                        System.out.println("This is how many people in this person's connection graph: "+ t.dijkstra(g.edges, hashTable.geti(input.next())));
                        break;
                    case 8:
                        System.out.println("Exiting from program. Good Bye :)");
                        operation = 0;
                        break;
                }
                        
                
            }
	  }
    public static GraphMatrix readfromFile(String f) {

        try {
            Scanner sc = new Scanner(new File(f));
            int v = 107;
            int e = 107;
            System.out.println("\nReading the graph from file...\n\nProcessing the data please wait...");
            //System.out.println("constructing a graph of " + v + " vertices and "+ e + " edges ");
            GraphMatrix g1 = new GraphMatrix(v);
            for (int i = 0; i < e; i++) {
                while (sc.hasNextLine()) {
                String v1 = sc.next();
                    System.out.println(v1);
                if(!hashTable.contains(v1)){
                hashTable.insert(v1);}
                String v2 = sc.next();
                    System.out.println(v2);
                if(!hashTable.contains(v2)){
                hashTable.insert(v2);}
                g1.addEdge(hashTable.geti(v1), hashTable.geti(v2),sc.nextInt());}
                if(i==(e/2))
                    System.out.println("\nSo far so good half way done...");
            }
            //System.out.println("Loaded " + e + " edges ");
            System.out.println("\nAll done you can continue now!");
            return g1;

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
}
