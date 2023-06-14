package project.pkg3;
import java.util.ArrayList;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author serse
 */
public class allPaths {
    boolean[] marked;
    ArrayList<Stack<Integer>> stl;
    int from;
    int c;
    GraphMatrix g;
    int deneme;
    LinearProbingHash lph;
    public allPaths(LinearProbingHash lph,GraphMatrix g,int from){
        marked= new boolean[g.getNumV()];
        stl= new ArrayList<Stack<Integer>>();
        c=0;
        deneme=0;
        this.from=from;
        this.lph=lph;
        this.g=g;
    }
    public void startNap(int to){
        Stack<Integer> stack= new Stack<Integer>();
        stack.push(from);
        nap(from,to,stack,marked);
        System.out.println(" ");
    }
    public int startNop(int to){
        c=0;
        Stack<Integer> stack= new Stack<Integer>();
        stack.push(from);
        nop(from,to,stack,marked);
        return c;
    }
    public void startLengthPath(int l,int v){
        Stack<Integer> stack= new Stack<Integer>();
        stack.push(from);
        printAllPathsFromLength(from,l,v,stack,marked,0);
    }
    public void printAllPathsFromLength(int from,int l,int v,Stack<Integer> st,boolean[] ml,int count){
        if(st.size()>=v&&count<=l){
            Stack<Integer> nt= (Stack<Integer>) st.clone();
            printS(nt,nt.size());
            deneme++;
        }
        ml[from]=true;
        Integer[] na= g.neighborsArray(from);
        for(int i=0;i<na.length;i++){
            if(ml[na[i]]==false){
                st.push(na[i]);
                count+=g.edges[from][na[i]];
                printAllPathsFromLength(na[i],l,v,st,ml,count);
                st.pop();
                count-=g.edges[from][na[i]];
            }
            
        }
        ml[from]=false;
        
    }
    public void nap(int from,int to,Stack<Integer> st,boolean[] ml){
        if(from==to){
            Stack<Integer> nt= (Stack<Integer>) st.clone();
            printS(nt,nt.size());
            return;
        }
        ml[from]=true;
        Integer[] na= g.neighborsArray(from);
        for(int i=0;i<na.length;i++){
            if(ml[na[i]]==false){
                st.push(na[i]);
                nap(na[i],to,st,ml);
                st.pop();
            }
            
        }
        ml[from]=false;
    }
    public void nop(int from,int to,Stack<Integer> st,boolean[] ml){
        if(from==to){
            c++;
            return;
        }
        ml[from]=true;
        Integer[] na= g.neighborsArray(from);
        for(int i=0;i<na.length;i++){
            if(ml[na[i]]==false){
                st.push(na[i]);
                nop(na[i],to,st,ml);
                st.pop();
            }
            
        }
        ml[from]=false;
    }
    
    public void printS(Stack<Integer> s,int size){
        
        
        if(!s.isEmpty()){
            int x=s.pop();
            printS(s,size);
            if(s.size()+1==size){
                System.out.print(lph.table[x]);
            }else{
                System.out.print(lph.table[x]+"-");
            }
            s.push(x);
        }else{
            System.out.println(" ");
        }
        s.toString();
            // *12 *13 *14 *15 *16 *17
        
        
    }
    public void printS2(Stack<Integer> s){
        Stack<Integer>n=(Stack<Integer>) s.clone();
        
        while(!n.isEmpty()){
            int x=n.pop();
            System.out.print(lph.table[x]+"-");
        }
        System.out.println(" ");
        
        
    }
    
}
