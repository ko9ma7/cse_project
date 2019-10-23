Q = int(input())

for q in range(Q):
    N = int(input())
    kids = list(map(int, input().split()))

    new = []
    for n in range(N):
        new.append(0)

    ans = []
    for i in range(len(kids)):
        j = i
        cnt = 0
        while True:
            new[i], new[kids[i]-1] = new[kids[i]-1], new[i]
            i = kids[i]-1
            cnt += 1

            if j == i:
                break

        ans.append(cnt)

    for i in range(len(ans)):
        if i == len(ans) - 1:
            print(ans[i])
        else:
            print(ans[i], end=' ')