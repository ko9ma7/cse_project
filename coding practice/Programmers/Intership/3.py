def permutation(arr, r):
    # 1.
    arr = sorted(arr)
    used = [0 for _ in range(len(arr))]

    def generate(chosen, used):
        # 2.
        if len(chosen) == r:
            print(chosen)
            return

        for i in range(len(arr)):
            # 3.
            if not used[i] and (i == 0 or arr[i - 1] != arr[i] or used[i - 1]):
                chosen.append(arr[i])
                used[i] = 1
                generate(chosen, used)
                used[i] = 0
                chosen.pop()

    generate([], used)

def solution(user_id, banned_id):

    result = []
    cnt = [0] * len(banned_id)

    for i, banned in enumerate(banned_id):
        # print('banned:', banned)
        banned_index = []
        tempory = []
        for j, b in enumerate(banned):
            if b == '*':
                banned_index.append(j)

        # user_id의 값들을 비교해보기
        for idx, user in enumerate(user_id):
            origin = user
            # 비교하기 전에 길이의 값으로 거를 수 있음
            if len(banned) == len(user):
                for id in range(len(banned_index)):

                    # banned_index의 위치와 똑같이 '*'로 바꿔주기
                    user = ReplaceStringByIndex(user, banned_index[id], '*')
                    # 값이 같으면 count
                    if user == banned:
                        tempory.append(origin)
                # print(user)
        result.append(tempory)
    print(result)

    permutation(result, len(banned_id))

    # last_answer = []
    # for idx, r in enumerate(result):
    #     r = tuple(r)
    #     last_answer.append(r)
    # last_answer = list(set(tuple(last_answer)))
    # print(last_answer)



    # idx = 0
    # pp = []
    # possible = []
    # for i in range(len(result)):
    #     for j in range(len(result[i])):
    #         possible.append(result[i][j])
    #
    #     pp.append(possible)
    #
    # print(pp)
    # print()
def ReplaceStringByIndex(str, idx = 0, replace = ''):
    return '%s%s%s'%(str[:idx], replace, str[idx+1:])

user_id1 = ["frodo", "fradi", "crodo", "abc123", "frodoc"]
banned_id1 = ["fr*d*", "abc1**"]

user_id2 = ["frodo", "fradi", "crodo", "abc123", "frodoc"]
banned_id2 = ["*rodo", "*rodo", "******"]

user_id3 = ["frodo", "fradi", "crodo", "abc123", "frodoc"]
banned_id3 = ["fr*d*", "*rodo", "******", "******"]

solution(user_id1, banned_id1)
solution(user_id2, banned_id2)
solution(user_id3, banned_id3)