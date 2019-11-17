def solution(progresses, speeds):
    answer = []

    for i in range(len(progresses)):
        if (100 - progresses[i]) % speeds[i] != 0:
            answer.append((100-progresses[i]) // speeds[i] + 1)
        else:
            answer.append((100 - progresses[i]) // speeds[i])

    cnt = [0 for i in range(len(answer))]
    max = 0
    max_index = 0
    for i in range(len(answer)):
        if max < answer[i]:
            max = answer[i]
            max_index = i
            cnt[i] += 1
        else:
            cnt[max_index] += 1

    answer = []
    for c in cnt:
        if c == 0:
            pass
        else:
            answer.append(c)

    return answer

print(solution1())