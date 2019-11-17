# Solution 1
def listsum(numList):
    theSum = 0
    for i in numList:
        theSum += i
    return theSum

print(listsum([1, 3, 5, 7, 9]))

# Solution 2
def listsum(numList):
    if len(numList) == 1:
        return numList[0]
    else:
        return numList[0] + listsum(numList[1:])

print(listsum([1, 3, 5, 7, 9]))