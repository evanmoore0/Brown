#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def array_search(item, array):
    """array_search: int * (int array) -> bool
    Purpose: Checks if a specific integer is in an int array
    Consumes: an integer and an array
    Produces: a boolean indicating if the integer is in the array
    Example: array_search(3, [1,3,4]) -> True
             array_search(6, [1,3,4]) -> False
    """

    # error checking on input array -- is it valid?
    if array is None:
        raise InvalidInputException("array is None (invalid)")

    # error checking on input item -- is it valid?
    if item is None:
        raise InvalidInputException("item is None (invalid)")

    
    for int in array:
        if (int == item):
            return True
    return False
