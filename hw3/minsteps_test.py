#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import minsteps
from importlib import reload
reload(minsteps)
from minsteps import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

def example_test():
    assert minsteps([3, 1, 2, 0, 8]) == 2, "Test Failed"
    assert minsteps([1, 3, 6, 1, 0, 9]) == 3, "Test Failed"
    assert minsteps([8, 1]) == 1
    assert minsteps([1, 3, 1, 2, 4, 1,]) == 3

def exception_test_example():
    # In order to test for an exception, you write something like this:
    with pytest.raises(InvalidInputException):
        minsteps(None) # This test will pass, because the exception will be raised

# Add more test functions here!
def zero_test():
    assert minsteps([0, 5, 7, 9]) == None, "zero should be returned if the array starts with zero"
    assert minsteps([0, 0, 0]) == None, "An array of all zeros should return zero"

def one_step_test():
    assert minsteps([4, 1, 2, 1]) == 1, "Only one step is needed to reach the end of the array"

def no_step_test():
    assert minsteps([3]) == 0, "One item in array not working"

def multiple_single_step_test():
    assert minsteps([1, 1, 1]) == 2, "Not working with only one step multiple times"




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
    return [example_test, exception_test_example, zero_test, no_step_test, one_step_test, multiple_single_step_test]


# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
