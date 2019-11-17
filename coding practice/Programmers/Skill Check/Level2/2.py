def solution(heights):
    answer = []
    re_heights = list(reversed(heights))

    for i in range(len(re_heights)):
        for j in range(i+1, len(re_heights)):
            print(re_heights[i], re_heights[j])
            if re_heights[i] < re_heights[j]:
                answer.append(j)
                break
            else:
                if j == len(re_heights)-1

    print(answer)

    answer = list(reversed(answer))
    result = []
    for a in answer:
        if a == 0:
            result.append(0)
        else:
            result.append(len(heights)-a)

    return result

print(solution([6,9,5,7,4]))