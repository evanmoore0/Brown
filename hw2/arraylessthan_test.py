#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
from arraysearch import array_search
from arraysearch_test import single_item_array_test
import arraylessthan
from importlib import reload
reload(arraylessthan)
from arraylessthan import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

def example_test():
    assert array_less_than([4, 0, -4, 1], [5, 2, 5, 23]) == True, "Test Failed"
    assert array_less_than([27, 0], [5]) == False, "Test Failed"

def exception_test_example():
    # In order to test for an exception, you write something like this:
    # This test will pass, because the exception will be raised
    with pytest.raises(InvalidInputException):
        array_less_than(None, [9,3,5])
        
        #added exception tests

        array_less_than(None, None)
        array_less_than([None], [None])
        array_less_than([1, 2], [None])
        array_less_than([None], [1, 3])
        array_less_than(None, [1, 3])
        array_less_than([1, 3], None)
       
def negative_test():
    assert array_less_than([-5, -10, -3, -2], [-2, -9, -2, -1]) == True, "Full list of smaller negative numbers not working"
    assert array_less_than([-4, -2, -1, -3], [-3, -1, -5, -10]) == False, "Full list of negative numbers with smaller negative number in q not working"

def equal_test():
    assert array_less_than([4, 2, 1, 0], [4, 2, 1, 0]) == False, "p and q have all same values should return False"


def empty_array():
    assert array_less_than([1, 3, 5], []) == False, "Empty second array"
    assert array_less_than([], [1, 3 ,6, 10]) == False, "Empty first array"
    assert array_less_than([], []) == False, "Two empty arrays"

def decimal_test():
    assert array_less_than([1.1, 2.1, 3.1], [1.3, 3.12, 3.5]) == True, "Not working with decimals"

    
def single_item_test():
    assert array_less_than([1], [-1]) == False, "Single item in both arrays not working"


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
    return [example_test, exception_test_example, negative_test, equal_test, empty_array, single_item_test, decimal_test]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
