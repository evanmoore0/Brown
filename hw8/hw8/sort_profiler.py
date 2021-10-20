#!/usr/bin/python3
from sort import *
import time

def sortFile(filename, sortingAlg):
    """sortFile: string, function -> None
        Purpose: Checks the time elapsed during the function call (in seconds)
        Example: sortFile(unsortedNums.txt, quick_sort) --> 1.23
    """
    f = open(filename, "r")
    numList = [int(line.strip()) for line in f]
    f.close()

    start = time.time()
    sortingAlg(numList)
    end = time.time()
    elapsed = end - start

    print("\t" + filename + " time: " + str(elapsed))

if __name__ == '__main__':
    # Maps the name of an algorithm to its function
    algorithms = {'INSERTION SORT': insertion_sort, 'MERGE SORT': merge_sort, 'SELECTION SORT': selection_sort}
    for alg in algorithms:
        print(alg)
        sortFile('numlist1.txt', algorithms[alg])
        sortFile('numlist2.txt', algorithms[alg])
