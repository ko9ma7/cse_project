# def solution(cookie):
#
#     m = 2
#     if m == 2:
#         for i in reversed(range(len(cookie[:m+1]))):
#             first_son = cookie[i:m+1]
#             print('f:', first_son)
#         for j in range(m+1, len(cookie)):
#             second_son = cookie[m+1:j+1]
#             print('s:', second_son)

def solution(cookie):
    largest = set()
    for m in range(1, len(cookie)):
        lsum = 0
        rsum = 0
        lsums = set()
        for i in range(m-1, -1, -1):
            lsum += cookie[i]
            lsums.add(lsum)
        for j in range(m, len(cookie)):
            rsum += cookie[j]
            if rsum in lsums:
                largest.add(rsum)
    return max(largest) if largest else 0

print(solution([1,1,2,3,4,5]))