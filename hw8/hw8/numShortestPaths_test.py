#/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import numShortestPaths
from importlib import reload
reload(numShortestPaths)
from numShortestPaths import *
from mygraph import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.

# Here are some example test functions.

def sample_test():
    assert True != False, 'Error: True is equal to False'

def test1():

    g = MyGraph()
    v0 = GraphVertex("v0")
    v1 = GraphVertex("v1")
    v2 = GraphVertex("v2")
    g.insertVertex(v0)
    g.insertVertex(v1)
    g.insertVertex(v2)

    e0 = GraphEdge("e0", 1)
    e1 = GraphEdge("e1", 1)
    e2 = GraphEdge("e2", 1)

    g.insertEdge(v0, v2, e0)
    g.insertEdge(v0, v1, e1)
    g.insertEdge(v1, v2, e2)

    ret = numShortestPaths(g, v0, v2)

    assert ret == 1


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
    return [sample_test, test1]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
