/**
 * @author Jorge Travieso <jtrav029@fiu.edu/>
 * PI: 4857521
 * Professor Weiss
 * Assignment 1
 */
package jt.algo.graphs;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TSort {

    /**
     * Process a graph file to see if topological sort can be achieved in such
     * graph and returns a possible topological order. In case, the graph is not
     * acyclic it will find one of the existing cycles in this graph
     *
     * @param fileName name of the file containing a graph
     * @throws IOException
     */
    public static void processFile(String fileName) throws IOException {

        DiGraph graph = DiGraph.readGraphFile(fileName); //reads graphs
        Queue<String> queue = new LinkedList<>();
        List<String> topOrder = new ArrayList<>();       //contains possiblle order

        //add 0 indegrees to the queue
        for (String v : graph) {
            if (graph.indegree(v) == 0) {
                queue.add(v);
            }
        }

        while (!queue.isEmpty()) {
            String v = queue.remove();                  //dequeue vertex
            topOrder.add(v);                            //add it to the order
            for (String w : graph.adjacents(v)) {         //for each w adjacent to v
                Integer newVal = graph.indegree(w) - 1; //indegree[v]--s 
                graph.updateIndegree(w, newVal);
                if (newVal == 0) //if that makes it 0
                {
                    queue.add(w);                            //add w to queue
                }
            }
        }
        if (topOrder.size() == graph.size()) {
            //every node has been considerd, we have a top. order
            System.out.printf("%s: %s\n", fileName, topOrder);
        } else {
            //we have a cycle to find
            System.out.printf("%s: graph has a cycle: %s\n",
                    fileName, processCycle(graph));
        }
    }

    /**
     * 
     * @param graph a graph without cycles   
     * @return 
     */
    
    private static String processCycle(DiGraph graph) {

        Map<String, Integer> badIndegrees = new HashMap<>(); //only indegree > 0;
        Map<String, List<String>> incommings = new HashMap<>(); //only among those with indegrees > 0
        LinkedHashSet<String> used = new LinkedHashSet<>(); //all vertices used as you go backwards
        
        //filter and select only badIndegrees
        for (Map.Entry<String, Integer> e : graph.getIndegrees().entrySet()) {
            if (e.getValue() > 0) {
                //System.out.println(e);
                badIndegrees.put(e.getKey(), e.getValue());
                incommings.put(e.getKey(), graph.incommings(e.getKey()));
            }
        }
        
        String v = DiGraph.selectFirstKey(badIndegrees);
        String lastV = null;

        //traverse the graph backward to find a cycle
        boolean flag = false;
        do {
            used.add(v);
            List<String> ins = incommings.get(v);
            for (String w : ins) {
                if (used.contains(w)) {
                    lastV = w;
                    flag = true;
                    break;
                }
                if (badIndegrees.containsKey(w)) {
                    v = w;
                }
            }

        } while (!flag);

        //select cycle, basically we stored the last known vertext and traverse
        //the used list in descending order until we find this same vertex again
        //which is the cycle
        StringBuilder buffer = new StringBuilder(lastV + "->");
        Iterator<String> itr = new LinkedList<String>(used).descendingIterator();
        while (itr.hasNext()) {
            String next = itr.next();
            if (next.equals(lastV)) {
                break;
            }
            buffer.append(next).append("->");

        }
        buffer.append(lastV);

        return buffer.toString();
    }

    public static void main(String[] args) {

        for (String arg : args) {

            try {
                long start = System.currentTimeMillis();
                processFile(arg);
                long end = System.currentTimeMillis();
                System.out.println("Elapsed time (ms): " + (end - start));
            } catch (IOException e) {
                System.out.println("File " + arg + " could not be read");
                e.printStackTrace();
            }

        }

    }

}