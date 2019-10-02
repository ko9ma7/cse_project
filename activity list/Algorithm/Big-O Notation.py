'''
    An Anagram Detection Example

    "Problem Solving with Algorithms and Data Structures using Python" - by Brad Miller and David Ranum, Luther College -
'''

# Solution 1. Checking Off
# 첫 번째 문자열의 각 문자가 두 번째 문자열에 있는지 checking 한다.
# Solution 1 방식을 분석해보면, s1의 n개의 문자를 s2의 n개의 문자를 비교하면서 반복하기 때문에 산술을 해보면 O(n^2)이 된다.
def anagramSolution1(s1,s2):
    if len(s1) != len(s2):
        stillOK = False

    # 두 번재 문자열을 리스트로 바꿔서 인덱싱이 가능하도록 한다.
    alist = list(s2)

    pos1 = 0
    stillOK = True

    # 인덱스 위치가 첫 번째 문자열보다 크다면 더 이상 비교할 이유가 없기 때문에 작을 때까지만이 조건식이다.
    while pos1 < len(s1) and stillOK:
        pos2 = 0
        found = False
        while pos2 < len(alist) and not found:
            # 첫 번째 문자열의 pos1의 위치에 있는 문자가 두 번째 문자열의 pos2의 위치에 있는 문자와 같은 경우
            if s1[pos1] == alist[pos2]:
                found = True
            else:
                pos2 = pos2 + 1
        # 문자를 찾았다면 다음 문자도 비교해보아야 하기 때문에 모두 초기화를 해주어야 한다.
        if found:
            alist[pos2] = None
        else:
            stillOK = False

        pos1 = pos1 + 1

    return stillOK

print(anagramSolution1('abcd','dcba'))

# Solution 2. Sort and Compare
# 각 문자열을 a부터 z까지 순서대로 정렬 후, 서로 같다면 같은 문자열이다.
# Solution 2 방식을 분석해보면, 얼핏보면 O(n) 같아 보이지만 마지막에 n만큼 비교해주기 때문에 산술을 해보면 O(n^2)이다.
def anagramSolution2(s1,s2):
    alist1 = list(s1)
    alist2 = list(s2)

    # 두 문자열을 각각 정렬해준다.
    alist1.sort()
    alist2.sort()

    pos = 0
    matches = True

    while pos < len(s1) and matches:
        # 첫 번재 인덱스부터 문자열의 길이까지 하나씩 비교해보면서 같은지 다른지 알 수 있다.
        if alist1[pos] == alist2[pos]:
            pos = pos + 1
        else:
            matches = False

    return matches

print(anagramSolution2('abcde','edcba'))

# Solution 3. Brute Force
# 각각 하나씩 비교해보면서 하는 가장 안좋은 방법이다.
'''
    A brute force technique for solving a problem typically tries to exhaust all possibilities. 
    For the anagram detection problem, we can simply generate a list of all possible strings using the characters from s1 and then see if s2 occurs. 
    However, there is a difficulty with this approach. 
    When generating all possible strings from s1, there are n possible first characters, n−1 possible characters for the second position, n−2 for the third, and so on. 
    The total number of candidate strings is n∗(n−1)∗(n−2)∗...∗3∗2∗1, which is n!. 
    Although some of the strings may be duplicates, the program cannot know this ahead of time and so it will still generate n! different strings.

    It turns out that n! grows even faster than 2n as n gets large. 
    In fact, if s1 were 20 characters long, there would be 20!=2,432,902,008,176,640,000 possible candidate strings. 
    If we processed one possibility every second, it would still take us 77,146,816,596 years to go through the entire list. 
    This is probably not going to be a good solution.
'''

# Solution 4. Count and Compare
# 문자마다 하나씩 26 개의 카운터 목록을 사용할 수 있다.
# 특정 문자마다 해당 위치에서 카운터가 증가하며, 결국 두 카운터 목록이 동일한 경우 문자열은 'Anagram'이 된다.
def anagramSolution4(s1,s2):
    c1 = [0] * 26
    c2 = [0] * 26

    for i in range(len(s1)):
        pos = ord(s1[i]) - ord('a') # ord는 문자의 아스키코드 값을 돌려주는 함수이다.
        c1[pos] = c1[pos] + 1

    for i in range(len(s2)):
        pos = ord(s2[i]) - ord('a')
        c2[pos] = c2[pos] + 1

    j = 0
    stillOK = True
    while j < 26 and stillOK:
        # 0부터 25까지 각 문자열의 count 값이 같다면 'Anagram'이다.
        if c1[j] == c2[j]:
            j = j + 1
        else:
            stillOK = False

    return stillOK

print(anagramSolution4('apple','pleap'))