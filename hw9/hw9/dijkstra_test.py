#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import dijkstra
from importlib import reload
reload(dijkstra)
from dijkstra import *
from mygraph import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.

# Here are some example test functions. Feel free to delete example_test_1
# and remove it from the list in get_tests().

def example_test_1():
    assert 1 == 1, 'Error: 1 does not equal 1!'

def simple_test():
    # Setup simple graph
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)

    e0 = GraphEdge("e0", 8)
    e1 = GraphEdge("e1", 3)
    e2 = GraphEdge("e2", 4)
    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v1, v2, e2)

    # Run the algorithm
    ret = dijkstra(g, v0)

    # Make sure it matches our expectations
    assert e1 in ret.edges()
    assert e2 in ret.edges()
    assert e0 not in ret.edges()

    # If necessary you can take a look at the graph
    # you created for testing and what was returned
    g.popup()
    ret.popup()

def single_path_test():
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    v3 = GraphVertex("v3")

    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)
    g.insertVertex(v3)

    e0 = GraphEdge("e0", 100)
    e1 = GraphEdge("e1", 200)
    e2 = GraphEdge("e2", 500)

    g.insertEdge(v0, v1, e0)
    g.insertEdge(v1, v2, e1)
    g.insertEdge(v2, v3, e2)

    ret = dijkstra(g, v0)

    assert e0 in ret.edges() and e1 in ret.edges() and e2 in ret.edges(), "Graph with one path not working"


def none_graph_test():
    g = None
    v0 = GraphVertex("v0")

    with pytest.raises(InvalidInputException):    
        dijkstra(g, v0)

def none_src_test():
    g = MyGraph()
    v0 = None
    with pytest.raises(InvalidInputException):    
        dijkstra(g, v0)

def src_missing_test():
    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)

    e1 = GraphEdge("e1", 3)
    
    g.insertEdge(v0, v1, e1)

    # Run the algorithm

    with pytest.raises(InvalidInputException):    
        dijkstra(g, v2)

def multiple_shortest_paths():

    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    v4 = GraphVertex("v4")

    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)
    g.insertVertex(v4)

    e0 = GraphEdge("e1", 1)
    e1 = GraphEdge("e1", 1)
    e2 = GraphEdge("e1", 2)
    e3 = GraphEdge("e1", 2)
    
    g.insertEdge(v0, v1, e0)
    g.insertEdge(v0, v2, e1)
    g.insertEdge(v2, v4, e2)
    g.insertEdge(v1, v4, e3)

    ret = dijkstra(g, v0)

    assert e0 in ret.edges() and e3 in ret.edges() or e1 in ret.edges() and e2 in ret.edges(), "Graph with multiple shortest paths not working"
    
def single_vertex_test():
    g = MyGraph()
    v0 = GraphVertex("v0")
    g.insertVertex(v0)

    ret = dijkstra(g, v0)

    assert v0 in ret.vertices(), "Graph with single vertex not working"

def multiple_paths_test():
    g = MyGraph()

    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    v3 = GraphVertex("v3")
    v4 = GraphVertex("v4")
    v5 = GraphVertex("v5")
    v6 = GraphVertex("v6")

    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)
    g.insertVertex(v3)
    g.insertVertex(v4)
    g.insertVertex(v5)


    e0 = GraphEdge("e0", 6)
    e1 = GraphEdge("e1", 3)
    e2 = GraphEdge("e2", 2)
    e3 = GraphEdge("e3", 3)
    e4 = GraphEdge("e4", 4)
    e5 = GraphEdge("e5", 2)
    e6 = GraphEdge("e6", 1)

    g.insertEdge(v0, v1, e0)
    g.insertEdge(v0, v2, e1)
    g.insertEdge(v0, v3, e2)
    g.insertEdge(v1, v4, e3)
    g.insertEdge(v1, v5, e4)
    g.insertEdge(v2, v5, e5)
    g.insertEdge(v3, v5, e6)

    ret = dijkstra(g, v0)

    assert e2 in ret.edges() and e6 in ret.edges() and e0 in ret.edges() and e1 in ret.edges() and e3 in ret.edges(), "Graph not working with multiple paths but only one shortest"
    

def get_tests():
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # VERY IMPORTANT
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # Add the names of each of your test functions to this list. It is very
    # important that you do this, or the TAs will not run your tests properly
    # and you will not receive full credit.
    #
    # DO NOT remove either example test from this list, just add new tests like so:
    #       [example, example, new test,...]
    # We will not be able to properly grade your coal tests if you do not follow
    # these instructions! You will lose points on your submission for failing
    # to follow these instructions.
    return [multiple_paths_test]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
