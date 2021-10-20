package graph;

import java.util.*;

import net.datastructures.Entry;
import support.graph.CS16AdaptableHeapPriorityQueue;
import support.graph.CS16Edge;
import support.graph.CS16GraphVisualizer;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.MinSpanForest;

/**
 * In this class you will implement a slightly modified version
 * of the Prim-Jarnik algorithm for generating Minimum Spanning trees.
 * The original version of this algorithm will only generate the 
 * minimum spanning tree of the connected vertices in a graph, given
 * a starting vertex. Like Kruskal's, this algorithm can be modified to 
 * produce a minimum spanning forest with very little effort.
 *
 * See the handout for details on Prim-Jarnik's algorithm.
 * Like Kruskal's algorithm this algorithm makes extensive use of 
 * the decorator pattern, so make sure you know it.
 */
public class MyPrimJarnik<V> implements MinSpanForest<V> {

    /** 
     * This method implements Prim-Jarnik's algorithm and extends 
     * it slightly to account for disconnected graphs. You must return 
     * the collection of edges of the Minimum Spanning Forest (MSF) for 
     * the given graph, g.
     * 
     * This algorithm must run in O((|E| + |V|)log(|V|)) time
     * @param g Your graph
     * @param v Only used if you implement the optional animation.
     * @return returns a data structure that contains the edges of your MSF that implements java.util.Collection
     *
    */
    @Override
    public Collection<CS16Edge<V>> genMinSpanForest(Graph<V> g, CS16GraphVisualizer<V> visualizer) {

        //Decorators for each vertex
        MyDecorator<CS16Vertex<V>, Integer> costDecorator = new MyDecorator();
        MyDecorator<CS16Vertex<V>, CS16Edge<V>> prevDecorator = new MyDecorator();
        MyDecorator<CS16Vertex, Entry> entryDecorator = new MyDecorator();
        MyDecorator<CS16Vertex<V>, Boolean> inQueueDecorator = new MyDecorator<>();

        //Collection of edges to be returned
        ArrayList<CS16Edge<V>> edgesMSF = new ArrayList<>();

        //Priority Queue
        CS16AdaptableHeapPriorityQueue<Integer, CS16Vertex> PQ = new CS16AdaptableHeapPriorityQueue<Integer, CS16Vertex>();

        //Iterator to loop through each vertex
        Iterator<CS16Vertex<V>> vertices = g.vertices();

        //Initialize each vertex with decoration values
        while(vertices.hasNext()) {

            CS16Vertex vertex = vertices.next();

            costDecorator.setDecoration(vertex, Integer.MAX_VALUE);
            prevDecorator.setDecoration(vertex, null);
            Entry entry = PQ.insert(costDecorator.getDecoration(vertex), vertex);
            entryDecorator.setDecoration(vertex, entry);
            inQueueDecorator.setDecoration(vertex, true);

        }

        while(!PQ.isEmpty()) {

            //Remove vertex with minimum cost value
            Entry<Integer, CS16Vertex> v = PQ.removeMin();

            //Set decorator that checks whether the vertex is in the PQ to false
            inQueueDecorator.setDecoration(v.getValue(), false);

            //If the vertex has an edge associated with it
            if(prevDecorator.getDecoration(v.getValue()) != null) {

                //Add the edge into the collection of edges
                edgesMSF.add(prevDecorator.getDecoration(v.getValue()));
            }

            //Iterator to loop through each of the vertex's outgoing edges
            Iterator<CS16Edge<V>> edgeIterator = g.outgoingEdges(v.getValue());
           while(edgeIterator.hasNext()) {
               CS16Edge edge = edgeIterator.next();

               //Vertex opposite of the outgoing edge
               CS16Vertex<V> u = g.opposite(v.getValue(), edge);

               //If the vertex is in the queue
               if(inQueueDecorator.getDecoration(u)) {

                   //If the cost decoration is greater than the edge
                   if(costDecorator.getDecoration(u) > edge.element()) {

                       //Update cost decoration
                       costDecorator.setDecoration(u, edge.element());

                       //Add the edge to the prev decorator
                       prevDecorator.setDecoration(u, edge);

                       //Update the key vertex's key in the PQ
                       PQ.replaceKey(entryDecorator.getDecoration(u), costDecorator.getDecoration(u));
                   }
               }
           }
        }

        //Return edges that make up the MSF
        return edgesMSF;
      }
    }
