Q = int(input())

for q in range(Q):
    cnt = int(input())
    student = sorted(list(map(int, input().split())))

    answer = []
    for idx in range(len(student)-1):
        if abs(student[idx]-student[idx+1]) == 1:
            answer.append(student[idx])

    if answer == []:
        print(1)
    elif len(answer) != len(student):
        print(2)