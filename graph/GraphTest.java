package graph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static support.graph.Constants.MAX_VERTICES;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import support.graph.*;

/**
 * This class tests the functionality of a Graph based on a 'String' type
 * parameter for the vertices. Edge elements are Integers. The general framework
 * of a test case is: - Name the test method descriptively, mentioning what is
 * being tested (it is ok to have slightly verbose method names here) - Set-up
 * the program state (ex: instantiate a heap and insert K,V pairs into it) - Use
 * assertions to validate that the program is in the state you expect it to be
 * See header comments over tests for what each test does
 * 
 * Before each test is run, you can assume that the '_graph' variable is reset to
 * a new instance of the a Graph<String> that you can simply use 'as is'
 * via the methods under the 'DO NOT MODIFY ANYTHING BELOW THIS LINE' line.
 * Of course, please do not modify anything below that, or the above 
 * assumptions may be broken.
 */
@RunWith(Parameterized.class)
public class GraphTest {
    

    // Undirected Graph instance variable
    private Graph<String> _graph;
    // Directed Graph instance variable
    private Graph<String> _dirGraph;
    private String _graphClassName;
	
    /**
     * A simple test for insertVertex, that adds 3 vertices and then checks to
     * make sure they were added by accessing them through the vertices
     * iterator.
     */
    @Test(timeout = 10000)
    public void testInsertVertexAndNumVertices() {
        //Make sure num vertices is 0 at start
        assertThat(_graph.getNumVertices(), is(0));

        // insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _graph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
        assertThat(_graph.getNumVertices(), is(3));
    }

    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testInsertVertexAndNumVerticesDirected() {
        //Make sure num vertices is 0
        assertThat(_dirGraph.getNumVertices(), is(0));

        // insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the vertex iterator to get a list of the vertices in the actual
        // graph
        List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();
        Iterator<CS16Vertex<String>> it = _dirGraph.vertices();
        while (it.hasNext()) {
            actualVertices.add(it.next());
        }

        // assert that the graph state is consistent with what you expect
        assertThat(actualVertices.size(), is(3));
        assertThat(actualVertices.contains(A), is(true));
        assertThat(actualVertices.contains(B), is(true));
        assertThat(actualVertices.contains(C), is(true));
        assertThat(_dirGraph.getNumVertices(), is(3));
    }


    /**
     * A simple test for insertEdges that adds 3 vertices, adds two edges to the
     * graph and then asserts that both edges were in fact added using the edge
     * iterator as well as checks to make sure their from and to vertices were
     * set correctly.
     */
    @Test(timeout = 10000)
    public void testInsertEdges() {
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _graph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }


    // Same test as above, but with a directed graph
    @Test(timeout = 10000)
    public void testInsertEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        // use the edge iterator to get a list of the edges in the actual graph.
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        // use the edge iterator to get a list of the edges in the actual graph.
        List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();
        Iterator<CS16Edge<String>> it = _dirGraph.edges();
        while (it.hasNext()) {
            actualEdges.add(it.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(actualEdges.size(), is(2));
        assertThat(actualEdges.contains(ab), is(true));
        assertThat(actualEdges.contains(bc), is(true));
    }

    //Checking to see if vertices() works
    @Test(timeout = 10000)
    public void testVertices() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        //Call vertices() method
        Iterator<CS16Vertex<String>> vertices = _graph.vertices();

        //Create array list to check iterator
        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Check to make sure vertices() is returning correct array size with correct vertices
        assertThat(testVertices.size(), is(3));
        assertThat(testVertices.contains(A), is(true));
        assertThat(testVertices.contains(B), is(true));
        assertThat(testVertices.contains(C), is(true));
        assertThat(_graph.getNumVertices(), is(3));

        //Remove vertices
        _graph.removeVertex(A);
        _graph.removeVertex(B);

        assertThat(_graph.getNumVertices(), is(1));

        //Insert new ones
        CS16Vertex<String> D = _graph.insertVertex("D");
        CS16Vertex<String> E = _graph.insertVertex("E");
        CS16Vertex<String> F = _graph.insertVertex("F");

        Iterator<CS16Vertex<String>> newVertices = _graph.vertices();

        ArrayList<CS16Vertex<String>> newTestVertices = new ArrayList<>();

        while(newVertices.hasNext()) {
            newTestVertices.add(newVertices.next());
        }

        //Make sure after removing/adding vertices still returns correct iterator in vertices()
        assertThat(newTestVertices.size(), is(4));
        assertThat(newTestVertices.contains(A), is(false));
        assertThat(newTestVertices.contains(B), is(false));
        assertThat(newTestVertices.contains(C), is(true));
        assertThat(newTestVertices.contains(D), is(true));
        assertThat(newTestVertices.contains(E), is(true));
        assertThat(newTestVertices.contains(F), is(true));
        assertThat(_graph.getNumVertices(), is(4));

    }

