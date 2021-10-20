#!/usr/bin/python3
# bintree.py

""" Binary Tree module

Implement a node-and-link based Binary Tree structure

"""

from queue import Queue

class EmptyBinTreeException(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return repr(self.value)

class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

class Node:

    def __init__(self, parent, value) : #TODO
        """
        Input: Node (implicit argument), parent: Node, value: anything
        Output: Nothing
        Purpose: constructor for a Node
        """
        self.nodeParent = parent
        self.nodeValue = value
        self.leftNode = None
        self.rightNode = None
        self.nodeDepth = 0

    def parent(self): #TODO
        """
        Input: Node (implicit argument)
        Output: Node
        Purpose: get the parent of this Node (if possible)
        """
        return self.nodeParent

    def left(self): #TODO
        """
        Input: Node (implicit argument)
        Output: Node
        Purpose: get the left child of this node (if possible)
        """
        
        return self.leftNode

    def right(self): #TODO
        """
        Input: Node (implicit argument)
        Output: Node
        Purpose: get the right child of this node (if possible)
        """
        
        return self.rightNode

    def addLeft(self, value) : #TODO
        """
        Input: Node (implicit argument), value: anything
        Output: Node (the left child)
        Purpose: add a left child to this node with the given value if there isn't one already and return it.
                If there is one already, just return the current one
        """
        if self.leftNode == None:
            self.leftNode = Node(self, value)
            return self.leftNode
        else:
            return self.leftNode

    def addRight(self, value) : #TODO
        """
        Input: Node (implicit argument), value: anything
        Output: Node (the right child)
        Purpose: add a right child to this node with the given value if there isn't one already and return it.
                 If there is one already, just return the current one
        """

        if self.rightNode == None:
            self.rightNode = Node(self, value)
            return self.rightNode
        else:
            return self.rightNode

    def hasLeft(self): #TODO
        """
        Input: Node (implicit argument)
        Output: boolean
        Purpose: return whether this node has a left child
        """
        
        if self.leftNode == None:
            return False
        else:
            return True

    def hasRight(self): #TODO
        """
        Input: Node (implicit argument)
        Output: boolean
        Purpose: return whether the node has a right child
        """
        if self.rightNode == None:
            return False
        else:
            return True

    def value(self): #TODO
        """
        Input: Node (implicit argument)
        Output: anything
        Purpose: return the value stored at this Node
        """
        return self.nodeValue

    def depth(self): #TODO
        """
        Input: Node (implicit argument)
        Output: int
        Purpose: return the depth of this node in the tree in O(1)
        """
        if self.nodeParent != None:
            self.nodeDepth = self.nodeParent.depth()
            self.nodeDepth +=1
        return self.nodeDepth


    ''' This is a helper method.
    You DO NOT need to touch this method.'''
    def __str__(self):
        """
        Input: Node (implicit argument)
        Output: String representation of the Node
        Purpose: printing
        """
        output = ""
        output += "(val: "
        output += repr(self.value())
        output += "; L: "
        if self.hasLeft():
            output += str(self.left())
        else:
            output += "<nothing>"
        output += "; R: "
        if self.hasRight():
            output += str(self.right())
        else:
            output += "<nothing>"
        output += ")"
        return output

class BinTree:
    """ Binary Tree class

    Implement a node-and-link based Binary Tree structure
    """

    def __init__(self) : #TODO
        """
        Input: BinTree (implicit argument)
        Output: Nothing
        Purpose: Creates an empty binary tree
        """
        self.binRoot = None
        self.binTreeSize = 0
        self.binHeight = 0

    def root(self):  #TODO
        """
        Input: BinTree (implicit argument)
        Output: Node
        Purpose: return the root node
        Throw a EmptyBinTreeException if the tree is empty
        """
        if self.binRoot == None:
            raise EmptyBinTreeException("The tree is empty!")
        else:
            return self.binRoot

    def parent(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: Node
        Purpose: return the parent node
        Exceptions: throw an InvalidInputException if input is None
        """
        if node == None:
            raise InvalidInputException("You must pass a node into parent")
        return node.parent()

    def children(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: List of child nodes
        Purpose: returns a list of direct child nodes
        Exceptions: throw an InvalidInputException if input is None
        """

        if node == None:
            raise InvalidInputException("You must pass a node into children")

        if node.hasRight() and node.hasLeft():
            return [node.left(), node.right()]
        elif node.hasLeft():
            return [node.left()]
        elif node.hasRight():
            return [node.right()]
        else:
            return []


    def isEmpty(self): #TODO
        """
        Input: BinTree (implicit argument)
        Output: boolean
        Purpose: return true if the tree is empty, false otherwise in O(1)
        """
        if self.binRoot == None:
            return True
        else:
            return False


    def size(self): #TODO
        """
        Input: BinTree (implicit argument)
        Output: int
        Purpose: return the size of the tree in O(1)
        """
        return self.binTreeSize

    def height(self): #TODO
        """
        Input: BinTree (implicit argument)
        Output: int
        Purpose: return the height of the tree in O(1) time
        Exceptions: throw an EmptyBinTreeException if the height is undefined
        """
        if self.binRoot == None:
            raise EmptyBinTreeException("You can't find the height of an empty tree")
        
        return self.binHeight + 1

    def isInternal(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: boolean
        Purpose: return whether the node is internal.
        Exceptions: throw an InvalidInputException if input is None
        """

        if node == None:
            raise InvalidInputException("You must pass a node into isInternal")

        if node.hasRight() or node.hasLeft():
            return True
        else:
            return False

    def isExternal(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: boolean
        Purpose: return whether the node is external.
        Exceptions: throw an InvalidInputException if input is None
        """

        if node == None:
            raise InvalidInputException("You must pass a node into isExternal")

        if node.hasRight() or node.hasLeft():
            return False
        else:
            return True

    def isRoot(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: boolean
        Purpose: return whether the node is the root
        Exceptions: throw an InvalidInputException if input is None
        """
        if node == None:
            raise InvalidInputException("You must pass a node into isRoot")

        if node == self.binRoot:
            return True
        else:
            return False

    def left(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: Node
        Purpose: get the left child of the node (if possible)
        Exceptions: throw an InvalidInputException if input is None
        """

        if node == None:
            raise InvalidInputException("You must pass a node into left")

        if node.hasLeft():
            return node.left()

    def right(self, node):  #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: Node
        Purpose: get the right child of the node (if possible)
        Exceptions: throw an InvalidInputException if input is None
        """

        if node == None:
            raise InvalidInputException("You must pass a node into right")

        if node.hasRight():
            return node.right()


    def hasLeft(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: boolean
        Purpose: return whether the node has a left child
        Exceptions: throw an InvalidInputException if input is None
        """

        if node == None:
            raise InvalidInputException("You must pass a node into hasLeft")

        if node.hasLeft():
            return True
        else:
            return False

    def hasRight(self, node): #TODO
        """
        Input: BinTree (implicit argument), node: Node
        Output: boolean
        Purpose: return whether the node has a right child
        Exceptions: throw an InvalidInputException if input is None
        """


        if node == None:
            raise InvalidInputException("You must pass a node into hasRight")

        if node.hasRight():
            return True
        else:
            return False

    def addRoot(self, e): #TODO
        """
        Input: BinTree (implicit argument), e: anything
        Output: Node (the root node)
        Purpose: add a root to the tree only if there isn't one already and return it.
                 If there is one already, just return the current one
        """

        if self.binRoot == None:
            self.binRoot = Node(None, e)
            self.binTreeSize += 1
            return self.binRoot
        else:
            return self.binRoot

    def addLeft(self, node, e): #TODO
        """
        Input: BinTree (implicit argument), node: Node, e: anything
        Output: the left child of the node
        Purpose: add a left child to the node only if there isn't one already and return it.
                 If there is one already, just return the current one
        Exceptions: throw an InvalidInputException if node input is None
        """

        if node == None:
            raise InvalidInputException("Only nodes can be added to the tree")

        self.binTreeSize +=1
        self.binHeight = node.depth()
        return node.addLeft(e)

    def addRight(self, node, e): #TODO
        """
        Input: BinTree (implicit argument), node: Node, e: anything
        Output: the right child of the node
        Purpose: add a right child to the node only if there isn't one already and return it.
                 If there is one already, return it
        Exceptions: throw an InvalidInputException if node input is None
        """

        if node == None:
            raise InvalidInputException("Only nodes can be added to the tree")

        self.binTreeSize+=1
        self.binHeight = node.depth()
        return node.addRight(e)

    def __str__(self):
        """
        Input: BinTree (implicit argument)
        Output: String representation of BinTree
        Purpose: printing
        """
        toReturn = 'Size: ' + str(self.size()) + '\n'
        toReturn += 'Height: ' + str(self.height()) + '\n'
        toReturn += str(self.root())
        return toReturn


    """ Helper methods for tree visualization.
    You DON'T need to touch these """

    def graphic(self):
        """Returns a representation of this graph as a .dot file.

        In other words, if you pass the string returned by this method into
        the program DOT (or, better yet, NEATO), you can get an image file
        of the graph."""
        strs = ["graph\n{\n"]

        def annex_vertex(v):
            strs.append("\t" + str(v.value()) + ";\n")

        def annex_edge(v):
            if v.hasLeft():
                strs.append("\t" + str(v.value()) + "--" + str(v.left().value()) + ";\n")
            if v.hasRight():
                strs.append("\t" + str(v.value()) + "--" + str(v.right().value()) + ";\n")

        self.parseVerts(annex_vertex, annex_edge)
        strs.append("}\n")
        return ''.join(strs)

    def popup(self):
        """Opens a new window with this graph rendered by DOT.
        Sequential calls to this function will show the window
        once at a time. """
        import os
        tmp = open("./.tmpgraph", "w+")
        tmp.write(self.graphic())
        tmp.close()
        os.system("dot -Tpng ./.tmpgraph | display")


    def parseVerts(self, f1, f2):
        Q = Queue()
        Q.put(self.root())
        while not Q.empty():
            v = Q.get()
            f1(v)
            f2(v)
            if v.hasLeft():
                Q.put(v.left())
            if v.hasRight():
                Q.put(v.right())
