from random import *

class InvalidEntryException(Exception):
    """This exception is raised when an entry that is passed in is not
    in the heap"""
    pass

class InvalidPositionException(Exception):
    """This exception is raised when no entry exists at the position
    that is passed in"""
    pass

class HeapException(Exception):
    """This exception is raised when some operation on the heap would be
    invalid, such as trying to pop from an empty heap."""
    pass

class HeapPriorityQueue(object):
    """An (adapted) priority queue implemented as a heap."""
    class Entry(object):
        """An \"entry\" in the heap contains a key and value, as expected,
        but it also contains the position of the entry in the heap. The
        information about the position allows us to make this an adapted
        priority queue, because given an entry we can locate it in O(1)
        time."""

        __nextId = 0

        def __init__(self, key, value, pos):
            """Creates an entry based on a key, value, and position in the heap."""
            self.__key = key
            self.__value = value
            self.__pos = pos
            self.__id = self.__class__.__nextId
            self.__class__.__nextId += 1

        def __str__(self):
            """Returns a string representation of the entry, giving key,
            value, and position information."""
            return "(" + str(self.__key) + ", " + str(self.__value) \
                    + ", " + str(self.__pos) + ")"

        def __eq__(self, other):
            """Equality between two entries is found by comparing their
            ID's. This means, in a sense, that every entry is unique."""
            return self.__id == other.__id

        def __ne__(self, other):
            """Tests non-equality between two entries."""
            return not self == other

        def key(self):
            """Gets the key held by this entry."""
            return self.__key

        def _setKey(self, key):
            """Changes the key held by this entry. Shouldn't be called
            by external functions."""
            self.__key = key

        def value(self):
            """Gets the value held by this entry."""
            return self.__value

        def _setValue(self, value):
            """Changes the value held by this entry. Shouldn't be called
            by external functions."""
            self.__value = value

        def pos(self):
            """Gets the position held by this entry."""
            return self.__pos

        def _setPos(self, pos):
            """Changes the position held by this entry. Shouldn't be called
            by external functions."""
            self.__pos = pos

        def id(self):
            """Gets a unique identifier for this entry."""
            return self.__id

    def __init__(self, l=None, cmp=None, reverse=False):
        """Creates a new priority queue. By default, it is just an
        empty priority queue. But you can also pass in a list L of
        key-value pairs that will be added on startup. You can also
        pass in a custom comparator function CMP which is used to
        determine the ordering of the keys (CMP should behave like
        a normal Python comparator). (Otherwise, a default comparator
        which just compares keys directly using < and > is used.) Finally,
        if REVERSE is false this is a min-heap, but a max-heap otherwise."""
        self.__heap = []
        self.__next = 0

        if cmp is not None:
            self.__cmp = cmp
        else:
            self.__cmp = self.__defaultCmp
        self.__reverse = reverse

        if l is not None:
            self.heapify(l)

    def __str__(self):
        """Returns a string representation of this priority queue. Prints
        out the array representation of the internal heap, and the entry
        in each cell. (This could be better, but anything superior would
        probably require ASCII art.)"""
        sList = ["["]
        for e in self.__heap[0:self.__next]:
            sList.append(str(e) + " ")
        s = ''.join(sList)
        s = s[0:(len(s) - 1)] + "]"
        return s

    def __len__(self):
        """Gets the number of entries currently in the heap."""
        return self.__next

    def __defaultCmp(self, k1, k2):
        """The default comparator for comparing two keys. It does exactly
        what you would expect."""
        if k1 == k2:
            return 0
        elif k1 > k2:
            return 1
        else:
            return -1

    def __fCmp(self, e1, e2):
        """A helper function for convenience. Given two entries E1 and E2,
        it compares the keys contained in the entries as necessitated by the parameters
        passed into __init__() (including REVERSE)"""
        result = self.__cmp(e1.key(), e2.key())
        if self.__reverse:
            return -result
        else:
            return result

    def __leftChild(self, e):
        """Gets the left child of an entry E. (helper)"""
        return self.__heap[e.pos() * 2 + 1]

    def __rightChild(self, e):
        """Gets the right child of an entry E. (helper)"""
        return self.__heap[e.pos() * 2 + 2]

    def __parent(self, e):
        """Gets the parent of an entry E. (helper)"""
        return self.__heap[(e.pos() - 1) // 2]

    def __swap(self, e1, e2):
        """Helper that swaps two entries in the heap, making sure to maintain
        the position markers in the entries, etc. This is at the core
        of upheap and downheap."""
        tmp = e1.pos()
        e1._setPos(e2.pos())
        e2._setPos(tmp)

        tmp = self.__heap[e1.pos()]
        self.__heap[e1.pos()] = self.__heap[e2.pos()]
        self.__heap[e2.pos()] = tmp

    def __upHeap(self, e):
        """Helper that does upheap on an entry E in the heap."""
        while e.pos() > 0 and self.__fCmp(e, self.__parent(e)) < 0:
            self.__swap(e, self.__parent(e))

        return e

    def __downHeap(self, e, end = None):
        if end is None: end = self.__next

        """Helper that does downheap on an entry E in the heap. Restricts"""
        while (e.pos() * 2 + 1 < end \
                and self.__fCmp(e, self.__leftChild(e)) > 0) \
                or (e.pos() * 2 + 2 < end \
                and self.__fCmp(e, self.__rightChild(e)) > 0):
            if e.pos() * 2 + 2 >= end \
                    or self.__fCmp(self.__leftChild(e), self.__rightChild(e)) < 0:
                c = self.__leftChild(e)
                self.__swap(e, c)
            else:
                c = self.__rightChild(e)
                self.__swap(e, c)

        return e

    def __fixPosition(self, e):
        """Helper that \"fixes\" the position of an entry E somewhere in
        the heap. That is, assuming the entry has correct position information,
        and all other entries are in the correct position, it upheaps or downheaps
        the entry as necessary so that the heap remains in heap order."""
        if e.pos() > 0 and self.__fCmp(e, self.__parent(e)) < 0:
            return self.__upHeap(e)
        else:
            return self.__downHeap(e)

    def __getitem__(self, pos):
        """Gets an entry at a certain position in the heap.

        Throws InvalidPositionException if no such position is in the heap.

        Runs in O(1)."""
        if pos < 0 or pos >= self.__next:
            raise InvalidPositionException("position does not exist in heap")

        return self.__heap[pos]

    def isEmpty(self):
        """Returns whether this priority queue is empty."""
        return len(self) == 0

    def top(self):
        """Gets the entry at the top of the heap (the entry with lowest key)

        Throws HeapException if the heap is empty.

        Runs in O(1)."""
        if self.__next == 0:
            raise HeapException("no value in empty heap")

        return self.__heap[0]

    def push(self, key, value):
        """Adds a key-value pair to the heap.

        Runs in O(log n)."""
        if self.__next >= len(self.__heap):
            self.__heap.append(None)
        e = HeapPriorityQueue.Entry(key, value, self.__next)
        self.__heap[self.__next] = e
        self.__next += 1

        return self.__upHeap(e)

    def pop(self):
        """Removes the entry at the top of the heap (the entry with lowest key),
        and returns it.

        Throws HeapException if the heap is empty.

        Runs in O(log n)."""
        if self.__next == 0:
            raise HeapException("cannot pop from empty heap")

        e = self.__heap[0]
        self.__next -= 1

        if self.__next == 0: # heap is empty
            return e

        self.__heap[0] = self.__heap[self.__next]
        self.__heap[0]._setPos(0)
        self.__downHeap(self.__heap[0])

        return e

    def remove(self, entry):
        """Removes an entry ENTRY from the heap, returning ENTRY.

        Throws InvalidEntryException if the entry is not in the heap.

        Runs in O(log n)"""
        self.__testEntry(entry)

        pos = entry.pos()

        self.__next -= 1
        rEntry = self.__heap[self.__next]

        # We got lucky and removed the last node
        if pos == self.__next:
            return entry

        self.__heap[pos] = rEntry
        rEntry._setPos(pos)
        self.__fixPosition(rEntry)

        return entry

    def replaceKey(self, entry, key):
        """Replaces the key of an entry ENTRY in the priority queue, with KEY
        (of course the heap will be adjusted.), returning ENTRY (with the new
        KEY)

        Throws InvalidEntryException if the entry is not in the heap.

        Runs in O(log n)"""
        self.__testEntry(entry)

        self.__heap[entry.pos()]._setKey(key)
        return self.__fixPosition(entry)

    def replaceValue(self, entry, value):
        """Replaces the value of an entry ENTRY in the priority queue, with
        VALUE

        Throws InvalidEntryException if the entry is not in the heap.

        Runs in O(1)."""
        self.__testEntry(entry)

        pos = entry.pos()
        self.__heap[pos]._setValue(value)

        return self.__heap[pos]

    def merge(self, q):
        """Merges this HeapPriorityQueue with another one, Q.

        Throws HeapException if the two queues do not use the same comparator
        or ordering (min/max) (after all, it makes no sense to combine a
        min-heap with a max-heap!)

        Runs in O(n) time.

        #if self.__cmp != q.__cmp or self.__reverse != q.__reverse:
        #    raise HeapException, "heaps do not use same parameters"
        posa = 0
        posb = 0
        npos = 0
        newQ = HeapPriorityQueue(cmp = self.__cmp, reverse = self.__reverse)

        while posa < self.__next and posb < q.__next:
            if self.__fCmp(self[posa], q[posb]) < 0:
                newQ.__heap.append(self.Entry( \
                    self[posa].key(), self[posa].value(), npos))
                posa += 1
                npos += 1
            else:
                newQ.__heap.append(self.Entry( \
                    q[posb].key(), q[posb].value(), npos))
                posb += 1
                npos += 1

        while posa < self.__next:
            newQ.__heap.append(self.Entry( \
                self[posa].key(), self[posa].value(), npos))
            posa += 1
            npos += 1

        while posb < q.__next:
            newQ.__heap.append(q.Entry( \
                q[posb].key(), q[posb].value(), npos))
            posb += 1
            npos += 1

        newQ.__next = self.__next + q.__next
        self.__heap = newQ.__heap
        self.__next = newQ.__next"""
        pass

    def heapify(self, l):
        """Recreates the heap out of a list L, by heapifying it.

        This method runs in O(n) time."""

        self.__heap = []
        # "convert" the array into our internal array, using Entry format
        for i in range(0, len(l)):
            self.__heap.append(self.Entry(l[i][0], l[i][1], i))
        self.__next = len(l)

        for i in range((len(l) - 1) // 2, -1, -1):
            self.__downHeap(self.__heap[i])

    def __testEntry(self, entry):
        """Helper that checks whether an entry is in the heap.

        More precisely, it first sees if the entry has a valid position value,
        and if so, retrieves the entry allegedly at that position, ensuring
        that it's the same as ENTRY."""

        if entry is None:
            raise InvalidEntryException("Entry is None")

        pos = entry.pos()
        if pos < 0 or pos >= len(self) or self.__heap[pos] != entry:
            raise InvalidEntryException("Entry is not in this heap")

if __name__ == "__main__":
    pass