    //Check to see if vertices() is working for directed graph
    @Test(timeout = 10000)
    public void testVerticesDirected() {

        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        Iterator<CS16Vertex<String>> vertices = _dirGraph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Check to make sure all expected vertices are in iterator
        assertThat(testVertices.size(), is(3));
        assertThat(testVertices.contains(A), is(true));
        assertThat(testVertices.contains(B), is(true));
        assertThat(testVertices.contains(C), is(true));
        assertThat(_dirGraph.getNumVertices(), is(3));


        //Remove vertices
        _dirGraph.removeVertex(A);
        _dirGraph.removeVertex(B);

        assertThat(_dirGraph.getNumVertices(), is(1));

        //Insert new ones
        CS16Vertex<String> D = _dirGraph.insertVertex("D");
        CS16Vertex<String> E = _dirGraph.insertVertex("E");
        CS16Vertex<String> F = _dirGraph.insertVertex("F");

        Iterator<CS16Vertex<String>> newVertices = _dirGraph.vertices();

        ArrayList<CS16Vertex<String>> newTestVertices = new ArrayList<>();

        while(newVertices.hasNext()) {
            newTestVertices.add(newVertices.next());
        }

        //Make sure after removing/adding vertices still returns correct iterator in vertices()
        assertThat(newTestVertices.size(), is(4));
        assertThat(newTestVertices.contains(A), is(false));
        assertThat(newTestVertices.contains(B), is(false));
        assertThat(newTestVertices.contains(C), is(true));
        assertThat(newTestVertices.contains(D), is(true));
        assertThat(newTestVertices.contains(E), is(true));
        assertThat(newTestVertices.contains(F), is(true));
        assertThat(_dirGraph.getNumVertices(), is(4));
    }

