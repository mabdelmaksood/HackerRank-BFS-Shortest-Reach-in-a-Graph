import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Pair{
    Integer key;
    Integer Value;
    public Pair(Integer key, Integer Value){
        this.key=key;
        this.Value=Value;
    }
    public Integer getKey(){
        return key;
    }
    public Integer getValue(){
        return Value;
    }
    }
    public static class Graph {
        private ArrayList<Integer> edges[];
        private int distance[];
        private Queue<Pair> queue = new LinkedList<Pair>();
        
        
        public Graph(int size) {
            edges= new ArrayList[size];
            distance= new int[size];
            Arrays.fill(distance, -1);
        }

        public void addEdge(int first, int second) {
            if(edges[first]==null){
            edges[first]=new ArrayList<Integer>();
            }
            if(edges[second]==null){
            edges[second]=new ArrayList<Integer>();
            }
            edges[first].add(second);
            edges[second].add(first);
        }
        
        public int[] shortestReach(int startId) { // 0 indexed
            distance[startId]=0;
            Pair temp=new Pair(startId,0); 
            queue.add(temp);
            calculateDist();
            return distance;
        }
        private void calculateDist(){
            while(!queue.isEmpty()){
                Pair temp=queue.poll();
                Integer node=temp.getKey();
                Integer dist=temp.getValue();
                ArrayList<Integer> tempList=edges[node];
                if(tempList!=null){
                    for(int i=0; i<tempList.size();i++){
                        Integer nodes=tempList.get(i);
                        if(distance[nodes]>dist+6||distance[nodes]==-1){
                            distance[nodes]=dist+6;
                            Pair temp1=new Pair(nodes,dist+6); 
                            queue.add(temp1);
                        }
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        int queries = scanner.nextInt();
        
        for (int t = 0; t < queries; t++) {
            
            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();
            
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                
                // add each edge to the graph
                graph.addEdge(u, v);
            }
            
            // Find shortest reach from node s
            int startId = scanner.nextInt()-1;
            int[] distances = graph.shortestReach(startId);
 
            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();            
        }
        
        scanner.close();
    }
}
