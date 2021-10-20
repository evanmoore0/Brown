import random

def randomIncrement(n):
    arr1 = [0] * n
    for i in range(0, n):
        x = random.randint(0, n-1)
        arr1[x] +=1
    print("n:")
    print(n)
    print("max arr1:")
    print(max(arr1))
    print("arr1")
    print(arr1)

randomIncrement(2)
randomIncrement(4)
randomIncrement(16)
randomIncrement(32)
randomIncrement(64)
# randomIncrement(128)
# randomIncrement(256)
# randomIncrement(512)
# randomIncrement(1048)
# randomIncrement(2**11)
# randomIncrement(2**12)
# randomIncrement(2**13)
# randomIncrement(2**14)
# randomIncrement(2**15)


