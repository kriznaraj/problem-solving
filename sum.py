def sumDigit1(n, sum):
    if(n == 0):
        return sum
    sum = sum + n%10
    n = int(n/10)
    return sumDigit1(n, sum)

def sumDigit(n):
    if(n == 0):
        return 0
    return n%10 + sumDigit(int(n/10))

print(sumDigit(123456))
print(sumDigit1(123456, 0))