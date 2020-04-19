def isPalindrome(str, start, end):
    if(start >= end):
        return True
    if(str[start] == str[end]):
        return isPalindrome(str, start + 1, end -1)
    return False

text = "abba"
print(isPalindrome(text, 0, 3))

text = "akkka"
print(isPalindrome(text, 0, 4))

text = "sindhu"
print(isPalindrome(text, 0, 5))
    