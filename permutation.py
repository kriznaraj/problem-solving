# arrangements

def permutaion(input, partial, used):
    if(len(partial) == len(input)):
        print(partial)

    for i in range(0, len(input)):
        if not used[i]:
            used[i] = True
            partial.append(input[i])
            permutaion(input, partial, used)
            used[i] = False
            partial.pop(len(partial) - 1)

permutaion([1,2, 1], [], [False, False, False])

print("----------------")


def permutation2(input_list, partial2, used2):
    if len(partial2) == len(input_list):
        print(partial2)
    else:
        for i in range(0, len(input_list)):
            if not used2[i] and not (input_list[i] == input_list[i - 1] and not used2[i - 1]):
                used2[i] = True
                partial2.append(input_list[i])
                permutation2(input_list, partial2, used2)
                used2[i] = False
                partial2.pop(len(partial2) - 1)


# nums = [1, 2, 3, 4, 5]
nums = [1, 1, 1, 2]
used = [False for _ in range(0, len(nums))]
permutation2(nums, [], used)


