 Design Choices:
    In MyHeapEntry I decided to add key, value, and position instance variables. In my constructor I
    accept a key and value which I assign to the instance variables. This allows me to access/modify
    them with my designated getters and setters. For position I created a getter and setter that allows
    me to set the position of an entry when it is inserted, and get the entry's position that needs to
    be removed. In MyHeap a design choice I made was to assign positions to entries when I add them into
    the heap and remove them from the heap. This makes it very easy to remove an entry from the desired
    position. Another design choice I made in MyHeap was to access the last entry in the heap by using my
    deque. I did this because the last item in the deque will always be the last position in the tree,
    thus making it very easy to access. Originally I used my remove() function in MyLinkedHeapTree to
    access the last position in the heap, but this was removing the position from the heap before I could
    swap the entries.

    In MyHeap I decided to create an upHeap and downHeap method that recursively swaps the
    entries based on their keys, and assigns positions to the entries that are being swapped. This
    allowed me to factor out a significant amount of code from insert, remove, removeMin, and replaceKey.
    In MyLinkedHeapTree I added a getDeque function that returns the deque. This lets me to access the
    deque from MyHeap allowing me to get the last item in the deque as described above.

    To keep track of where to add and remove nodes in the tree I use a deque in MyLinkedHeapTree, and
    implemented add and remove for the deque. When a node is added to the tree I first check if the
    first node in the deque has a left child, if it doesn't I add the node as a left child to the first
    node in the deque then add the node to the deque. If it does have a left child I then add the node
    to the tree to the right of the first node in the deque, add the node to the deque, then remove the
    first node in the deque. In remove I first remove the last node in the deque. I then check to see if
    the parent of the last node has a left child, and if the parent of the last node equals the firstNode
    in the deque. If the last node's parent does have a left child, and isn't the first node in the deque
    I add the parent to the deque. This ensures that a node with two children isn't at the front of the
    deque. I then remove the node from the tree.

    All of my methods in MyLinkedHeapTree<E> run in big 0(1). In add/remove I use if statements to check for
    conditions, and insert/remove nodes into the deque and tree, and getDeque just returns my deque.

Tests:
    In MyLinkedHeapTreeTest I test my add and remove method by checking the node position every time I
    add/remove something. I also test my deque by adding/removing to the tree and comparing the deque
    to the positions in the tree. In MyHeapTest I test all of my exceptions first. The first edge case
    I check is removing only external nodes from the heap. I then test remove for only left nodes to
    guarantee the heap stays left complete and maintains the correct order. Next I test inserting something
    into the heap after removing to make sure the correct nodes are being removed and their aren't any
    left over nodes. I also test replacing values and keys to make sure the heap is maintaining the correct
    order and the correct values are being applied. Finally, I test removeMin for the root node and add
    a baseline test for remove.

    Final Handin