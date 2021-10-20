from heappriorityqueue import *
from mygraph import *

def dijkstra(g, src):
    """ Calculate the shortest path tree from the src in the input
    connected graph g using Dijkstra's algorithm. The elements attached
    to the edges should be the distances. Must run in O((|E| + |V|) log |V|)
    time using the provided HeapPriorityQueue data structure.

    Returns the shortest path tree in the form of a new MyGraph object.
    Do not modify the input MyGraph instance.

    Raise the InvalidInputException if input is None or if src is not in g.

    Note: To access the actual vertices in the HeapPriorityQueue,
    you need to call pop().value(), not just pop().


    function dijkstra(G, s):
        // Input: graph G with vertices V, and source s
        // Output: Nothing
        // Purpose: Decorate nodes with shortest distance from s 
        for v in V:
            v.dist = infinity  // Initialize distance decorations
            v.prev = null      // Initialize previous pointers to null
        s.dist = 0         // Set distance to start to 0
   
        PQ = PriorityQueue(V)    // Use v.dist as priorities
        while PQ not empty:
            u = PQ.removeMin()
            for all edges (u, v): //each edge coming out of u
                if u.dist + cost(u, v) < v.dist: // cost() is weight 
                    v.dist = u.dist + cost(u,v) // Replace as necessary 
                    v.prev = u // Maintain pointers for path 
                    PQ.decreaseKey(v, v.dist)
    """
    if g == None or src == None or g.containsVertex(src) == False:
        raise InvalidInputException("Please input a valid graph and vertex")

    HPQ = HeapPriorityQueue()
    ShortestPath = MyGraph()
    for v in g.vertices():
        ShortestPath.insertVertex(v)

        if v == src:
            v.dist = 0
            v.prev = None
            v.entry = HPQ.push(v.dist, v)

        else: 
            v.dist = float('inf')
            v.prev = None
            v.entry = HPQ.push(v.dist, v)

    while not HPQ.isEmpty():
        u = HPQ.pop()
    
        for edge in g.incidentEdges(u.value()):
            v = g.opposite(u.value(), edge)
            if u.key() + edge.element() < v.dist:
                v.dist = u.key() + edge.element()
                v.prev = u.value()
                HPQ.replaceKey(v.entry, v.dist)

    
    for v in ShortestPath.vertices():

        if not v == src:

            ShortestPath.insertEdge(v.prev, v, g.connectingEdge(v.prev, v))
            print(g.connectingEdge(v.prev, v).element())
        
    return ShortestPath

class InvalidInputException(Exception):
    def __str__(self):
        return "Invalid Input Given."