    //Check to make sure edges() is returning expected iterator
    @Test(timeout = 10000)
    public void testEdges() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);

        Iterator<CS16Edge<String>> edges = _graph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        //Make sure edges are in returned iterator
        assertThat(testEdges.size(), is(2));
        assertThat(testEdges.contains(ab), is(true));
        assertThat(testEdges.contains(bc), is(true));

        //Remove edges
        _graph.removeEdge(ab);
        _graph.removeEdge(bc);

        //Insert new vertex
        CS16Vertex<String> D = _graph.insertVertex("D");

        //Insert new edges
        CS16Edge<String> cd = _graph.insertEdge(B, D, 5);
        CS16Edge<String> de = _graph.insertEdge(A, C, 10);
        CS16Edge<String> ef = _graph.insertEdge(C, D, 30);

        Iterator<CS16Edge<String>> newEdges = _graph.edges();

        ArrayList<CS16Edge<String>> newTestEdges = new ArrayList<>();
        while (newEdges.hasNext()) {
            newTestEdges.add(newEdges.next());
        }

        //Check after removing then inserting edges, edges() returns correct edges
        assertThat(newTestEdges.size(), is(3));
        assertThat(newTestEdges.contains(ab), is(false));
        assertThat(newTestEdges.contains(bc), is(false));
        assertThat(newTestEdges.contains(cd), is(true));
        assertThat(newTestEdges.contains(de), is(true));
        assertThat(newTestEdges.contains(ef), is(true));


    }

    //Check edges() on directed graph
    @Test(timeout = 10000)
    public void testEdgesDirected() {
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);

        Iterator<CS16Edge<String>> edges = _dirGraph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        // assert that the graph state is consistent with what you expect.
        assertThat(testEdges.size(), is(2));
        assertThat(testEdges.contains(ab), is(true));
        assertThat(testEdges.contains(bc), is(true));

        //Remove edges
        _dirGraph.removeEdge(ab);
        _dirGraph.removeEdge(bc);

        //Insert new vertex
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        //Insert new edges
        CS16Edge<String> cd = _dirGraph.insertEdge(B, D, 5);
        CS16Edge<String> de = _dirGraph.insertEdge(A, C, 10);
        CS16Edge<String> ef = _dirGraph.insertEdge(C, D, 30);

        Iterator<CS16Edge<String>> newEdges = _dirGraph.edges();

        ArrayList<CS16Edge<String>> newTestEdges = new ArrayList<>();
        while (newEdges.hasNext()) {
            newTestEdges.add(newEdges.next());
        }

        //Check after removing then inserting edges, edges() returns correct edges
        assertThat(newTestEdges.size(), is(3));
        assertThat(newTestEdges.contains(ab), is(false));
        assertThat(newTestEdges.contains(bc), is(false));
        assertThat(newTestEdges.contains(cd), is(true));
        assertThat(newTestEdges.contains(de), is(true));
        assertThat(newTestEdges.contains(ef), is(true));
    }

    //Check to make sure removeVertex() is correctly removing vertices and edges
    @Test(timeout = 10000)
    public void testRemoveVertex() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        //Remove vertex
        _graph.removeVertex(A);

        //Get edges from graph
        Iterator<CS16Edge<String>> edges = _graph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        //Get vertices from graph
        Iterator<CS16Vertex<String>> vertices = _graph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Make sure edges connected to A were removed
        assertThat(testEdges.size(), is(1));
        assertThat(testEdges.contains(ab), is(false));
        assertThat(testEdges.contains(ca), is(false));
        assertThat(testEdges.contains(bc), is(true));

        //Make sure A was removed
        assertThat(testVertices.contains(A), is(false));
        assertThat(testVertices.contains(B), is(true));
        assertThat(testVertices.contains(C), is(true));

        //Now remove B from graph
        _graph.removeVertex(B);

        //Get edges from graph
        Iterator<CS16Edge<String>> newEdges = _graph.edges();

        ArrayList<CS16Edge<String>> newTestEdges = new ArrayList<>();
        while (newEdges.hasNext()) {
            newTestEdges.add(newEdges.next());
        }

        //Get vertices from graph
        Iterator<CS16Vertex<String>> newVertices = _graph.vertices();

        ArrayList<CS16Vertex<String>> newTestVertices = new ArrayList<>();
        while (newVertices.hasNext()) {
            newTestVertices.add(newVertices.next());
        }

        assertThat(newTestVertices.size(), is(1));
        assertThat(newTestEdges.size(), is(0));
        assertThat(newTestEdges.contains(bc), is(false));
        assertThat(newTestVertices.contains(B), is(false));
        assertThat(newTestVertices.contains(C), is(true));

        //Make sure it works with one vertex in graph
        _graph.removeVertex(C);

        //Get vertices from graph
        Iterator<CS16Vertex<String>> newVerticesTwo = _graph.vertices();

        ArrayList<CS16Vertex<String>> newTestVerticesTwo = new ArrayList<>();
        while (newVerticesTwo.hasNext()) {
            newTestVerticesTwo.add(newVerticesTwo.next());
        }

        assertThat(newTestVerticesTwo.size(), is(0));
        assertThat(newTestVerticesTwo.contains(C), is(false));

    }

    //Check to make sure removeVertex() is correctly removing vertices and edges from directed graph
    @Test(timeout = 10000)
    public void testRemoveVertexDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);

        //Remove vertex
        _dirGraph.removeVertex(A);

        //Get edges from graph
        Iterator<CS16Edge<String>> edges = _dirGraph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        //Get vertices from graph
        Iterator<CS16Vertex<String>> vertices = _dirGraph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Make sure edges connected to A were removed
        assertThat(testEdges.size(), is(1));
        assertThat(testEdges.contains(ab), is(false));
        assertThat(testEdges.contains(ca), is(false));
        assertThat(testEdges.contains(bc), is(true));

        //Make sure A was removed
        assertThat(testVertices.contains(A), is(false));
        assertThat(testVertices.contains(B), is(true));
        assertThat(testVertices.contains(C), is(true));

        //Now remove B from graph
        _dirGraph.removeVertex(B);

        //Get edges from graph
        Iterator<CS16Edge<String>> newEdges = _dirGraph.edges();

        ArrayList<CS16Edge<String>> newTestEdges = new ArrayList<>();
        while (newEdges.hasNext()) {
            newTestEdges.add(newEdges.next());
        }

        //Get vertices from graph
        Iterator<CS16Vertex<String>> newVertices = _dirGraph.vertices();

        ArrayList<CS16Vertex<String>> newTestVertices = new ArrayList<>();
        while (newVertices.hasNext()) {
            newTestVertices.add(newVertices.next());
        }

        assertThat(newTestVertices.size(), is(1));
        assertThat(newTestEdges.size(), is(0));
        assertThat(newTestEdges.contains(bc), is(false));
        assertThat(newTestVertices.contains(B), is(false));
        assertThat(newTestVertices.contains(C), is(true));

        //Make sure it works with one vertex in graph
        _dirGraph.removeVertex(C);

        //Get vertices from graph
        Iterator<CS16Vertex<String>> newVerticesTwo = _dirGraph.vertices();

        ArrayList<CS16Vertex<String>> newTestVerticesTwo = new ArrayList<>();
        while (newVerticesTwo.hasNext()) {
            newTestVerticesTwo.add(newVerticesTwo.next());
        }

        assertThat(newTestVerticesTwo.size(), is(0));
        assertThat(newTestVerticesTwo.contains(C), is(false));

    }

    //Check to make sure removeEdge is correctly removing edges
    @Test(timeout = 10000)
    public void testRemoveEdge() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        //Remove edges
        _graph.removeEdge(ab);
        _graph.removeEdge(bc);

        //Get edges from graph
        Iterator<CS16Edge<String>> edges = _graph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        //Get vertices from graph
        Iterator<CS16Vertex<String>> vertices = _graph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Make sure edges ab and bc were removed
        assertThat(testEdges.size(), is(1));
        assertThat(testEdges.contains(ab), is(false));
        assertThat(testEdges.contains(bc), is(false));
        assertThat(testEdges.contains(ca), is(true));

        //Make sure vertices are still there
        assertThat(testVertices.contains(A), is(true));
        assertThat(testVertices.contains(B), is(true));
        assertThat(testVertices.contains(C), is(true));

        //Check to make sure correctly removing last edge
        _graph.removeEdge(ca);

        //Get edges from graph
        Iterator<CS16Edge<String>> newEdges = _graph.edges();

        ArrayList<CS16Edge<String>> newTestEdges = new ArrayList<>();
        while (newEdges.hasNext()) {
            newTestEdges.add(newEdges.next());
        }

        assertThat(newTestEdges.size(), is(0));
        assertThat(newTestEdges.contains(ca), is(false));

    }

    //Check to make sure removeEdge is correctly removing edges for directed graph
    @Test(timeout = 10000)
    public void testRemoveEdgeDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);

        //Remove edges
        _dirGraph.removeEdge(ab);
        _dirGraph.removeEdge(bc);

        //Get edges from graph
        Iterator<CS16Edge<String>> edges = _dirGraph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        //Get vertices from graph
        Iterator<CS16Vertex<String>> vertices = _dirGraph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Make sure edges ab and bc were removed
        assertThat(testEdges.size(), is(1));
        assertThat(testEdges.contains(ab), is(false));
        assertThat(testEdges.contains(bc), is(false));
        assertThat(testEdges.contains(ca), is(true));

        //Make sure vertices are still there
        assertThat(testVertices.contains(A), is(true));
        assertThat(testVertices.contains(B), is(true));
        assertThat(testVertices.contains(C), is(true));

        //Check to make sure correctly removing last edge
        _dirGraph.removeEdge(ca);

        //Get edges from graph
        Iterator<CS16Edge<String>> newEdges = _dirGraph.edges();

        ArrayList<CS16Edge<String>> newTestEdges = new ArrayList<>();
        while (newEdges.hasNext()) {
            newTestEdges.add(newEdges.next());
        }

        assertThat(newTestEdges.size(), is(0));
        assertThat(newTestEdges.contains(ca), is(false));

    }

    //Check to make sure connectingEdge is correctly returning edges
    @Test(timeout = 10000)
    public void testConnectingEdge() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        //Check connecting edges
        assertThat(_graph.connectingEdge(A, B), is(ab));
        assertThat(_graph.connectingEdge(B, C), is(bc));
        assertThat(_graph.connectingEdge(C, A), is(ca));

        //Check connecting edges returns correct edge after removing edge
        _graph.removeEdge(ab);

        assertThat(_graph.connectingEdge(B, C), is(bc));
        assertThat(_graph.connectingEdge(C, A), is(ca));

        //Check connecting edges returns correct edge after removing vertex
        _graph.removeVertex(A);

        assertThat(_graph.connectingEdge(B, C), is(bc));

    }

    //Check to make sure connectingEdge is correctly returning edges on directed graph
    @Test(timeout = 10000)
    public void testConnectingEdgeDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);

        //Check connecting edges
        assertThat(_dirGraph.connectingEdge(A, B), is(ab));
        assertThat(_dirGraph.connectingEdge(B, C), is(bc));
        assertThat(_dirGraph.connectingEdge(C, A), is(ca));

        //Check connecting edges returns correct edge after removing edge
        _dirGraph.removeEdge(ab);

        assertThat(_dirGraph.connectingEdge(B, C), is(bc));
        assertThat(_dirGraph.connectingEdge(C, A), is(ca));

        //Check connecting edges returns correct edge after removing vertex
        _dirGraph.removeVertex(A);

        assertThat(_dirGraph.connectingEdge(B, C), is(bc));

    }

    //Check to make sure incomingEdges() is correctly returning edges
    @Test(timeout = 10000)
    public void testIncomingEdges() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        //Check incoming edges
        Iterator<CS16Edge<String>> incomingEdges = _graph.incomingEdges(A);
        ArrayList<CS16Edge<String>> incomingEdgesTest = new ArrayList<>();
        while(incomingEdges.hasNext()) {
            incomingEdgesTest.add(incomingEdges.next());
        }

        assertThat(incomingEdgesTest.size(), is(2));
        assertThat(incomingEdgesTest.contains(ab), is(true));
        assertThat(incomingEdgesTest.contains(ca), is(true));

        //Check to make sure it's working for one incoming edge
        _graph.removeEdge(ab);

        Iterator<CS16Edge<String>> newIncomingEdges = _graph.incomingEdges(A);
        ArrayList<CS16Edge<String>> newIncomingEdgesTest = new ArrayList<>();
        while(newIncomingEdges.hasNext()) {
            newIncomingEdgesTest.add(newIncomingEdges.next());
        }

        assertThat(newIncomingEdgesTest.size(), is(1));
        assertThat(newIncomingEdgesTest.contains(ab), is(false));
        assertThat(newIncomingEdgesTest.contains(ca), is(true));

        //Check to make sure it's working for no incoming edges
        _graph.removeEdge(ca);

        Iterator<CS16Edge<String>> newIncomingEdgesTwo = _graph.incomingEdges(A);
        ArrayList<CS16Edge<String>> newIncomingEdgesTestTwo = new ArrayList<>();
        while(newIncomingEdgesTwo.hasNext()) {
            newIncomingEdgesTestTwo.add(newIncomingEdgesTwo.next());
        }

        assertThat(newIncomingEdgesTestTwo.size(), is(0));
        assertThat(newIncomingEdgesTestTwo.contains(ab), is(false));
        assertThat(newIncomingEdgesTestTwo.contains(ca), is(false));

    }

    //Check to make sure incomingEdges() is correctly returning edges for directed graph
    @Test(timeout = 10000)
    public void testIncomingEdgesDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        //Add edges
        CS16Edge<String> ba = _dirGraph.insertEdge(B, A, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);

        //Check incoming edges
        Iterator<CS16Edge<String>> incomingEdges = _dirGraph.incomingEdges(A);
        ArrayList<CS16Edge<String>> incomingEdgesTest = new ArrayList<>();
        while(incomingEdges.hasNext()) {
            incomingEdgesTest.add(incomingEdges.next());
        }

        //Check to make sure its working for multiple incoming edges
        assertThat(incomingEdgesTest.size(), is(2));
        assertThat(incomingEdgesTest.contains(ba), is(true));
        assertThat(incomingEdgesTest.contains(ca), is(true));

        //Check to make sure it's working for one incoming edge
        _dirGraph.removeEdge(ca);

        Iterator<CS16Edge<String>> newIncomingEdges = _dirGraph.incomingEdges(A);
        ArrayList<CS16Edge<String>> newIncomingEdgesTest = new ArrayList<>();
        while(newIncomingEdges.hasNext()) {
            newIncomingEdgesTest.add(newIncomingEdges.next());
        }

        assertThat(newIncomingEdgesTest.size(), is(1));
        assertThat(newIncomingEdgesTest.contains(ba), is(true));
        assertThat(newIncomingEdgesTest.contains(ca), is(false));

        //Check to make sure it's working for no incoming edges
        _dirGraph.removeEdge(ba);

        Iterator<CS16Edge<String>> newIncomingEdgesTwo = _dirGraph.incomingEdges(A);
        ArrayList<CS16Edge<String>> newIncomingEdgesTestTwo = new ArrayList<>();
        while(newIncomingEdgesTwo.hasNext()) {
            newIncomingEdgesTestTwo.add(newIncomingEdgesTwo.next());
        }

        assertThat(newIncomingEdgesTestTwo.size(), is(0));
        assertThat(newIncomingEdgesTestTwo.contains(ba), is(false));
        assertThat(newIncomingEdgesTestTwo.contains(ca), is(false));

    }

    //Check to make sure outgoingEdges() is correctly returning edges
    @Test(timeout = 10000)
    public void testOutgoingEdges() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        //Check outgoing edges
        Iterator<CS16Edge<String>> outgoingEdges = _graph.outgoingEdges(A);
        ArrayList<CS16Edge<String>> outgoingEdgesTest = new ArrayList<>();
        while(outgoingEdges.hasNext()) {
            outgoingEdgesTest.add(outgoingEdges.next());
        }

        assertThat(outgoingEdgesTest.size(), is(2));
        assertThat(outgoingEdgesTest.contains(ab), is(true));
        assertThat(outgoingEdgesTest.contains(ca), is(true));

        //Check to make sure it's working for one outgoing edge
        _graph.removeEdge(ab);

        Iterator<CS16Edge<String>> newOutgoingEdges = _graph.outgoingEdges(A);
        ArrayList<CS16Edge<String>> newOutgoingEdgesTest = new ArrayList<>();
        while(newOutgoingEdges.hasNext()) {
            newOutgoingEdgesTest.add(newOutgoingEdges.next());
        }

        assertThat(newOutgoingEdgesTest.size(), is(1));
        assertThat(newOutgoingEdgesTest.contains(ab), is(false));
        assertThat(newOutgoingEdgesTest.contains(ca), is(true));

        //Check to make sure it's working for no outgoing edges
        _graph.removeEdge(ca);

        Iterator<CS16Edge<String>> newOutgoingEdgesTwo = _graph.outgoingEdges(A);
        ArrayList<CS16Edge<String>> newOutGoingEdgesTestTwo = new ArrayList<>();
        while(newOutgoingEdgesTwo.hasNext()) {
            newOutGoingEdgesTestTwo.add(newOutgoingEdgesTwo.next());
        }

        assertThat(newOutGoingEdgesTestTwo.size(), is(0));
        assertThat(newOutGoingEdgesTestTwo.contains(ab), is(false));
        assertThat(newOutGoingEdgesTestTwo.contains(ca), is(false));

    }

    //Check to make sure outgoingEdges() is correctly returning edges for directed graph
    @Test(timeout = 10000)
    public void testOutgoingEdgesAndNumOutgoingEdgesDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        //Add edges
        CS16Edge<String> ba = _dirGraph.insertEdge(B, A, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);

        //Check incoming edges
        Iterator<CS16Edge<String>> outgoingEdges = _dirGraph.outgoingEdges(B);
        ArrayList<CS16Edge<String>> outgoingEdgesTest = new ArrayList<>();
        while(outgoingEdges.hasNext()) {
            outgoingEdgesTest.add(outgoingEdges.next());
        }

        //Check to make sure its working for multiple outgoing edges
        assertThat(outgoingEdgesTest.size(), is(2));
        assertThat(outgoingEdgesTest.contains(ba), is(true));
        assertThat(outgoingEdgesTest.contains(bc), is(true));
        assertThat(outgoingEdgesTest.contains(ca), is(false));
        assertThat(_dirGraph.numOutgoingEdges(B), is(2));

        //Check to make sure it's working for one outgoing edge

        Iterator<CS16Edge<String>> newOutgoingEdges = _dirGraph.outgoingEdges(C);
        ArrayList<CS16Edge<String>> newOutgoingEdgesTest = new ArrayList<>();
        while(newOutgoingEdges.hasNext()) {
            newOutgoingEdgesTest.add(newOutgoingEdges.next());
        }

        assertThat(newOutgoingEdgesTest.size(), is(1));
        assertThat(newOutgoingEdgesTest.contains(ca), is(true));
        assertThat(newOutgoingEdgesTest.contains(bc), is(false));
        assertThat(_dirGraph.numOutgoingEdges(C), is(1));

        //Check to make sure it's working for no outgoing edges

        Iterator<CS16Edge<String>> newOutgoingEdgesTwo = _dirGraph.outgoingEdges(A);
        ArrayList<CS16Edge<String>> newOutgoingEdgesTestTwo = new ArrayList<>();
        while(newOutgoingEdgesTwo.hasNext()) {
            newOutgoingEdgesTestTwo.add(newOutgoingEdgesTwo.next());
        }

        assertThat(newOutgoingEdgesTestTwo.size(), is(0));
        assertThat(newOutgoingEdgesTestTwo.contains(ba), is(false));
        assertThat(newOutgoingEdgesTestTwo.contains(ca), is(false));
        assertThat(_dirGraph.numOutgoingEdges(A), is(0));

    }

    //Check to make sure opposite is correctly returning edges
    @Test(timeout = 10000)
    public void testOpposite() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        assertThat(_graph.opposite(A, ab), is(B));
        assertThat(_graph.opposite(B, ab), is(A));
        assertThat(_graph.opposite(B, bc), is(C));
        assertThat(_graph.opposite(C, bc), is(B));
        assertThat(_graph.opposite(C, ca), is(A));
        assertThat(_graph.opposite(A, ca), is(C));

    }

    //Check to make sure opposite is correctly returning edges for directed graph
    @Test(timeout = 10000)
    public void testOppositeDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);

        //Check that opposite is returning correct vertex
        assertThat(_dirGraph.opposite(A, ab), is(B));
        assertThat(_dirGraph.opposite(B, ab), is(A));
        assertThat(_dirGraph.opposite(B, bc), is(C));
        assertThat(_dirGraph.opposite(C, bc), is(B));
        assertThat(_dirGraph.opposite(C, ca), is(A));
        assertThat(_dirGraph.opposite(A, ca), is(C));

    }

    //Check to make sure endVertices() is correctly returning vertices
    @Test(timeout = 10000)
    public void testEndVertices() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        //Make sure endVertices is returning both end vertices for each edge
        assertThat(_graph.endVertices(ab).contains(B), is(true));
        assertThat(_graph.endVertices(ab).contains(A), is(true));
        assertThat(_graph.endVertices(bc).contains(B), is(true));
        assertThat(_graph.endVertices(bc).contains(C), is(true));
        assertThat(_graph.endVertices(ca).contains(A), is(true));
        assertThat(_graph.endVertices(ca).contains(C), is(true));

    }

    //Check to make sure endVertices() is correctly returning vertices for directed graph
    @Test(timeout = 10000)
    public void testEndVerticesDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);

        //Make sure endVertices is returning both end vertices for each edge
        assertThat(_dirGraph.endVertices(ab).contains(B), is(true));
        assertThat(_dirGraph.endVertices(ab).contains(A), is(true));
        assertThat(_dirGraph.endVertices(bc).contains(B), is(true));
        assertThat(_dirGraph.endVertices(bc).contains(C), is(true));
        assertThat(_dirGraph.endVertices(ca).contains(A), is(true));
        assertThat(_dirGraph.endVertices(ca).contains(C), is(true));
    }

    //Check to make sure areAdjacent() is returning correct boolean value
    @Test(timeout = 10000)
    public void testAreAdjacent() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");
        CS16Vertex<String> D = _graph.insertVertex("D");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);
        CS16Edge<String> da = _graph.insertEdge(D, A, 4);

        //Make sure areAdjacent works for adjacent/ non adjacent vertices
        assertThat(_graph.areAdjacent(A, B), is(true));
        assertThat(_graph.areAdjacent(B, C), is(true));
        assertThat(_graph.areAdjacent(C, A), is(true));
        assertThat(_graph.areAdjacent(A, D), is(true));
        assertThat(_graph.areAdjacent(D, C), is(false));


    }

    //Check to make sure areAdjacent() is returning correct boolean value for directed graph
    @Test(timeout = 10000)
    public void testAreAdjacentDirected() {
        //Insert vertices
        CS16Vertex<String> A = _dirGraph.insertVertex("A");
        CS16Vertex<String> B = _dirGraph.insertVertex("B");
        CS16Vertex<String> C = _dirGraph.insertVertex("C");
        CS16Vertex<String> D = _dirGraph.insertVertex("D");

        //Add edges
        CS16Edge<String> ab = _dirGraph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _dirGraph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _dirGraph.insertEdge(C, A, 3);
        CS16Edge<String> da = _dirGraph.insertEdge(D, A, 4);

        //Make sure areAdjacent works for adjacent/ non adjacent vertices for directed graph
        assertThat(_dirGraph.areAdjacent(A, B), is(true));
        assertThat(_dirGraph.areAdjacent(B, C), is(true));
        assertThat(_dirGraph.areAdjacent(C, A), is(true));
        assertThat(_dirGraph.areAdjacent(A, D), is(true));
        assertThat(_dirGraph.areAdjacent(D, C), is(false));

    }

    //Check to make sure toggleDirected is clearing vertices set/ setting _directed to correct value
    @Test(timeout = 10000)
    public void testToggleDirected() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        _graph.toggleDirected();

        //Get vertices from graph
        Iterator<CS16Vertex<String>> vertices = _dirGraph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Get edges from graph
        Iterator<CS16Edge<String>> edges = _dirGraph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        assertThat(testVertices.size(), is(0));
        assertThat(testEdges.size(), is(0));

        //Check that vertices and edges set is cleared after toggling back to undirected graph
        _graph.toggleDirected();

        //Get vertices from graph
        Iterator<CS16Vertex<String>> newVertices = _graph.vertices();

        ArrayList<CS16Vertex<String>> testNewVertices = new ArrayList<>();
        while (newVertices.hasNext()) {
            testNewVertices.add(newVertices.next());
        }

        //Get edges from graph
        Iterator<CS16Edge<String>> newEdges = _graph.edges();

        ArrayList<CS16Edge<String>> testNewEdges = new ArrayList<>();
        while (newEdges.hasNext()) {
            testNewEdges.add(newEdges.next());
        }

        assertThat(testNewVertices.size(), is(0));
        assertThat(testNewEdges.size(), is(0));

    }

    //Check to make sure clear() is resetting graph
    @Test(timeout = 10000)
    public void testClear() {
        //Insert vertices
        CS16Vertex<String> A = _graph.insertVertex("A");
        CS16Vertex<String> B = _graph.insertVertex("B");
        CS16Vertex<String> C = _graph.insertVertex("C");

        //Add edges
        CS16Edge<String> ab = _graph.insertEdge(A, B, 1);
        CS16Edge<String> bc = _graph.insertEdge(B, C, 2);
        CS16Edge<String> ca = _graph.insertEdge(C, A, 3);

        _graph.clear();

        //Get vertices from graph
        Iterator<CS16Vertex<String>> vertices = _graph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        //Get edges from graph
        Iterator<CS16Edge<String>> edges = _graph.edges();

        ArrayList<CS16Edge<String>> testEdges = new ArrayList<>();
        while (edges.hasNext()) {
            testEdges.add(edges.next());
        }

        assertThat(testEdges.size(), is(0));
        assertThat(testVertices.size(), is(0));
        assertThat(_graph.getNumVertices(), is(0));

    }

    //Check to make sure each vertex has it's own unique number, and when a vertex is removed the next vertex's number
    //that is added is not overlapping
    @Test(timeout = 10000)
    public void testVertexNumber() {
        //Insert vertices until MAX_VERTICES
        for(int i = 0; i < MAX_VERTICES; i++) {
            CS16Vertex<String> A = _graph.insertVertex("A");
            assertThat(A.getVertexNumber(), is(i));
        }

        Iterator<CS16Vertex<String>> vertices = _graph.vertices();

        ArrayList<CS16Vertex<String>> testVertices = new ArrayList<>();
        while (vertices.hasNext()) {
            testVertices.add(vertices.next());
        }

        _graph.removeVertex(testVertices.get(100));
        _graph.removeVertex(testVertices.get(255));

        //Make sure that the graph is inserting into the position that was removed
        assertThat(_graph.insertVertex("A").getVertexNumber(), is(testVertices.get(255).getVertexNumber()));
        assertThat(_graph.insertVertex("A").getVertexNumber(), is(testVertices.get(100).getVertexNumber()));

    }


    /**
     * EXCEPTION TESTS
     *
     */

    @Test(expected = InvalidVertexException.class)
    public void testInsertEdgeThrowsInvalidVertexException() {
        CS16Vertex<String> A = _graph.insertVertex("A");

        _graph.insertEdge(null, A, 1);
        _graph.insertEdge(A, null, 1);
        _graph.insertEdge(null, null,1);

    }

    @Test(expected = InvalidVertexException.class)
    public void testRemoveVertexThrowsInvalidVertexException() {

        _graph.removeVertex(null);

    }

    @Test(expected = InvalidEdgeException.class)
    public void testRemoveEdgeThrowsInvalidEdgeException() {

        _graph.removeEdge(null);

    }

    @Test(expected = InvalidVertexException.class)
    public void testConnectingEdgeThrowsInvalidVertexException() {

       CS16Vertex<String> A =  _graph.insertVertex("A");

       _graph.connectingEdge(null, A);
       _graph.connectingEdge(A, null);
       _graph.connectingEdge(null, null);

    }

    @Test(expected = NoSuchEdgeException.class)
    public void testConnectingEdgeThrowsNoSuchEdgeException() {

        CS16Vertex<String> A =  _graph.insertVertex("A");
        CS16Vertex<String> B =  _graph.insertVertex("B");
        CS16Vertex<String> C =  _graph.insertVertex("C");

        CS16Edge<String> ab = _graph.insertEdge(A, B,1);

        _graph.connectingEdge(A, C);

    }

    @Test(expected = InvalidVertexException.class)
    public void testIncomingEdgesThrowsInvalidVertexException() {

        _graph.incomingEdges(null);

    }

    @Test(expected = InvalidVertexException.class)
    public void testOutgoingEdgesThrowsInvalidVertexException() {

        _graph.outgoingEdges(null);

    }

    @Test(expected = InvalidVertexException.class)
    public void testNumOutgoingEdgesThrowsInvalidVertexException() {

        _graph.numOutgoingEdges(null);

    }

    @Test(expected = DirectionException.class)
    public void testNumOutgoingEdgesThrowsDirectionException() {

        CS16Vertex<String> A =  _graph.insertVertex("A");
        _graph.numOutgoingEdges(A);

    }

    @Test(expected = InvalidVertexException.class)
    public void testOppositeThrowsInvalidVertexException() {

        CS16Vertex<String> A =  _graph.insertVertex("A");
        CS16Vertex<String> B =  _graph.insertVertex("B");

        CS16Edge<String> ab = _graph.insertEdge(A, B,1);

        _graph.opposite(null, ab);
    }

    @Test(expected = InvalidEdgeException.class)
    public void testOppositeThrowsInvalidEdgeException() {

        CS16Vertex<String> A =  _graph.insertVertex("A");
        CS16Vertex<String> B =  _graph.insertVertex("B");

        _graph.opposite(A, null);

    }

    @Test(expected = NoSuchVertexException.class)
    public void testOppositeThrowsNoSuchVertexException() {

        CS16Vertex<String> A =  _graph.insertVertex("A");
        CS16Vertex<String> B =  _graph.insertVertex("B");
        CS16Vertex<String> C =  _graph.insertVertex("C");


        CS16Edge<String> ab = _graph.insertEdge(A, B,1);

        _graph.opposite(C, ab);


    }

    @Test(expected = InvalidEdgeException.class)
    public void testEndVerticesThrowsInvalidEdgeException() {

        _graph.endVertices(null);

    }

    @Test(expected = InvalidVertexException.class)
    public void testAreAdjacentThrowsInvalidVertexException() {

        CS16Vertex<String> A =  _graph.insertVertex("A");

        _graph.areAdjacent(null, A);
        _graph.areAdjacent(A, null);
        _graph.areAdjacent(null, null);

    }


    /*
     * List of graphs for testing!
     */
    @Parameters(name = "with graph: {0}")
    public static Collection<String> graphs() {
        List<String> names = new ArrayList<>();
        names.add("graph.AdjacencyMatrixGraph");
        return names;
    }
    
    /*
     * ####################################################
     * 
     * DO NOT MODIFY ANYTHING BELOW THIS LINE
     * 
     * ####################################################
     */
	
	
    @SuppressWarnings("unchecked")
    @Before
	public void makeGraph() {
        Class<?> graphClass = null;
        try {
            graphClass = Class.forName(_graphClassName);
            Constructor<?> constructor = graphClass.getConstructors()[0];
            _graph = (Graph<String>) constructor.newInstance(false);
	    _dirGraph = (Graph<String>) constructor.newInstance(true);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            System.err.println("Exception while instantiating Graph class in GraphTest.");
            e.printStackTrace();
        }
	}
	
    public GraphTest(String graphClassName) {
	    this._graphClassName = graphClassName;
	}
}
