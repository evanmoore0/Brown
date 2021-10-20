Handin: Final Handin


Design Choices:

One design choice I made in AdjacencyMatrixGraph was to use a stack to store the vertex number of removed vertices. Then,
when I insert a vertex into the graph I first check to see if there are any vertex numbers in the stack, if there
are I set the vertex number to be the previously removed vertex number. This ensures that when adding/removing vertices
each vertex will always have a unique number, and the vertex number will not surpass the max number of vertices the graph
can have. Another design choice I made in AdjacencyMatrixGraph was to create a counter for the number of outgoing edges.
I made the counter an instance variable because it allows me to just call my outgoing edges method in num outgoing edges
and return the instance variable. A design choice I made in MyPrimJarnik was to create an in queue decorator which
decorates the vertices based on whether they are in the queue allowing me to access the decorator when I want to check
if something is in the queue. I also stored edges in my prev decorator instead of vertices because it allowed me to
directly add the edges into the MSF. In MyPageRank I created a separate PageRank method that returns the calculated
rank of a page. This allowed me to factor out code, and make my program cleaner. Finally, I created my removeBlacklist
method that implements my answer to the conceptual question below.



Test Cases:

AdjacencyMatrixGraph: Tested each method on directed/undirected graphs (except for methods that are only supposed to be
called on directed/undirected graphs)
    -insertVertex: Test num vertices in the given insert vertex method. I also test insert vertex throuhgout my other
    tests. Example: in my test vertex number method
    -insertEdge: Test insert edge throughout my other tests. Example: edges test
    -vertices: Make sure vertices is correctly returning all vertices after removing/adding vertices
    -edges: Test edges by removing/adding edges and making sure the correct edges are still returned by edges method
    -removeVertex/edge: Test by adding vertices/edges, then removing until there are not any left
    -connectingEdge: Make sure that it correctly returns edge even after removing vertices
    -incomingEdge: Test for multiple incoming edges, one incoming edge, and no incoming edges
    -outgoingEdge: Test for multiple outgoing edges, one outgoing edge, no outgoing edges, also test num outgoing in
    same method
    -opposite: Call opposite on every edge, make sure it returns desired vertex
    -endVertices: Call on every edge, make sure its returning desired vertices
    -areAdjacent: Test for vertices that are adjacent, and that are not adjacent
    -toggleDirected: Toggle the graph so its directed, then toggle back, and make sure that the graph was cleared
    -clear: Create a graph, call clear on it, make sure everything was reset
    -testVertexNumber: Insert vertices until there are the max number of vertices. Then remove vertices, insert new
    ones, and make sure that the newly inserted vertices have the same vertex number as the removed vertices
    -exceptionTests: Created an exception test for every exception each method is supposed to through

MsfTest
    -noEdgeTest: makes sure that MyPrimJarnik is not returning any edges when there are no edges in the graph
    -noVerticesTest: checks that no edges are returned when there is nothing in the graph
    -onePathTest: MyPrimJarnik must return every node in the graph
    -sameWeightTest: checks for graph that contains edges that all have the same weight
    -connectedNodeTest: every node is connected to one central node, MyPrimJarnik should return every edge in the graph
    -infinityDistanceTest: create two edges with infinity as distance, should not return any edges
    -multiplePathsTest: creates a graph with multiple edges, makes sure MyPrimJarnik is correctly returning edges

MyPageRankTest
    -noPagesTest: nothing in the adjacency matrix, should return empty map
    -onePageTest: if there is one page, it's page rank value should be 1
    -sinkTest: tests MyPageRank when there is a sink in the graph
    -multipleSinksTest: tests MyPageRank when there are multiple sinks in the graph
    -sameRankTest: each page has an incoming and outgoing edge, therefore each page should have the same rank
    -multipleLayersTest: the page at the end of multiple pages should have the highest rank a->b->c (c should have
    highest rank, followed by b, then a)
    -multipleIncomingEdgesTest: checks to make sure that a page with more incoming edges has a higher rank

Conceptual Question:

To ensure that an undesired page has a low rank on a given search you could take the pages rank value and divide
it by the number of pages in the graph and redistribute the page's rank throughout the graph. Therefore, the page would
still have a rank, but every other page would have a higher rank value. For example, if you have three pages, A, B, C,
A is the undesired page and has a rank value of 0.5 while B and C have a rank value of 0.25. You would take A's rank
value divide it by 3, then distribute equally throughout the graph. This would result in A having a page rank of 0.167,
B having a page rank of 0.4167, and C having a page rank of 0.4167. Now, the undesired page has the lowest rank, but
still has a rank, and all of the other pages are equally incremented thus preserving their original ranks. To do this
in an algorithm I potentially would create a decorator that decorates a page if we don't want to display it's real rank,
then at the end of page rank I would loop through each page, and if the page is decorated divide it's page rank,
distribute divided page rank to all other pages, and set the decorated page rank to be rank/num pages.

