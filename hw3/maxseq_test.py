#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import maxseq
from importlib import reload
reload(maxseq)
from maxseq import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

def example_test():
    assert maxseq([-1, 2, 7, -8, 13, -2]) == 194, "Test Failed"
    assert maxseq([-7, -9, -10]) == 180, "Test Failed"
    

def bruh_test():
    assert maxseq([30, 10, -20, 50]) == 250

def zero_test():
    assert maxseq([0, 0, 0]) == 180, "An array of zeros should return zero"

def two_value_test():
    assert maxseq([-1, 1]) == 181, "Should only return positive componenet"
    assert maxseq([2, -2]) == 182, "Should only return positive component"

def single_item_test():
    assert maxseq([-50]) == 180, "One negative item in array not working"
    assert maxseq([1]) == 181, "One positive item in array not working"


def alternating_values_test():
    assert maxseq([-1, 1, 2, -2, -3, 3]) == 183, "Alternating Values not working"
    assert maxseq([-1, 1, -2, 2, -3, 3]) == 183, "Alternating Values not working"

def empty_array_test():
    assert maxseq([]) == 180, "Empty array not working"

def exception_test_example():
    # In order to test for an exception, you write something like this:
    with pytest.raises(InvalidInputException):
        maxseq(None) # This test will pass, because the exception will be raised

# Add more test functions here!

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
    return [example_test, exception_test_example, bruh_test, empty_array_test, alternating_values_test, single_item_test, two_value_test, zero_test, bruh_test]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
