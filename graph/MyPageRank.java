package graph;

import java.util.*;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.Graph;
import support.graph.PageRank;

/**
 * In this class you will implement one of many different versions
 * of the PageRank algorithm. This algorithm will only work on
 * directed graphs. Keep in mind that there are many different ways
 * to handle sinks.
 *
 * Make sure you review the help slides and handout for details on
 * the PageRank algorithm.
 *
 */
public class MyPageRank<V> implements PageRank<V> {
	private Graph<V> _g;
	private List<CS16Vertex<V>> _vertices;
	private Map<CS16Vertex<V>, Double> _vertsToRanks;
	private static final double _dampingFactor = 0.85;
	private static final int _maxIterations = 100;
	private static final double _error = 0.01;
	private ArrayList<Double> _currentRank;
	private ArrayList<Double> _previousRank;
	private ArrayList<Integer> _outgoing;

	/**
	 * TODO: Feel free to add in anything else necessary to store the information
	 * needed to calculate the rank. Maybe make something to keep track of sinks,
	 * your ranks, and your outgoing edges?
	 */

	/**
	 * The main method that does the calculations! You'll want to call the methods
	 * that initialize your variables here. You'll also want to decide on a
	 * type of loop - for loop, do while, or while loop - for your calculations.
	 *
	 * @return A Map of every Vertex to its corresponding rank
	 *
	 */

	@Override
	public Map<CS16Vertex<V>, Double> calcPageRank(Graph<V> g) {

		//Instantiate variables
		_g = g;
		_vertices = new ArrayList<>();
		_vertsToRanks = new HashMap<CS16Vertex<V>,Double>();
		_currentRank = new ArrayList<>();
		_previousRank = new ArrayList<>();
		_outgoing = new ArrayList<>();

		//Get the vertices
		Iterator<CS16Vertex<V>> vertices = _g.vertices();

		//Add information about the vertex for every vertex
		while(vertices.hasNext()) {
			CS16Vertex<V> vertex = vertices.next();
			_vertices.add(vertex);
			_currentRank.add((double)1/_g.getNumVertices());
			_previousRank.add((double)1/_g.getNumVertices());
			_outgoing.add(_g.numOutgoingEdges(vertex));
		}

		//Check for sinks
		this.handleSinks();

		//Counter to keep track of what iteration
		int count = 0;

		//Margin of error
		double errorMargin = 1;

		//
		while(count < _maxIterations && errorMargin >_error) {

			//Set the previous and current rank for each vertex
			for(int i = 0; i <_g.getNumVertices(); i++) {
				_previousRank.set(i, _currentRank.get(i));
				_currentRank.set(i, 0.0);
			}

			//Calculate the margin of error and set the page rank
			for(int j = 0; j < _g.getNumVertices(); j++) {
				_currentRank.set(j, pageRank(j));
				errorMargin = Math.abs(_currentRank.get(j) - _previousRank.get(j));
			}

			count++;

		}

		this.removeBlacklist();

		//Insert final rank into _vertsToRanks
		for (int k = 0; k < _g.getNumVertices(); k++) {

			_vertsToRanks.put(_vertices.get(k), _currentRank.get(k));

		}

		return _vertsToRanks;
	}

	/**
	 * Method used to account for sink pages (those with no outgoing
	 * edges). There are multiple ways you can implement this, check
	 * the lecture and help slides!
	 *
	 * Made this method public so that it could be tested.
	 */
	private void handleSinks() {

		//List of sinks
		List<CS16Vertex<V>> sinksList = new ArrayList<>();

		//Loop through vertices
		for(int i=0; i < _g.getNumVertices(); i++ ) {

			//If there is a sink add it to the list of sinks
			if(_outgoing.get(i) == 0) {
				sinksList.add(_vertices.get(i));
			}

		}

		this.sinkHelper(sinksList);
	}

	//Method to make handleSinks() cleaner
	private void sinkHelper(List<CS16Vertex<V>> sinksList) {
		Iterator<CS16Vertex<V>> sinks = sinksList.iterator();

		//For every item in sink add an edge to every vertex in the graph
		while(sinks.hasNext()) {

			CS16Vertex<V> sink = sinks.next();

			//For every sink loop through every vertex and insert an edge
			for(int i = 0; i < _g.getNumVertices(); i++) {
				_g.insertEdge(sink, _vertices.get(i), null);
				_outgoing.set(_vertices.indexOf(sink), _outgoing.get(_vertices.indexOf(sink)) + 1);
			}
		}
	}


	//Used to calculate the pageRank of a certain vertex
	private Double pageRank(int i) {

		//Initialize the rank
		double pageRank = 0;

		//Get the incoming edges
		Iterator<CS16Edge<V>> incomingEdges = _g.incomingEdges(_vertices.get(i));

		//Iterate through vertex's incoming edges
		while (incomingEdges.hasNext()) {

			//Get rank of incoming vertices
			CS16Edge<V> incomingEdge = incomingEdges.next();
			CS16Vertex<V> source = _g.opposite(_vertices.get(i), incomingEdge);
			pageRank += (_previousRank.get(_vertices.indexOf(source))/_outgoing.get(_vertices.indexOf(source)));
		}

		//Calculate pageRank
		pageRank = (1.0 - _dampingFactor)/(_g.getNumVertices()) + _dampingFactor * (pageRank);

		return pageRank;

	}

	//If a vertex is blacklisted it's rank will be reduced to rank/num vertices, and every vertex's rank will be
	//increase by blacklisted rank/num vertices
	private void removeBlacklist() {

		//Get blacklist set
		Set<String> blacklist = PageRank.blacklist;

		//Get vertices
		Iterator<CS16Vertex<V>> vertices = _g.vertices();

		//Iterate through vertices
		while(vertices.hasNext()) {

			CS16Vertex<V> vertex = vertices.next();

			//If the vertex is in the blacklist
			if(blacklist.contains(vertex.getVertexName())) {

				//Calculate the blacklisted vertex new rank
				double blacklistRank = _currentRank.get(_vertices.indexOf(vertex))/_g.getNumVertices();

				_currentRank.set(_vertices.indexOf(vertex), blacklistRank);

				//Increment each vertex's rank by the blacklisted vertex new rank
				for(int i = 0; i < _g.getNumVertices(); i++) {

					//If the vertex isn't the blacklisted vertex
					if(i != _vertices.indexOf(vertex)) {

						_currentRank.set(i, _currentRank.get(i) + blacklistRank);

					}
				}

			}
		}

	}

}
