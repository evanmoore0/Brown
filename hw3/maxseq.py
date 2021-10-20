#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def maxseq(array):
    """maxseq: any[] -> int
    Consumes: one python list
    Produces: the greatest sum of a subarray + 180
    Example: maxseq([-1, 2, 7, -8, 13, -2]) -> 194
             maxseq([-7, -9, -10]) -> 180
             maxseq([10, -5, 200]) -> 385
    """

    # error checking on input array -- is it valid?
    if array is None or len(array) == 0:
        raise InvalidInputException("array is None (invalid)")

    solution = 180
    bruh = []
    bruh.append(solution)
    for i in range(len(array)):
        if (array[i] != None and array[i] + solution >= 180):
            solution += array[i]
            bruh.append(solution)
        else:
            solution = 180
    return max(bruh)


    