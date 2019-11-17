def solution(s):

    s = s.replace('{{', '{')
    s= s.replace('}}', '}')
    s = s.split('},{')
    answer = []
    for idx, str in enumerate(s):
        if '{' in str:
            str = str.replace('{', '')
        if '}' in str:
            str = str.replace('}', '')

        answer.append([str])

    for a in range(len(answer)):
        answer[a] = answer[a][0].split(',')

    answer = sorted(answer, key=lambda answer: len(answer))
    print(answer)
    result = []
    idx = 0
    while True:
        print('idx:', idx)
        if idx == len(answer):
            break
        if len(answer[idx]) == 1:
            value = answer[idx][0]
            result.append(int(value))
            for j, one in enumerate(answer):
                print('j:', j)
                print('일', one)
                if value in one:
                    print('있당')
                    one.remove(value)

        idx += 1

    print('result:', result)

s = "{{2},{2,1},{2,1,3},{2,1,3,4}}"
# s = "{{4,2,3},{3},{2,3,4,1},{2,3}}"
# s = "{{20,111},{111}}"
# s = "{{123}}"
solution(s)
