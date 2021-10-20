#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENT
import maxword
from importlib import reload
reload(maxword)
from maxword import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# Here are some example test functions. Feel free to delete example_test
# and remove it from the list in get_tests().

def example_test():
    assert max_word("hello world") == 1, "Test Failed"
    assert max_word("the quick brown fox jumped over the lazy dog") == 2, "Test Failed"


def exception_test_example():
    # In order to test for an exception you expect to be raised, you write something like this:
    with pytest.raises(InvalidInputException): # This test will pass, because the exception will be raised
        max_word("")

    with pytest.raises(InvalidInputException): # This test will pass, because the exception will be raised
        max_word(None)

def same_word_test():
    assert max_word("helloworld bruh hello helloworld hello") == 2, "Same word multiple times, and same word within a word not working"


def single_item_test():
    assert max_word("s") == 1, "Single letter should return 1"
    assert max_word(" ") == 0, "String with just a space should return 0"

def different_word_test():
    assert max_word("hello bruh") == 1, "Two different words should only return 1"



def get_tests():
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # IMPORTANT
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # Add the names of each of your test functions to this list. It is very
    # important that you do this, or the TAs will not run your tests properly
    # and you will not receive full credit.
    return [example_test, exception_test_example, single_item_test, same_word_test, different_word_test]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print("Testing...")
    for test in get_tests():
        test()
    print("All tests passed!")
