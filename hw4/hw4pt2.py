import random

def lessRandomIncrement(n):
    arr1 = [0] * n
    i = 0
    #Use a while loop so if the randomly generated indexes are
    #you can rerun the iteration
    while i < n:
        x = random.randint(0, n-1)
        j = random.randint(0, n-1)
        if(j > x):
            arr1[x] +=1
        elif(x > j):
            arr1[j] +=1
        else:
            continue
        i+=1
    print("n:")
    print(n)
    print("max arr1:")
    print(max(arr1))

lessRandomIncrement(2)
lessRandomIncrement(4)
lessRandomIncrement(8)
lessRandomIncrement(16)
lessRandomIncrement(32)
lessRandomIncrement(64)
lessRandomIncrement(128)
lessRandomIncrement(256)
lessRandomIncrement(512)
lessRandomIncrement(1048)
lessRandomIncrement(2**11)
lessRandomIncrement(2**12)
lessRandomIncrement(2**13)
lessRandomIncrement(2**14)
lessRandomIncrement(2**15)

    