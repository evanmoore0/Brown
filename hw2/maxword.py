#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def max_word(s):
    """max_word: string -> int
    Purpose: Find the number of occurences of the most common word in a string
    Consumes: a string
    Produces: an int, the number of occurences of the most common word in the string
    Example: max_word("hello world") -> 1
             max_word("the quick brown fox jumped over the lazy dog") -> 2
    """
    # error checking on input string -- is it valid?
    if s is None or s == "":
        raise InvalidInputException("string is invalid")

    if(s == " "):
        return 0

    a = s.split(" ")
    dict = {}

    solution = 0

    for word in a:
        #dict[word] = a.count(word)
        if word in dict:
            dict[word] += 1
        else:
            dict[word] = 1

    for key in dict:
        if(solution < dict[key]):
            solution = dict[key]
    return solution    
        