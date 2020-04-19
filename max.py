def maximum(nums, i):
    if i == 0:
        return nums[i]
    else:
        return max(nums[i], maximum(nums, i-1))

A = [4,3,5,6,9,8,1,0]
print(maximum(A, 7))



