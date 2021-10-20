#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import functional
from importlib import reload
reload(functional)
from functional import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.


# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.

# Here are some example test functions. Feel free to delete example_test_1 and
# remove it from the list in get_tests().

def example_test_1():
    assert 1 == 1, 'Error: 1 does not equal 1!'

def simple_test():

    assert apply_all([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == [5,6,7], "apply_all is not correct"

    assert compose([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == 10, "compose is not correct"

    assert list_compose_steps([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == [4, 5, 7, 10], "list_compose_steps is not correct"

def none_test():
    with pytest.raises(InvalidInputException):
        apply_all(None, None)
    with pytest.raises(InvalidInputException):
        compose(None, None)
    with pytest.raises(InvalidInputException):
        list_compose_steps(None, None)

def no_function_test():
     
    assert apply_all([], 2) == [], "Apply all not working with empty list of functions"
    assert compose([], 1) == 1, "Compose not working with empty list of functions"
    assert list_compose_steps([], 3) == [3], "List compose steps not working with empty list of functions"

def none_function_test():
    with pytest.raises(InvalidInputException):
        apply_all(None, 1)
    with pytest.raises(InvalidInputException):
        compose(None, 2)
    with pytest.raises(InvalidInputException):
        list_compose_steps(None, 1)

def none_value_test():
    with pytest.raises(InvalidInputException):
        apply_all([lambda x: x+1, lambda x: x*2], None)
    with pytest.raises(InvalidInputException):
        compose([lambda x: x-1, lambda x: x*10], None)
    with pytest.raises(InvalidInputException):
        list_compose_steps([lambda x: x*3, lambda x: x*4], None)

def decimal_test():
    assert apply_all([lambda x: x+1.5, lambda x: x+1.3, lambda x: x*10], 0.2) == [1.7, 1.5, 2], "Apply all not working with decimals"
    assert compose([lambda x: x*2, lambda x: x+1, lambda x: x+2], 1.4) == 5.8, "Compose not working with decimals"
    assert list_compose_steps([lambda x: x+1.2, lambda x: x+10, lambda x:x*20], 1.3) == [1.3, 2.5, 12.5, 250], "List compose steps not working with decimals"

def neg_test():
    assert apply_all([lambda x: x*2, lambda x: x-1, lambda x: x*10], -3) == [-6, -4, -30], "Apply all not working with decimals"
    assert compose([lambda x: x*3, lambda x: x*2, lambda x: x/4], -4) == -6, "Compose not working with decimals"
    assert list_compose_steps([lambda x: -x, lambda x: x+10, lambda x:x-30], -4) == [-4, 4, 14, -16], "List compose steps not working with decimals"

def single_function_test():
    assert apply_all([lambda x: x+1], 0) == [1], "Apply all not working with single function input"
    assert compose([lambda x: x*2], 1) == 2, "Compose not working with single function input"
    assert list_compose_steps([lambda x: -x], 1000) == [1000, -1000], "list compose steps not working with single function input"


def get_tests():
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # VERY IMPORTANT
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
    return [example_test_1, simple_test, none_value_test, none_function_test, none_test, no_function_test, decimal_test, neg_test, single_function_test]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
