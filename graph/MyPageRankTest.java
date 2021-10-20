package graph;

import static org.junit.Assert.*;

import org.junit.Test;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import java.util.Map;

/**
 * This class tests the functionality of your PageRank algorithm on a
 * directed AdjacencyMatrixGraph. The general framework of a test case is:
 * - Name the test method descriptively,
 * - Mention what is being tested (it is ok to have slightly verbose method names here)
 *
 * Some tips to keep in mind when writing test cases:
 * - All pages' ranks should total to 1.
 * - It will be easier to start out by writing test cases on smaller graphs.
 *
 */
public class MyPageRankTest {

	// This is your margin of error for testing
	double _epsilon = 0.03;

	/**
     * A simple test with four pages. Each page only has one
	 * outgoing link to a different page, resulting in a square
	 * shape or cycle when visualized. The pages' total ranks is 1.
     */
	@Test
	public void testFourEqualRanks() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");

		/**
		  * Inserting an edge with a null element since a weighted edge is not
		  * meaningful for the PageRank algorithm.
		  */

		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(c,d,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(d,a,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		// Check that the number of vertices returned by PageRank is 4
		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// The weights of each vertex should be 0.25
		double expectedRankA = 0.25;
		double expectedRankB = 0.25;
		double expectedRankC = 0.25;
		double expectedRankD = 0.25;

		// The sum of weights should always be 1
		assertEquals(total, 1, _epsilon);

		// The Rank for each vertex should be 0.25 +/- epsilon
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);
		assertEquals(expectedRankC, output.get(c), _epsilon);
		assertEquals(expectedRankD, output.get(d), _epsilon);

	}

	/**
     	 * A simple test with three pages. Note that vertex A's
	 * rank is not 0 even though it has no incoming edges,
	 * demonstrating the effects of the damping factor and handling sinks.
     	 */
	@Test
	public void simpleTestOne() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Edge<String> e0 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 3);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		// These are precomputed values
		double expectedRankA = 0.186;
		double expectedRankB = 0.342;
		double expectedRankC = 0.471;

		assertEquals(total, 1, _epsilon);
		assertEquals(expectedRankA, output.get(a), _epsilon);
		assertEquals(expectedRankB, output.get(b), _epsilon);
		assertEquals(expectedRankC, output.get(c), _epsilon);

	}


	/**
	  * TODO: Add your own tests here. Instead of checking for specific rank values,
	  * make test cases comparing the relative ranks of pages (e.g. using an assertThat statement)!
	  */

	//Make sure PageRank works for empty adjMatrix
	@Test
	public void noPagesTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 0);

	}

	//Make sure PageRank works for one page
	@Test
	public void onePageTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 1);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		//Expected rank
		double expectedRankA = 1;

		assertEquals(total, 1, _epsilon);
		assertEquals(expectedRankA, output.get(a), _epsilon);
	}

	//Page at the end of a line of pages should have the greatest page rank
	@Test
	public void multipleLayersTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");
		CS16Edge<String> e1 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(c,d,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		assertEquals(total, 1, _epsilon);
		//d should have the greatest rank, followed by c, b, a
		assertTrue(output.get(a) < output.get(b) && output.get(b) < output.get(c) && output.get(c) < output.get(d));

	}

	//Make sure pageRank works with a sink
	@Test
	public void sinkTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");
		CS16Vertex<String> e = adjMatrix.insertVertex("E");
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(a,c,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(d,c,null);
		CS16Edge<String> e4 = adjMatrix.insertEdge(e,c,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 5);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		//C's rank should be greater than all other ranks
		assertEquals(total, 1, _epsilon);
		assertTrue(output.get(a) < output.get(c));
		//Other ranks should be equal to each other
		assertEquals(output.get(a), output.get(b), _epsilon);
		assertEquals(output.get(b), output.get(d), _epsilon);
		assertEquals(output.get(d), output.get(e), _epsilon);

	}

	//Make sure pageRank works with multiple sinks
	@Test
	public void multipleSinksTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");
		CS16Vertex<String> e = adjMatrix.insertVertex("E");
		CS16Edge<String> e1 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(a,c,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(a,d,null);
		CS16Edge<String> e4 = adjMatrix.insertEdge(b,d,null);
		CS16Edge<String> e5 = adjMatrix.insertEdge(a,e,null);
		CS16Edge<String> e6 = adjMatrix.insertEdge(b,e,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 5);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		assertEquals(total, 1, _epsilon);
		assertTrue(output.get(a) < output.get(c));
		assertTrue(output.get(b) < output.get(d));

		//d, e and c should have the same rank
		assertEquals(output.get(d), output.get(c), _epsilon);
		assertEquals(output.get(c), output.get(e), _epsilon);
		//a and b should have the same rank
		assertEquals(output.get(a), output.get(b), _epsilon);
	}

	//Make sure pageRank works when each page has one outgoing edge and one incoming edge
	@Test
	public void sameRankTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");
		CS16Edge<String> e1 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(b,c,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(c,d,null);
		CS16Edge<String> e4 = adjMatrix.insertEdge(d,a,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		assertEquals(total, 1, _epsilon);
		//All ranks should be equal
		assertEquals(output.get(a), output.get(b), _epsilon);
		assertEquals(output.get(b), output.get(c), _epsilon);
		assertEquals(output.get(c), output.get(d), _epsilon);

	}

	//Checks to make sure that a page with more incoming edges has a higher page rank
	@Test
	public void multipleIncomingEdgesTest() {
		Graph<String> adjMatrix = new AdjacencyMatrixGraph<String>(true);
		CS16Vertex<String> a = adjMatrix.insertVertex("A");
		CS16Vertex<String> b = adjMatrix.insertVertex("B");
		CS16Vertex<String> c = adjMatrix.insertVertex("C");
		CS16Vertex<String> d = adjMatrix.insertVertex("D");
		CS16Edge<String> e1 = adjMatrix.insertEdge(a,b,null);
		CS16Edge<String> e2 = adjMatrix.insertEdge(c,b,null);
		CS16Edge<String> e3 = adjMatrix.insertEdge(c,d,null);

		MyPageRank<String> pr = new MyPageRank<String>();

		Map<CS16Vertex<String>, Double> output = pr.calcPageRank(adjMatrix);

		assertEquals(output.size(), 4);
		double total = 0;
		for (double rank: output.values()) {
			total += rank;
		}

		assertEquals(total, 1, _epsilon);

		//Makes sure that a page with more incoming edges has a higher page rank
		assertTrue(output.get(b) > output.get(d));

	}




}
