#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def minsteps(array):
    """minsteps: any[] -> int
    Consumes: one python list
    Produces: the min number of steps to get from the first to the last element
      in the array
    Example: minsteps([3, 1, 2, 0, 8]) -> 2
     minsteps([1, 3, 6, 1, 0, 9]) -> 3
     minsteps([0, 5, 2, 7, 1, 3]) -> None
    """

    # error checking on input array -- is it valid?
    if array is None:
        raise InvalidInputException("array is None (invalid)")

    bruh=0
    a=0
    solution = 0
    bruhsk=[]
    oneJump = 0
    if(array[0] == 0):
        return None

    if(len(array) == 0  or len(array) ==1):
        return 0

    for i in range(len(array)):  
        if(i==0):
            bruh = array[i]
        if(bruh + a >= len(array)):
            solution+=1
            return solution
        else:
            bruhsk = array[a+1: bruh+1+a]  
            bruh = max(bruhsk)
            a = array.index(max(bruhsk))
        if(array.index(bruhsk[len(bruhsk)-1]) != len(array)-1):
            solution+=1
        if(a==0 and oneJump == len(array)-1):
            return oneJump
        elif(a == 0):
            oneJump +=1
            
        
    

            
        
        # for j in range(1,array[i]+1):
        #     if(i+array[bruh] >= len(array)):
        #         print(solution)
        #         return solution
        #     if(i != bruh):
        #         break
        #     else:
        #         bruhsk = array[i:array[bruh]+i]
        #         print("array:")
        #         print(bruhsk)
        # if(len(bruhsk) != 0):

        #     bruh = array.index(max(bruhsk))
        #     bruhsk.clear()
        #     solution+=1

        
        # for j in range(1, array[i]+1):
        #     # if(i+j >= len(array)):
        #     #     return solution
        #     # if(array[i+j] > bruh):
        #     #     print("Bruh:")
        #     #     print(bruh)
        #     #     bruh = array[i+j]
        #     #     print("Solution: ")
        #     #     print(solution)
        #     #     solution +=1
        #     if(i+j < len(array)):
        #         bruhsk.append(array[i+j])
        
        # if(len(bruhsk) != 0):
        #     i = max(bruhsk) + i
        #     bruhsk.clear()
        #     print("solution 1 is;")
        #     print(solution)
        #     solution+=1
        # if(bruh >= len(array)):
        #     print("solution is")
        #     print(solution)
        #     return solution
        # if(array[bruh] > len(array)):
        #     print(solution)
        #     return solution
        # bruhsk = array[bruh : array[bruh]]
        # print(bruhsk)

        # if(len(bruhsk) > 0):
        #     bruh = array.index(max(bruhsk))
        #     solution+=1
        # else:
        #     bruh+=1

        


                
