
package jt.algo.graphs;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




/**
 * Adjacency List
 * @author jtrav029
 */
public class DiGraph implements Iterable<String> {
    
    private Map<String, Integer> indegrees;
    private Map<String, List<String>> adjList;
    private Map<String, List<String>> incomings;
    
    public DiGraph() {
        indegrees = new HashMap<>();
        adjList = new HashMap<>();
        incomings = new HashMap<>();
    }
    
    /**
     * Reads a graph from a file
     * @param fileName containing a graph
     * @return a DiGraph ds
     * @throws IOException
     */
    public static DiGraph readGraphFile(String fileName) throws IOException {
        
        final int V_INDEX = 0;
        final int W_INDEX = 1;
        final String SPLIT_PATTERN = "\\s";
        DiGraph graph = new DiGraph();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line = null;
        int i = 1;
        while ((line = in.readLine()) != null) {
            String[] split = line.split(SPLIT_PATTERN);
            if (split.length != 2) {
                System.out.printf("Line %d skipped\n", i);
                continue;
            }
            
            graph.addEdge(split[V_INDEX], split[W_INDEX]);
            i++;
        }
        in.close();
        return graph;
    }
    
    public int numOfVertices() {
        return adjList.size();
    }
    
    public static String selectFirstKey(Map<String, Integer> map) {
        
        if (map.isEmpty()) {
            return null;
        }
        return map.keySet().iterator().next();
    }
    
    public int indegree(String v) {
        return indegrees.get(v);
    }
    
    public void updateIndegree(String v, int newVal) {
        if (!hasVertex(v)) {
            return;
        }
        indegrees.put(v, newVal);
    }
    
    public Map<String, Integer> getIndegrees() {
        return indegrees;
    }
    
    public Map<String, List<String>> getAdjList() {
        return adjList;
    }
    
    public Map<String, List<String>> getIncomings() {
        return incomings;
    }
    
    public int size() {
        return indegrees.size();
    }
    
    public List<String> adjacents(String v) {
        return adjList.get(v);
    }
    
    public List<String> incommings(String v) {
        return incomings.get(v);
    }
    
    public void addVertex(String v) {
        if (!hasVertex(v)) {
            adjList.put(v, new LinkedList<String>());
            indegrees.put(v, 0);
            incomings.put(v, new LinkedList<String>());
        }
    }
    
    public void addEdge(String v, String w) {
        if (!hasVertex(v)) {
            addVertex(v);
        }
        if (!hasVertex(w)) {
            addVertex(w);
        }
        adjList.get(v).add(w);
        incomings.get(w).add(v);
        Integer count = indegrees.get(w);
        indegrees.put(w, ++count);
    }
    
    public boolean hasVertex(String v) {
        return adjList.containsKey(v);
    }
    
    public boolean hasEdge(String v, String w) {
        if (!hasVertex(v)) {
            return false;
        }
        return adjList.get(v).contains(w);
    }
    
    @Override
    public String toString() {
        return "DiGraph{" + "indegrees=" + indegrees + ", adjList=" + adjList + '}';
    }
    
    @Override
    public Iterator<String> iterator() {
        return adjList.keySet().iterator();
    }
    
}