#! /usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import bintree
from importlib import reload
reload(bintree)
from bintree import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.


# Here are some example test functions.

#Idea for why parent is working
#BinTree().parent(node) is returning just the parent of the node
#BinTree().left(.....) is returning the children of the node including the parent node
#Either need to make .parent() return children too 
#Or .left(...) return just the parent node
#To do above possibly create a node (BinTree) that contains the whole tree and grab the
#parent node from the tree?
def simple_test_1():
    # Setup simple graph
    bt = BinTree()
    bt.addRoot("A")
    bt.addLeft(bt.root(), "B")
    bt.addRight(bt.root(), "C")

    # Check graph properties using assert
    assert bt.root().value() == "A", "ERROR: Incorrect root"
    assert bt.right(bt.root()).value() == "C"
    assert bt.left(bt.root()).value() == "B"
    

def node_exists_test():
    bt = BinTree()
    assert bt.isEmpty() == True, "BinTree should be empty"
    bt.addRoot("A")
    assert bt.addRoot("B") == bt.root(), "Root node should not be replaced"
    assert bt.addLeft(bt.root(), "C") == bt.left(bt.root()), "The left of the root node should equal the added left node"
    assert bt.addRight(bt.root(), "F") == bt.right(bt.root()), "The right of the root node should equal the added left right"
    assert bt.hasRight(bt.root()) == True, "The root node should have a right node"
    assert bt.hasLeft(bt.root()) == True, "The root node should have a left node"
    assert bt.hasLeft(bt.left(bt.root())) == False, "The left node of the root node should not have a left node"
    assert bt.hasRight(bt.right(bt.root())) == False, "The right node of the root node should not have a right node"
    assert bt.isEmpty() == False, "BinTree should not be empty"
    assert bt.root().value() == "A", "Root value shouldn't have been changed"
    assert bt.parent(bt.left(bt.root())) == bt.root(), "The parent of the left node should be the root node"
    assert bt.children(bt.root()) == [bt.left(bt.root()), bt.right(bt.root())], "Not returning the correct children"
    assert bt.isExternal(bt.root()) == False, "Root node is internal"
    assert bt.isInternal(bt.left(bt.root())) == False, "The node left of the root is external"
    #assert bt.size() == 3

def multiple_layer_test():
    bt = BinTree()
    bt.addRoot(1)
    assert bt.isRoot(bt.addRoot(2)) == True, "Add root method should still return the original root"
    bt.addLeft(bt.root(), 2)
    bt.addRight(bt.root(), 3)
    bt.addLeft(bt.left(bt.root()), 4)
    bt.addRight(bt.left(bt.root()), 5)
    bt.addLeft(bt.right(bt.root()), 6)
    bt.addRight(bt.right(bt.root()), 7)
    assert bt.left(bt.right(bt.root())).value() == 6, "Right child of right node not being added to tree"
    assert bt.right(bt.right(bt.root())).value() == 7, "Left child of right node not being added to tree"
    assert bt.left(bt.left(bt.root())).value() == 4, "Left child of left node not being added to tree"
    assert bt.right(bt.left(bt.root())).value() == 5, "Right child of left node not being added to tree"
    assert bt.size() == 7, "There should be 7 nodes in the tree"
    assert bt.height() == 2, "Height of tree should be 2"
    assert bt.left(bt.right(bt.root())).depth() == 2, "Depth of the root node's lowest child should be 2"
    bt.addRight(bt.left(bt.right(bt.root())), 8)
    assert bt.right(bt.left(bt.right(bt.root()))).hasRight() == False, "Most recently added child should not have a right node"
    assert bt.left(bt.right(bt.root())).hasRight() == True, "Parent of most recently added child should have a right node"
    assert bt.parent(bt.left(bt.root())) == bt.root(), "Parent of the child of the root node should be the root node"
    assert bt.parent(bt.right(bt.left(bt.right(bt.root())))) == bt.left(bt.right(bt.root())), "Parent method not working for lower children"
    assert bt.addLeft(bt.root(), 3).value() == 2, "left node getting overrun by addLeft"
    assert bt.addRight(bt.root(), 4).value() == 3, "right node getting overrun by addRight"
    assert bt.children(bt.left(bt.right(bt.root()))) == [bt.right(bt.left(bt.right(bt.root())))], "Children method not working for only one child"
    assert bt.children(bt.right(bt.left(bt.right(bt.root())))) == [], "Lowest node should return an empty list because it doesn't have any children"

def none_test():
    bt = BinTree()
    bt.addRoot(None)
    assert bt.parent(bt.root()) == None
    assert bt.root().value() == None
    bt.addLeft(bt.root(), None)
    bt.addRight(bt.root(), None)
    assert bt.left(bt.root()).value() == None
    assert bt.right(bt.root()).value() == None
    assert bt.isEmpty() == False



def exception_test():
    with pytest.raises(EmptyBinTreeException):
        bt = BinTree()
        bt.root()
        bt.height()
    with pytest.raises(InvalidInputException):
        bt = BinTree()
        bt.addLeft(None, "A")
        bt.addRight(None, "B")
        bt.isInternal(None)
        bt.isExternal(None)
        bt.children(None)
        bt.parent(None)
        bt.isRoot(None)
        bt.left(None)
        bt.right(None)
        bt.hasRight(None)
        bt.hasLeft(None)
    with pytest.raises(EmptyBinTreeException):
        bt = BinTree()
        bt.addLeft(bt.root(), "A")
        bt.addRight(bt.root(), "B")

    # Call this method to see what your graph looks like
    # Your program will not continue running until you exit the popup

def get_tests():
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # IMPORTANT
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
    return [simple_test_1, exception_test, node_exists_test, multiple_layer_test, none_test]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
