#!/usr/bin/python3

class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

    
def insertion_sort(array):
    """
    insertion_sort: int array -> int array
	Purpose: Sort the input array of integers in descending order using the insertion sort algorithm
	Consumes: an array of integers
	Produces: an array of integers sorted in descending order
	Example: insertion_sort([4,5,1,3,2]) -> [5,4,3,2,1]
	Throws: InvalidInputException if list is None


    function insertion_sort(A): 
    n = A.length
    for i = 1 to n-1:
        for j = i down to 1:
            if a[j] < a[j-1]:
                swap a[j] and a[j-1]
            else:
                break  
            # out of the inner for loop
            # this prevents double checking the # already sorted portion
	"""

    n = len(array)
    for i in range(1, n):
        for j in range(i, 0, -1):
            if array[j] > array[j-1]:
                swap = array[j]
                array[j] = array[j-1]
                array[j-1] = swap
            else:
                break
        print (array)

    return array


def selection_sort(array):
    """
    selection_sort: int array -> int array
	Purpose: Sort the input array of integers in descending order using the selection sort algorithm
	Consumes: an array of integers
	Produces: an array of integers sorted in descending order
	Example: selection_sort([4,5,1,3,2]) -> [5,4,3,2,1]
	Throws: InvalidInputException if list is None

    function selection_sort(A): n = A.length
    for i = 0 to n-2:
        min = argmin(A[i:n-1])
        swap A[i] with A[min]
	"""
    n = len(array)
    for i in range(0, n-1):
        max = array[i]
        index = i
        for j in range(i+1, n):
            if array[j] > max:
                max = array[j]
                index = j
        swap = array[i]
        array[i] = array[index]
        array[index] = swap
    return array

    
def merge_sort(array):
    """merge_sort: int array -> int array
        Purpose: Sort the input array of integers in descending order using the merge sort algorithm
        Example: merge_sort([4,5,1,3,2]) -> [5,4,3,2,1]
        Throws: InvalidInputException if list is None

    function mergeSort(A): 
        if A.length <= 1:
            return A
        mid = A.length/2
        left = mergeSort(A[0...mid-1])
        right = mergeSort(A[mid...n-1])
        return merge(left, right)
    """

    if len(array) <=1:
        return array
    else:
        mid = (int)(len(array)/2)
        left = merge_sort(array[0:mid])
        right = merge_sort(array[mid:len(array)])


    return merge(left, right)

    """
    function merge(A, B): result = []
        aIndex = 0
        bIndex = 0
        while aIndex < A.length and bIndex < B.length:
            if A[aIndex] <= B[bIndex]:
            result.append(A[aIndex])
            aIndex++
            else:
                result.append(B[bIndex])
                bIndex++
            if aIndex < A.length:
                result = result + A[aIndex:end]
            if bIndex < B.length:
                result = result + B[bIndex:end]
        return result

    """

def merge(left, right):
    result = []
    aIndex = 0
    bIndex = 0
    while aIndex < len(left) and bIndex < len(right):
        if left[aIndex] <= right[bIndex]:
            result.append(left[aIndex])
            aIndex+=1
        else:
            result.append(right[bIndex])
            bIndex +=1
        if aIndex < len(left):
            result = result + left[aIndex:]
        if bIndex < len(right):
            result = result + right[bIndex:]
    return result