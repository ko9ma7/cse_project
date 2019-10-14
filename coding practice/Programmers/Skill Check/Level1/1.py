def solution(arr):
    answer = []

    for idx, i in enumerate(arr):
    # print("idx: " + str(idx))
    # print("i: " + str(i))
        if idx >= len(arr) - 1:
            answer.append(i)
            break
        elif arr[idx + 1] == i:
            # print("같은 값")
            continue
        else:
            # print("다른 값")
            answer.append(i)

    return answer