def combination(input, set, k, start):
    if(len(set) == k):
        print(set)
        return
    
    for i in range(start, len(input)):
        set.append(input[i])
        combination(input, set, k, i + 1)
        set.pop(len(set) -1)

    pass

combination([1,2,3,4], [], 3, 0)

print("----------------")

def combination2(input, set, k, i):
    if(len(set) == k):
        print(set)
        return

    if(i == len(input)):
        return
    
    set.append(input[i])
    combination2(input, set, k, i + 1)
    set.pop(len(set) -1)
    combination2(input, set, k, i + 1)
    pass

combination2([1,2,3,4], [], 3, 0)