#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
from arraylessthan import array_less_than
import arraysearch
from importlib import reload
reload(arraysearch)
from arraysearch import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

def example_test():
	assert array_search(2, [1, 2, 3]) == True, "Test Failed"
	assert array_search(6, [1, 7, 9, 2, 3]) == False, "Test Failed"

def exception_test_example():
	# In order to test for an exception you expect to be raised, you write something like this:
	# This test will pass, because the exception will be raised
	with pytest.raises(InvalidInputException):
		array_search(None, [9,3,5])

		#added exception tests
		
		array_search([None], [None])
		array_search([None], [3, 4, 3])
		array_search(1, [None])
		
		array_search(None, 1)
		array_search(None, None)
		array_search(1, None)

# Add more test functions here!
def negative_test():
	assert array_search(-1, [0, -1, 2, 5, -2]) == True, "Couldn't find negative number in array"

def zero_test():
	assert array_search(0, [1, 13, 4, 0, 1]) == True, "Couldn't find 0 in array"
	
def multiple_instances_test():
	assert array_search(4, [0, 2, 4, 4, 4, 4]) == True, "Isn't working when item is found multiple times in the array"

def single_item_array_test():
	assert array_search(3, [3]) == True, "Array of size 1 with desired value"

def empty_array_test():
	assert array_search(-1, []) == False, "Empty array not working"

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
	return [example_test, exception_test_example, negative_test, multiple_instances_test, zero_test, single_item_array_test, empty_array_test]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
	print('Running tests...')
	for test in get_tests():
		test()
	print('All tests passed!')
