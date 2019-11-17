def solution(n,a,b):
    answer = 0
    n = [i for i in range(1, n+1)]

    if n == 2:
        return 1
    else:
        n = [[n[i], n[i+1]] for i in range(0, len(n)-1, 2)]
        answer += 1
        for i in range(len(n)):
            if a and b in n[i]:
                return answer
            else:
                solution(len(n), (a+1)//2, (b+1)//2)

print(solution(8, 4, 7))