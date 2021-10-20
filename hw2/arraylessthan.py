#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def array_less_than(p, q):
    """array_less_than: any[] * any[] -> boolean
    Consumes: two python lists
    Produces: a boolean signifying whether all elements in p are smaller than
              their corresponding elements in q
    Purpose: Check if array p and q are equal in size and p < q in contents
    Example: array_less_than([4, 0, -4, 1], [5, 2, 5, 23]) -> True
             array_less_than([1, 4, 2], [4, 1, 2]) -> False
             array_less_than([5, 3], [6, 4, 5, 3]) -> False
    """
    # error checking on input arrays -- are they valid?
    if p is None:
        raise InvalidInputException("array p is None (invalid)")
    if q is None:
        raise InvalidInputException("array p is None (invalid)")

    if(len(p) != len(q) or len(p) == 0):
        return False
    else:
        for i in range(len(p)):
            if(p[i] >= q[i]):
                return False
    return True
