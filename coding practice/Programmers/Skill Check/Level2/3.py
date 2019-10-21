def solution(nums):
    N = len(nums)
    select = N // 2

    nums = list(set(nums))

    if select == 1:
        answer = 1
    else:
        if len(nums) < select:
            answer = len(nums)
        else:
            answer = select

    return answer