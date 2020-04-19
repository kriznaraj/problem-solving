#decreasing order
def isSequence(arr, i):
    if(i == 0):
        return True
    return arr[i - 1] + 1 == arr[i] and isSequence(arr, i-1)


#increasing order
def check_seq(arr, i):
    return i == len(arr) -1 or (arr[i] == arr[i + 1] -1 and check_seq(arr, i + 1))


A = [2,3,4,5,6,7]
print(isSequence(A, 5))
print(check_seq(A, 0))

A = [0,0,0]
print(isSequence(A, 2))
print(check_seq(A, 0))

A = [2,3,4,6,7]
print(isSequence(A, 4))
print(check_seq(A, 0))


# 2, 3, 