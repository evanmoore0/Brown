class InvalidInputException(Exception):
    def __str__(self):
        return "Invalid Input given."

def numShortestPaths(g, start, end):
    """graph, start node, end node -> int
    Purpose: find the number of shortest paths between two nodes in a graph
    Raises: raise InvalidInputException if an input is None, or
    if start or end are not in g
    //Input: start vertex //Output: Nothing
    Q = new Queue() 
    start.visited = true Q.enqueue(start)
     while Q is not empty:
      node = Q.dequeue()
      doSomething(node)
      for neighbor in adj nodes:
         if not neighbor.visited:
           neighbor.visited = true
           Q.enqueue(neighbor)
    
    # """
    # start = []
    # start.append(start)

    # while !start.isEmpty():
    # incidentEdges = []
    # for node in g.incidentEdges(start):
    #     if node== end:
    #         return 1
    #     else:
    #         incidentEdges.append(node)
        
        
